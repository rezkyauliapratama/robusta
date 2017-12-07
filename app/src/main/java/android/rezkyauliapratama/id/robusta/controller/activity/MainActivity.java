package android.rezkyauliapratama.id.robusta.controller.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.location.Location;
import android.os.AsyncTask;
import android.rezkyauliapratama.id.robusta.R;
import android.rezkyauliapratama.id.robusta.controller.fragment.BaseFragment;
import android.rezkyauliapratama.id.robusta.controller.fragment.DataFragment;
import android.rezkyauliapratama.id.robusta.controller.fragment.MapFragment;
import android.rezkyauliapratama.id.robusta.controller.service.GPSTracker;
import android.rezkyauliapratama.id.robusta.controller.service.GpsService;
import android.rezkyauliapratama.id.robusta.database.Facade;
import android.rezkyauliapratama.id.robusta.database.entity.IpksTbl;
import android.rezkyauliapratama.id.robusta.database.entity.KursusTbl;
import android.rezkyauliapratama.id.robusta.database.entity.PuskesmasTbl;
import android.rezkyauliapratama.id.robusta.database.entity.RawanBencanaTbl;
import android.rezkyauliapratama.id.robusta.database.entity.RptraTbl;
import android.rezkyauliapratama.id.robusta.database.entity.RsKhususTbl;
import android.rezkyauliapratama.id.robusta.database.entity.RsUmumTbl;
import android.rezkyauliapratama.id.robusta.database.entity.SekolahTbl;
import android.rezkyauliapratama.id.robusta.databinding.ActivityMainBinding;
import android.rezkyauliapratama.id.robusta.model.DataModel;
import android.rezkyauliapratama.id.robusta.model.api.Puskesmas;
import android.rezkyauliapratama.id.robusta.model.api.RPTRA;
import android.rezkyauliapratama.id.robusta.model.api.RawanBencana;
import android.rezkyauliapratama.id.robusta.model.api.RsKhusus;
import android.rezkyauliapratama.id.robusta.model.api.RsUmum;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class MainActivity extends BaseActivity implements MapFragment.LocationDialogListener {

    ActivityMainBinding binding;
    List<BaseFragment> fragments;
    BaseFragment fragment;
    Intent intent;

    LatLng location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fragments = new ArrayList<>();

        intent = new Intent(this,GpsService.class);

        initTab();
        initViewPager();


    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(intent);
        Timber.e("ONSTOP");

    }


    @Override
    public void onStart() {
        super.onStart();
        startService(intent);
        Timber.e("ONSTART");
        binding.layoutProgress.setVisibility(View.VISIBLE);
        binding.lottie.playAnimation();
    }

    private void initTab(){
        TabLayout.Tab[] tabs = {
                binding.content.tabLayout.newTab().setIcon(android.R.drawable.ic_menu_compass),
                binding.content.tabLayout.newTab().setIcon(android.R.drawable.ic_menu_info_details),

        };

        for (TabLayout.Tab tab : tabs) {
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(Gravity.CENTER);
            ImageView newTab = (ImageView) LayoutInflater
                    .from(this)
                    .inflate(android.support.design.R.layout.design_layout_tab_icon, null,false);

            newTab.setImageDrawable(tab.getIcon());
            layout.addView(newTab);

            tab.setCustomView(layout);
            binding.content.tabLayout.addTab(tab);

        }

        binding.content.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragment = fragments.get(tab.getPosition());

                binding.content.viewPager.setCurrentItem(tab.getPosition());
                int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.colorWhite);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initViewPager(){
        fragments.add(MapFragment.newInstance(0,0));
        fragments.add(new DataFragment());
        /*fragments.add(ScheduleFragment.newInstance());
        fragments.add(new BaseFragment());*/
//        fragments.add(GuideFragment.newInstance());

        fragment = fragments.get(0);

        binding.content.viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return binding.content.tabLayout.getTabCount();
            }
        });

