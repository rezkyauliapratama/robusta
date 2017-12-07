package android.rezkyauliapratama.id.robusta.controller.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.rezkyauliapratama.id.robusta.R
import android.rezkyauliapratama.id.robusta.controller.adapter.PlaceAdapter
import android.rezkyauliapratama.id.robusta.database.Facade
import android.rezkyauliapratama.id.robusta.database.entity.KursusTbl
import android.rezkyauliapratama.id.robusta.databinding.FragmentDataBinding
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter


/**
 * @author hendrawd on 12/7/17
 */

class DataFragment : BaseFragment(), OnItemSelectedListener {

    private lateinit var mBinding: FragmentDataBinding
    private lateinit var mPlaceList: List<KursusTbl>
    private var mCategories: MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_data, container, false)
        mPlaceList = Facade.getInstance().manageKursusTbl.all
        initSpinner()
        initRecyclerView()
        return mBinding.root
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val selectedItem = parent.getItemAtPosition(position).toString()
        updateRecyclerViewContent(selectedItem)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }

    private fun initSpinner() {
        for (place in mPlaceList) {
            if (!mCategories.contains(place.jenisKursus)) {
                mCategories.add(place.jenisKursus)
            }
        }
        val adapter = ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, mCategories
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mBinding.spinner.adapter = adapter
        mBinding.spinner.onItemSelectedListener = this
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        mBinding.recyclerView.layoutManager = layoutManager
        updateRecyclerViewContent(mCategories[0])
    }

    private fun updateRecyclerViewContent(jenisKursus: String) {
        mBinding.recyclerView.adapter = PlaceAdapter(
                Facade.getInstance().manageKursusTbl.getKursusByJenisKursus(jenisKursus)
        )
    }
}
