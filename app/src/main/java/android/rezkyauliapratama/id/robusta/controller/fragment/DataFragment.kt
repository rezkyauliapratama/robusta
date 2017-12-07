package android.rezkyauliapratama.id.robusta.controller.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.rezkyauliapratama.id.robusta.R
import android.rezkyauliapratama.id.robusta.controller.adapter.PlaceAdapter
import android.rezkyauliapratama.id.robusta.database.Facade
import android.rezkyauliapratama.id.robusta.databinding.FragmentDataBinding
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author hendrawd on 12/7/17
 */

class DataFragment : BaseFragment() {

    private lateinit var mBinding: FragmentDataBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_data, container, false)
        initRecyclerView()
        return mBinding.root
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        mBinding.recyclerView.layoutManager = layoutManager
        mBinding.recyclerView.adapter = PlaceAdapter(Facade.getInstance().manageKursusTbl.all)
    }
}
