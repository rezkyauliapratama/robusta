package android.rezkyauliapratama.id.robusta.controller.fragment;

import android.os.Bundle;
import android.rezkyauliapratama.id.robusta.R;
import android.rezkyauliapratama.id.robusta.utility.PreferencesManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.List;

/**
 * Created by Rezky Aulia Pratama on 12/6/2017.
 */

public class BaseFragment  extends Fragment {


    private static final String TAG = BaseFragment.class.getSimpleName();

    protected PreferencesManager pref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = PreferencesManager.getInstance();


    }



    protected void displayFragment(int id, Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .replace(id, fragment).commitAllowingStateLoss();
    }

    protected void addFragment(int id, Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .add(id, fragment).commitAllowingStateLoss();
    }
    protected void removeFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .remove(fragment).commitAllowingStateLoss();
    }

    protected List<Fragment> getFragments() {
        return getChildFragmentManager().getFragments();
    }



    @Override
    public void onResume() {
        super.onResume();

    }
    protected int getColorPrimary() {
        return ContextCompat.getColor(getContext(), R.color.colorPrimary);
    }

    public void update() {

    }
}
