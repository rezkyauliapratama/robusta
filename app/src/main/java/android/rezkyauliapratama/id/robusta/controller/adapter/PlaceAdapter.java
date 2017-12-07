package android.rezkyauliapratama.id.robusta.controller.adapter;

import android.rezkyauliapratama.id.robusta.database.entity.KursusTbl;
import android.rezkyauliapratama.id.robusta.databinding.ItemPlaceBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    private List<KursusTbl> mKursusTableList;

    public PlaceAdapter(List<KursusTbl> kursusTableList) {
        this.mKursusTableList = kursusTableList;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPlaceBinding itemPlaceBinding = ItemPlaceBinding.inflate(layoutInflater, parent, false);
        return new PlaceViewHolder(itemPlaceBinding);
    }

    @Override
    public void onBindViewHolder(final PlaceViewHolder holder, final int position) {
        final KursusTbl data = mKursusTableList.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return mKursusTableList == null ? 0 : mKursusTableList.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {

        private final ItemPlaceBinding mBinding;

        PlaceViewHolder(ItemPlaceBinding itemRedeemBinding) {
            super(itemRedeemBinding.getRoot());
            this.mBinding = itemRedeemBinding;
        }

        void bind(KursusTbl data) {
            mBinding.setKursusEntity(data);
            mBinding.executePendingBindings();
        }
    }
}

