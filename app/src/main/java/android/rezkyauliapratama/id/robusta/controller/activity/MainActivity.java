package android.rezkyauliapratama.id.robusta.controller.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.rezkyauliapratama.id.robusta.R;
import android.rezkyauliapratama.id.robusta.controller.fragment.BaseFragment;
import android.rezkyauliapratama.id.robusta.controller.fragment.DataFragment;
import android.rezkyauliapratama.id.robusta.controller.fragment.MapFragment;
import android.rezkyauliapratama.id.robusta.controller.service.GpsService;
import android.rezkyauliapratama.id.robusta.database.entity.SekolahTbl;
import android.rezkyauliapratama.id.robusta.databinding.ActivityMainBinding;
import android.rezkyauliapratama.id.robusta.model.DataModel;
import android.rezkyauliapratama.id.robusta.observer.RxBus;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MapFragment.LocationDialogListener {

    private ActivityMainBinding binding;
    private List<BaseFragment> fragments = new ArrayList<>();
    private BaseFragment fragment;
    private Intent intent;
    private LatLng location;
    private Disposable disposableGpsEvent;
    private DataModel mDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //make sure it is called first, so the data will be ready before used by viewpager's fragments
        mDataModel = DataModel.getOrInitDataModel(this);

        intent = new Intent(this, GpsService.class);

        initActionBar();
        initTab();
        initViewPager();

        binding.textviewTitle.setText("Peta");

        disposableGpsEvent = RxBus.getInstance().observable(LatLng.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {
                    Timber.e("RXBUS : " + new Gson().toJson(events));
                    ((MapFragment) fragments.get(0)).moveCamera(events);
                    binding.content.viewPager.setCurrentItem(0);
                });
    }

    private void initActionBar() {
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle("");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
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

    private void initTab() {
        TabLayout.Tab[] tabs = {
                binding.content.tabLayout.newTab().setIcon(android.R.drawable.ic_dialog_map),
                binding.content.tabLayout.newTab().setIcon(R.drawable.ic_open_book),

        };

        int i = 0;
        for (TabLayout.Tab tab : tabs) {
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(Gravity.CENTER);
            ImageView newTab = (ImageView) LayoutInflater
                    .from(this)
                    .inflate(android.support.design.R.layout.design_layout_tab_icon, null, false);

            newTab.setImageDrawable(tab.getIcon());
            layout.addView(newTab);

            tab.setCustomView(layout);
            binding.content.tabLayout.addTab(tab);

            if (i == 1) {
                int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            i++;
        }


        binding.content.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragment = fragments.get(tab.getPosition());

                binding.content.viewPager.setCurrentItem(tab.getPosition());
                int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.colorWhite);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

                if (fragment instanceof MapFragment) {
                    binding.textviewTitle.setText("Peta");
                } else {
                    binding.textviewTitle.setText("Daftar kursus");
                }
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

    private void initViewPager() {
        fragments.add(MapFragment.newInstance(0, 0));
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
        Timber.e("onMapReady : " + b);
        if (b) {

        } else {
            Timber.e("ONMAP READY FOR START SERVICES");
            if (location != null && !isSync) {
                Timber.e("location != null");
                if (location.latitude != 0 && location.longitude != 0)
                    new AsyncTaskRunner().execute(new LatLng(location.latitude, location.longitude));
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
        protected DataModel doInBackground(LatLng... latLngs) {
            Timber.e("latlang asyntask: " + new Gson().toJson(latLngs));
            LatLng latLng = latLngs[0];
            if (latLng.longitude != 0 && latLng.latitude != 0) {
                List<SekolahTbl> schoolList = new ArrayList<>();
                for (SekolahTbl item : mDataModel.getSekolahTbls()) {
                    float[] distance = new float[3];
                    Location.distanceBetween(latLng.latitude, latLng.longitude, item.getLatitude(), item.getLongitude(), distance);
                    if (distance[0] <= 2000) {
                        Timber.e("new sekolah : " + new Gson().toJson(item));

                        schoolList.add(item);
                    }
                }

                mDataModel.setSekolahTbls(schoolList);
            }
            return mDataModel;
        }

        @Override
        protected void onPostExecute(DataModel dataModel) {
            super.onPostExecute(dataModel);
            binding.lottie.cancelAnimation();
            binding.layoutProgress.setVisibility(View.GONE);

            if (dataModel != null) {
                Timber.e("onPostExecute tidak null");
                ((MapFragment) fragments.get(0)).locatePlaces(dataModel);
            }
        }
    }
}
