package android.rezkyauliapratama.id.robusta.controller.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.rezkyauliapratama.id.robusta.R;
import android.rezkyauliapratama.id.robusta.controller.service.GPSTracker;
import android.rezkyauliapratama.id.robusta.database.Facade;
import android.rezkyauliapratama.id.robusta.database.entity.KursusTbl;
import android.rezkyauliapratama.id.robusta.database.entity.SekolahTbl;
import android.rezkyauliapratama.id.robusta.databinding.FragmentMapBinding;
import android.rezkyauliapratama.id.robusta.model.DataModel;
import android.rezkyauliapratama.id.robusta.model.DirectionObject;
import android.rezkyauliapratama.id.robusta.model.GpsEvent;
import android.rezkyauliapratama.id.robusta.model.LegsObject;
import android.rezkyauliapratama.id.robusta.model.PolylineObject;
import android.rezkyauliapratama.id.robusta.model.RouteObject;
import android.rezkyauliapratama.id.robusta.model.StepsObject;
import android.rezkyauliapratama.id.robusta.observer.RxBus;
import android.rezkyauliapratama.id.robusta.utility.Helper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.LocationResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Rezky Aulia Pratama on 12/6/2017.
 */

public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    public final static int TARGET = 1;
    public final static String DiALOG = "DIALOG";
    private final static String ARG_PARAM1 = "ARG_PARAM1";
    private final static String ARG_PARAM2 = "ARG_PARAM2";

    private static final int PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 940;

    private GoogleApiClient mGoogleApiClient;

    private GoogleMap mMap;
    private String knownName;

    private Disposable disposableGpsEvent;

    private LatLng currentLatLng;
    private LatLng destionationLatLng;
    private boolean needRefresh = true;
    private CircleOptions circleOptions;
    private boolean blockMap = false;
    private View rootView;

    private Marker mCurrentMarker;
    private Circle mCurrentCircle;

    FragmentMapBinding binding;
    SupportMapFragment supportMapFragment;


    public static MapFragment newInstance(double latitude,double longitude) {
//        Timber.e("SET LATITUDE : "+latitude+" , LONGITUDE : "+longitude);
        MapFragment dialogFragment = new MapFragment();
        Bundle args = new Bundle();
        args.putDouble(ARG_PARAM1, latitude);
        args.putDouble(ARG_PARAM2, longitude);
        dialogFragment.setArguments(args);

        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            double latitude = getArguments().getDouble(ARG_PARAM1);
            double longitude= getArguments().getDouble(ARG_PARAM2);
            currentLatLng = new LatLng(latitude,longitude);
            Timber.e("GET LATITUDE : "+latitude+" , LONGITUDE : "+longitude);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT < 21) {
            supportMapFragment = (SupportMapFragment) getActivity()
                    .getSupportFragmentManager().findFragmentById(R.id.map);
            Timber.e(supportMapFragment.getTag());

        } else {
            supportMapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            Timber.e(""+R.id.map);
        }

            supportMapFragment.getMapAsync(this);



        binding.textViewAddress.setText(".......\n.......\n.......");


        setupGoogleApiClient();
        getLocation();


        if (currentLatLng.latitude == 0 && currentLatLng.longitude==0) {
            GPSTracker gps = new GPSTracker(getContext(), new GPSTracker.OnLocationEventListener() {
                @Override
                public void onChange(GPSTracker gpsTracker, Location location) {
                    if (location != null) {
                        currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                        locateMe();

                    }

                    gpsTracker.stopUsingGPS();
                }
            });

        }

        disposableGpsEvent = RxBus.getInstance().observable(GpsEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {
            updateGps(events.getLatitude(), events.getLongitude());
        });



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Timber.e("Map Ready");

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getActivity(), R.raw.map_style));


        } catch (Resources.NotFoundException e) {
            Timber.e("ERR RESOURCES : ".concat(e.getMessage()));
        }
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
//        mMap.getUiSettings().set(true);
        mMap.setTrafficEnabled (true);


       locateMe();

       DataModel dataModel = getDataModel();
       locatePlaces(dataModel);


    }
    private void setupGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Awareness.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();

                } else {
                    Timber.e("ERROR UPS");
                }
            }
        }
    }


    public void updateGps(double latitude,double longitude){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getLocation();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposableGpsEvent != null){
            Timber.e("disposableGpsEvent != null");
            if (!disposableGpsEvent.isDisposed()) {
                Timber.e("!disposableGpsEvent.isDisposed()");

                disposableGpsEvent.dispose();
            }
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            Awareness.SnapshotApi.getLocation(mGoogleApiClient)
                    .setResultCallback(new ResultCallback<LocationResult>() {
                        @Override
                        public void onResult(@NonNull LocationResult locationResult) {

                            if (locationResult.getStatus().isSuccess()) {
                                Location location = locationResult.getLocation();
                                currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                                Timber.e("onREsult : "+new Gson().toJson(currentLatLng));
                                locateMe();

                            } else {

                                Timber.e("ERROR : MAP google client null");
                            }
                        }
                    });
        }
    }



    private BitmapDescriptor getBitmapDescriptor(int id) {
        Drawable vectorDrawable = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            vectorDrawable = getContext().getDrawable(id);
        }else{
            vectorDrawable = ContextCompat.getDrawable(getContext(), id);
        }

        int h = ((int) vectorDrawable.getIntrinsicHeight());
        int w = ((int) vectorDrawable.getIntrinsicWidth());
        vectorDrawable.setBounds(0, 0, w, h);
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bm);
    }

   /* private void destination() {
        if (getActivity() == null)
            return;

        if (mMap == null)
            return;


        mMap.addMarker(new MarkerOptions()
                .position(destionationLatLng)
                .icon(getBitmapDescriptor(R.drawable.ic_map_marker_1))
                .title("Your Destination"));

        if (currentLatLng!= null && destionationLatLng != null){
            getDirection();
        }

    }*/

    private void locateMe(){
        if (getActivity() == null)
            return;

        if (mMap == null)
            return;



        if (mCurrentCircle != null)
            mCurrentCircle.remove();

        if (mCurrentMarker != null)
            mCurrentMarker.remove();

        mCurrentMarker = mMap.addMarker(new MarkerOptions()
                .position(currentLatLng)
                .icon(getBitmapDescriptor(R.drawable.ic_map_marker))
                .title("Your Location"));

        mCurrentCircle = mMap.addCircle(new CircleOptions()
                .center(currentLatLng)
                .radius(600)
                .strokeColor(ContextCompat.getColor(getContext(),R.color.primaryColor))
                .strokeWidth(1f)
                .fillColor(ContextCompat.getColor(getContext(),R.color.colorCircleMapTransparent)));
       /* if (currentLatLng != null) {
            Timber.e("current latlng not null");
            try {
                knownName = Utils.getInstance().setLocation(getContext(), currentLatLng);
                binding.textViewAddress.setText("Destination : \n" + knownName + "\n\n");
            } catch (IOException e) {
                Timber.e(e.getMessage());
            }
        }*/
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));

    }

    public void locatePlaces(DataModel dataModel){
        Timber.e("locatPlaces");


        if (dataModel != null){
            List<KursusTbl> kursusTbls = dataModel.getKursusTbls();

            if (kursusTbls.size() > 0){
                Timber.e("kursusTbls.size() : "+kursusTbls.size());
                for (KursusTbl kursusTbl: kursusTbls){
                    mCurrentMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(kursusTbl.getLatitude(),kursusTbl.getLongitude()))
                            .icon(getBitmapDescriptor(R.drawable.ic_map_marker))
                            .title(kursusTbl.getNamaLembaga()));

                }
            }

            List<SekolahTbl> sekolahTbls = dataModel.getSekolahTbls();

            if (sekolahTbls.size() > 0){
                Timber.e("sekolahTbls.size() : "+sekolahTbls.size());
                for (SekolahTbl sekolahTbl: sekolahTbls){
                    mCurrentMarker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(sekolahTbl.getLatitude(),sekolahTbl.getLongitude()))
                            .icon(getBitmapDescriptor(R.drawable.ic_college_graduation))
                            .title(sekolahTbl.getNamaSekolah()));

                }
            }
        }


    }


    private void setRouteDistanceAndDuration(String distance, String duration){

        binding.textViewAddress.append("Distance : "+distance+"\n");
        binding.textViewAddress.append("Duration : "+duration+"\n");
    }
    private List<LatLng> getDirectionPolylines(List<RouteObject> routes){
        List<LatLng> directionList = new ArrayList<LatLng>();
        for(RouteObject route : routes){
            List<LegsObject> legs = route.getLegs();
            for(LegsObject leg : legs){
                String routeDistance = leg.getDistance().getText();
                String routeDuration = leg.getDuration().getText();
                setRouteDistanceAndDuration(routeDistance, routeDuration);
                List<StepsObject> steps = leg.getSteps();
                for(StepsObject step : steps){
                    PolylineObject polyline = step.getPolyline();
                    String points = polyline.getPoints();
                    List<LatLng> singlePolyline = decodePoly(points);
                    for (LatLng direction : singlePolyline){
                        directionList.add(direction);
                    }
                }
            }
        }
        return directionList;
    }

    private void drawRouteOnMap(GoogleMap map, List<LatLng> positions){
        PolylineOptions options = new PolylineOptions().width(5).color(Color.RED).geodesic(true);
        options.addAll(positions);
        Polyline polyline = map.addPolyline(options);
    }
    /**
     * Method to decode polyline points
     * Courtesy : http://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
     * */
    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;
        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;
            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;
            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }

    private void getDirection(){
        //use Google Direction API to get the route between these Locations
        String directionApiPath = Helper.getUrl(String.valueOf(currentLatLng.latitude), String.valueOf(currentLatLng.longitude),
                String.valueOf(destionationLatLng.latitude), String.valueOf(destionationLatLng.longitude));

        Timber.e("directionApiPath : "+directionApiPath);


        AndroidNetworking.get(directionApiPath)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(DirectionObject.class,new ParsedRequestListener<DirectionObject>() {
                    @Override
                    public void onResponse(DirectionObject response) {
                        // do anything with response
                        Timber.e("RESPONSE : "+new Gson().toJson(response));
                        List<LatLng> mDirections = getDirectionPolylines(response.getRoutes());
                        drawRouteOnMap(mMap, mDirections);

                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Timber.e("ERROR : "+error.getMessage());

                    }
                });
    }
    public int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }



    public interface LocationDialogListener {

        void onGetLocation(LatLng latLng, String knownAddress);
        void onCancel();
    }


    private DataModel getDataModel(){

        DataModel dataModel = new DataModel();
        dataModel.setIpksTbls(Facade.getInstance().getManageIpksTbl().getAll());
        dataModel.setKursusTbls(Facade.getInstance().getManageKursusTbl().getAll());
        dataModel.setPuskesmasTbls(Facade.getInstance().getManagePuskesmasTbl().getAll());
        dataModel.setSekolahTbls(Facade.getInstance().getManageSekolahTbl().getAll());
        dataModel.setRsKhususTbls(Facade.getInstance().getManageRsKhususTbl().getAll());
        dataModel.setRsUmumTbls(Facade.getInstance().getManageRsUmumTbl().getAll());
        dataModel.setRptraTbls(Facade.getInstance().getManageRptraTbl().getAll());
        dataModel.setRawanBencanaTbls(Facade.getInstance().getManageRawanBencanaTbl().getAll());






        return dataModel;
    }

}