//        binding.content.viewPager.setPagingEnabled(false);
        binding.content.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.content.tabLayout));
    }

    @Override
    public void onMapReady(boolean b) {
        Timber.e("onMapReady : "+b);
        if (b){

        }else{
            Timber.e("ONMAP READY FOR START SERVICES");
            if (location != null && !isSync){
                Timber.e("location != null");
                if (location.latitude != 0&& location.longitude != 0)
                    new AsyncTaskRunner().execute(new LatLng(location.latitude,location.longitude));
            }
        }

    }

    boolean isSync = false;

    @Override
    public void onUpdateLatlng(LatLng latLng) {
        Timber.e("oNUpdateLatlng");
        if (location == null) {
            location = latLng;
            if (location != null) {
                Timber.e("location != null");
                if (location.latitude != 0 && location.longitude != 0)
                    new AsyncTaskRunner().execute(new LatLng(location.latitude, location.longitude));

                isSync = true;
            }
        }

    }

    private class AsyncTaskRunner extends AsyncTask<LatLng, DataModel, DataModel> {

        @Override
        protected DataModel doInBackground(LatLng... voids) {

            Facade facade = Facade.getInstance();
            DataModel dataModel = null;
            if (facade.getManageSekolahTbl().getAll().size() > 0){
                Timber.e("AsyncTaskRunner != NULL");
                dataModel = getDataModel();
            }else{
                Timber.e("AsyncTaskRunner NULL");
                dataModel = initData();
            }

            Timber.e("latlang asyntask: "+new Gson().toJson(voids));
            LatLng latLng = voids[0];
            if (latLng.longitude != 0 && latLng.latitude != 0){
                List<SekolahTbl> tempList = new ArrayList<>();
                for(SekolahTbl item : dataModel.getSekolahTbls()){
                    float[] distance = new float[3];
                    Location.distanceBetween(latLng.latitude, latLng.longitude, item.getLatitude(), item.getLongitude(), distance);
                    if (distance[0] <= 2000) {
                        Timber.e("new sekolah : "+new Gson().toJson(item));

                        tempList.add(item);
                    }
                }

                dataModel.getSekolahTbls().clear();
                dataModel.setSekolahTbls(tempList);
            }

            return dataModel;
        }

        @Override
        protected void onPostExecute(DataModel dataModel) {
            super.onPostExecute(dataModel);
            binding.lottie.cancelAnimation();
            binding.layoutProgress.setVisibility(View.GONE);

            if (dataModel != null){
                Timber.e("onPostExecute tidak null");
                ((MapFragment)fragments.get(0)).locatePlaces(dataModel);
            }
        }
    }

    private DataModel initData(){
        Timber.e("inidata");
        InputStream in = getResources().openRawResource(R.raw.data_sekolah);
        Reader rd = new BufferedReader(new InputStreamReader(in));
        SekolahTbl[] arrSekolah = new Gson().fromJson(rd, SekolahTbl[].class);
        List<SekolahTbl> sekolahTbls = Arrays.asList(arrSekolah);

        in = getResources().openRawResource(R.raw.ipks);
        rd = new BufferedReader(new InputStreamReader(in));
        IpksTbl[] arrIpks = new Gson().fromJson(rd, IpksTbl[].class);
        List<IpksTbl> ipksTbls = Arrays.asList(arrIpks);

        in = getResources().openRawResource(R.raw.kursus);
        rd = new BufferedReader(new InputStreamReader(in));
        KursusTbl[] arrKursus = new Gson().fromJson(rd, KursusTbl[].class);
        List<KursusTbl> kursusTbls = Arrays.asList(arrKursus);


        in = getResources().openRawResource(R.raw.puskesmas);
        rd = new BufferedReader(new InputStreamReader(in));
        Puskesmas[] arrPuskesmas = new Gson().fromJson(rd, Puskesmas[].class);
        List<PuskesmasTbl> puskesmasTbls = new ArrayList<>();

        for (Puskesmas puskesmas : arrPuskesmas){
            /*PuskesmasTbl(Long id, String namaPuskesmas, Double latitude,
                    Double longitude, String telepon, String faximile, String email,
                    String kepalaPuskesmas, Integer kodeKota, Integer kodeKecamatan,
                    Integer kodeKelurahan)*/
            PuskesmasTbl puskesmasTbl = new PuskesmasTbl(Long.parseLong(String.valueOf(puskesmas.getId())),
                                                        puskesmas.getNamaPuskesmas(),puskesmas.getLocation().getLatitude(),puskesmas.getLocation().getLongitude()
                                                        ,(puskesmas.getTelepon().size() > 0 ) ? puskesmas.getTelepon().get(0) : "",(puskesmas.getFaximile().size() > 0) ? puskesmas.getFaximile().get(0) : "",puskesmas.getEmail(),puskesmas.getKepalaPuskesmas(),puskesmas.getKodeKota(),puskesmas.getKodeKecamatan()
                                                        ,puskesmas.getKodeKelurahan());

            if (puskesmasTbl != null){
                puskesmasTbls.add(puskesmasTbl);
            }
        }

        in = getResources().openRawResource(R.raw.rawan_bencana);
        rd = new BufferedReader(new InputStreamReader(in));
        RawanBencana[] arrRawanBencana = new Gson().fromJson(rd, RawanBencana[].class);
        List<RawanBencanaTbl> rawanBencanaTbls = new ArrayList<>();

        for (RawanBencana rawanBencana : arrRawanBencana){
            RawanBencanaTbl rawanBencanaTbl = new RawanBencanaTbl(Long.parseLong(String.valueOf(rawanBencana.getId())),rawanBencana.getBencana(),rawanBencana.getLokasi(),rawanBencana.getKecamatan(),
                                                                    rawanBencana.getKota(),rawanBencana.getLocation().getLatitude(),rawanBencana.getLocation().getLongitude());

            if (rawanBencanaTbl != null){
                rawanBencanaTbls.add(rawanBencanaTbl);
            }
        }

        in = getResources().openRawResource(R.raw.rptra);
        rd = new BufferedReader(new InputStreamReader(in));
        RPTRA[] arrRptra = new Gson().fromJson(rd, RPTRA[].class);
        List<RptraTbl> rptraTbls= new ArrayList<>();

        for (RPTRA rptra: arrRptra){
            RptraTbl rptraTbl = new RptraTbl(
                    Long.parseLong(String.valueOf(rptra.getId())),rptra.getNamaRptra(),rptra.getAlamat(),rptra.getKelurahan(),rptra.getKecamatan(),rptra.getLuas(),rptra.getWaktuPeresmian(),
                    rptra.getTelepon(),rptra.getEmail(),rptra.getFasilitas(),rptra.getPermasalahan(),rptra.getLocation().getLatitude(),rptra.getLocation().getLongitude()
            );

            if (rptraTbl != null){
                rptraTbls.add(rptraTbl);
            }
        }

        in = getResources().openRawResource(R.raw.rs_khusus);
        rd = new BufferedReader(new InputStreamReader(in));
        RsKhusus[] arrRsKhusus= new Gson().fromJson(rd, RsKhusus[].class);
        List<RsKhususTbl> rsKhususTbls= new ArrayList<>();

        for (RsKhusus rsKhusus: arrRsKhusus){
            RsKhususTbl rsKhususTbl= new RsKhususTbl(
                    Long.parseLong(String.valueOf(rsKhusus.getId())),rsKhusus.getNamaRsk(),rsKhusus.getJenisRsk(),rsKhusus.getKodePos(),rsKhusus.getTelepon().size()>0?rsKhusus.getTelepon().get(0):""
                    ,rsKhusus.getFaximile().size()>0?rsKhusus.getFaximile().get(0):"",
                    rsKhusus.getWebsite(),rsKhusus.getEmail(),rsKhusus.getKodeKota(),rsKhusus.getKodeKecamatan(),rsKhusus.getKodeKelurahan(),
                    rsKhusus.getLatitude(),rsKhusus.getLongitude()
            );

            if (rsKhususTbl!= null){
                rsKhususTbls.add(rsKhususTbl);
            }
        }


        in = getResources().openRawResource(R.raw.rs_khusus);
        rd = new BufferedReader(new InputStreamReader(in));
        RsUmum[] arrRsUmum= new Gson().fromJson(rd, RsUmum[].class);
        List<RsUmumTbl> rsUmumTbls= new ArrayList<>();

        for (RsUmum rsUmum: arrRsUmum){
            RsUmumTbl rsUmumTbl= new RsUmumTbl(
                    Long.parseLong(String.valueOf(rsUmum.getId())),rsUmum.getNamaRsu(),rsUmum.getJenisRsu(),rsUmum.getKodePos(),rsUmum.getTelepon().size()>0?rsUmum.getTelepon().get(0):""
                    ,rsUmum.getFaximile().size()>0?rsUmum.getFaximile().get(0):"",
                    rsUmum.getWebsite(),rsUmum.getEmail(),rsUmum.getKodeKota(),rsUmum.getKodeKecamatan(),rsUmum.getKodeKelurahan(),
                    rsUmum.getLatitude(),rsUmum.getLongitude()
            );

            if (rsUmumTbl!= null){
                rsUmumTbls.add(rsUmumTbl);
            }
        }

        DataModel dataModel = new DataModel();
        dataModel.setIpksTbls(ipksTbls);
        dataModel.setKursusTbls(kursusTbls);
        dataModel.setPuskesmasTbls(puskesmasTbls);
        dataModel.setSekolahTbls(sekolahTbls);
        dataModel.setRsKhususTbls(rsKhususTbls);
        dataModel.setRsUmumTbls(rsUmumTbls);
        dataModel.setRptraTbls(rptraTbls);
        dataModel.setRawanBencanaTbls(rawanBencanaTbls);

        Facade.getInstance().getManageIpksTbl().add(ipksTbls);
        Facade.getInstance().getManageRawanBencanaTbl().add(rawanBencanaTbls);
        Facade.getInstance().getManageKursusTbl().add(kursusTbls);
        Facade.getInstance().getManagePuskesmasTbl().add(puskesmasTbls);
        Facade.getInstance().getManageSekolahTbl().add(sekolahTbls);
        Facade.getInstance().getManageRsKhususTbl().add(rsKhususTbls);
        Facade.getInstance().getManageRsUmumTbl().add(rsUmumTbls);
        Facade.getInstance().getManageRptraTbl().add(rptraTbls);

        return dataModel;
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
