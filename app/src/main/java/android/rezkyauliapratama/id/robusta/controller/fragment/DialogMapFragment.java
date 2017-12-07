package android.rezkyauliapratama.id.robusta.controller.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.rezkyauliapratama.id.robusta.R;
import android.rezkyauliapratama.id.robusta.databinding.DialogMapBinding;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;

import io.reactivex.disposables.Disposable;

/**
 * Created by Rezky Aulia Pratama on 12/7/2017.
 */

public class DialogMapFragment  extends DialogFragment {
    public final static String Dialog= "DIALOG";
    public final static int TARGET = 1;
    public final static String ARG1 = "ARGS1";
    public final static String ARG2 = "ARGS2";
    public final static String ARG3 = "ARGS3";

    DialogMapBinding binding;

    private DialogListener mListener;

    private String title;
    private String desc;
    private LatLng latLng;

    private boolean isSave;

    public static DialogMapFragment newInstance(String title, String description, LatLng latLng){
        DialogMapFragment dialogFragment = new DialogMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG1, title);
        args.putString(ARG2, description);
        args.putParcelable(ARG3, latLng);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG1);
            desc = getArguments().getString(ARG2);
            latLng = getArguments().getParcelable(ARG3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding =  DataBindingUtil.inflate(inflater, R.layout.dialog_map,container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListener = (DialogListener) getTargetFragment();


        binding.textviewTitle.setText(title);
        binding.textviewDetail.setText(desc);
        binding.buttonDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSetRoute(latLng);
                dismiss();
            }
        });
    }


    public interface DialogListener {
        void onSetRoute(LatLng latLng);
    }
}
