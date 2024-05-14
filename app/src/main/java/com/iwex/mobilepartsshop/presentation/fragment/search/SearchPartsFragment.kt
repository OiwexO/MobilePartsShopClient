package com.iwex.mobilepartsshop.presentation.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshop.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshop.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshop.domain.entity.recommendation.RecommendationByCriteriaRequest
import com.iwex.mobilepartsshop.domain.entity.recommendation.RecommendationByDeviceRequest
import com.iwex.mobilepartsshop.presentation.common.adapter.DeviceTypeSpinnerAdapter
import com.iwex.mobilepartsshop.presentation.common.adapter.ManufacturerSpinnerAdapter
import com.iwex.mobilepartsshop.presentation.common.adapter.PartTypeSpinnerAdapter
import com.iwex.mobilepartsshop.presentation.utils.LocalizationHelper
import com.iwex.mobilepartsshop.presentation.viewmodel.search.SearchPartsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchPartsFragment : Fragment() {

    private val viewModel: SearchPartsViewModel by viewModels()

    private var deviceId: Long = NO_DEVICE_ID

    private var manufacturerSpinnerAdapter: ManufacturerSpinnerAdapter? = null
    private var deviceTypeSpinnerAdapter: DeviceTypeSpinnerAdapter? = null
    private var partTypeSpinnerAdapter: PartTypeSpinnerAdapter? = null
    private lateinit var btnViewAllParts: Button
    private lateinit var btnSearchByCriteria: Button
    private lateinit var layoutSearchByCriteria: LinearLayout
    private lateinit var textViewManufacturerLabel: TextView
    private lateinit var spinnerManufacturer: Spinner
    private lateinit var textViewDeviceTypeLabel: TextView
    private lateinit var spinnerDeviceType: Spinner
    private lateinit var textViewPartTypeLabel: TextView
    private lateinit var spinnerPartType: Spinner
    private lateinit var checkboxSearchByCriteriaSortByPrice: CheckBox
    private lateinit var btnSearchByCriteriaConfirm: Button
    private lateinit var layoutSearchByDevice: LinearLayout
    private lateinit var btnSearchByDevice: Button
    private lateinit var textViewSaveDeviceFirst: TextView
    private lateinit var checkboxSearchByDeviceSortByPrice: CheckBox
    private lateinit var btnSearchByDeviceConfirm: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search_parts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setClickListeners()
        observeViewModel()
    }

    private fun initViews(view: View) {
        btnViewAllParts = view.findViewById(R.id.btnViewAllParts)
        btnSearchByCriteria = view.findViewById(R.id.btnSearchByCriteria)
        layoutSearchByCriteria = view.findViewById(R.id.layoutSearchByCriteria)
        textViewManufacturerLabel = view.findViewById(R.id.textViewManufacturerLabel)
        spinnerManufacturer = view.findViewById(R.id.spinnerManufacturer)
        textViewDeviceTypeLabel = view.findViewById(R.id.textViewDeviceTypeLabel)
        spinnerDeviceType = view.findViewById(R.id.spinnerDeviceType)
        textViewPartTypeLabel = view.findViewById(R.id.textViewPartTypeLabel)
        spinnerPartType = view.findViewById(R.id.spinnerPartType)
        checkboxSearchByCriteriaSortByPrice =
            view.findViewById(R.id.checkboxSearchByCriteriaSortByPrice)
        btnSearchByCriteriaConfirm = view.findViewById(R.id.btnSearchByCriteriaConfirm)
        layoutSearchByDevice = view.findViewById(R.id.layoutSearchByDevice)
        btnSearchByDevice = view.findViewById(R.id.btnSearchByDevice)
        textViewSaveDeviceFirst = view.findViewById(R.id.textViewSaveDeviceFirst)
        checkboxSearchByDeviceSortByPrice =
            view.findViewById(R.id.checkboxSearchByDeviceSortByPrice)
        btnSearchByDeviceConfirm = view.findViewById(R.id.btnSearchByDeviceConfirm)
    }

    private fun setClickListeners() {
        btnViewAllParts.setOnClickListener {
            viewAllParts()
        }
        btnSearchByCriteria.setOnClickListener {
            setSearchByCriteriaLayoutVisible()
        }
        btnSearchByCriteriaConfirm.setOnClickListener {
            searchByCriteria()
        }
        btnSearchByDevice.setOnClickListener {
            setSearchByDeviceLayoutVisible()
        }
        btnSearchByDeviceConfirm.setOnClickListener {
            searchByDevice()
        }
    }

    private fun viewAllParts() {
        navigateToPartsFragment()
    }

    private fun searchByCriteria() {
        val manufacturerId = (spinnerManufacturer.selectedItem as? Manufacturer)?.id ?: 0
        val deviceTypeId = (spinnerDeviceType.selectedItem as? DeviceType)?.id ?: 0
        val partTypeId = (spinnerPartType.selectedItem as? PartType)?.id ?: 0
        val sortAscending = checkboxSearchByCriteriaSortByPrice.isEnabled
        val recommendationByCriteriaRequest = RecommendationByCriteriaRequest(
            manufacturerId = manufacturerId,
            deviceTypeId = deviceTypeId,
            partTypeId = partTypeId,
            sortAscending = sortAscending
        )
        navigateToPartsFragment(recommendationByCriteriaRequest = recommendationByCriteriaRequest)
    }

    private fun searchByDevice() {
        val recommendationByDeviceRequest = RecommendationByDeviceRequest(
            deviceId = deviceId,
            sortAscending = checkboxSearchByDeviceSortByPrice.isEnabled
        )
        navigateToPartsFragment(recommendationByDeviceRequest = recommendationByDeviceRequest)
    }

    private fun navigateToPartsFragment(
        recommendationByCriteriaRequest: RecommendationByCriteriaRequest? = null,
        recommendationByDeviceRequest: RecommendationByDeviceRequest? = null
    ) {
        findNavController().navigate(
            SearchPartsFragmentDirections.actionSearchPartsFragmentToPartsFragment(
                recommendationByCriteriaRequest = recommendationByCriteriaRequest,
                recommendationByDeviceRequest = recommendationByDeviceRequest
            )
        )
    }

    private fun setSearchByCriteriaLayoutVisible() {
        layoutSearchByCriteria.visibility = View.VISIBLE
        layoutSearchByDevice.visibility = View.GONE
    }

    private fun setSearchByDeviceLayoutVisible() {
        layoutSearchByCriteria.visibility = View.GONE
        layoutSearchByDevice.visibility = View.VISIBLE
    }

    private fun observeViewModel() {
        viewModel.deviceTypes.observe(viewLifecycleOwner) {
            setupDeviceTypesSpinner(it)
        }
        viewModel.manufacturers.observe(viewLifecycleOwner) {
            setupManufacturersSpinner(it)
        }
        viewModel.partTypes.observe(viewLifecycleOwner) {
            setupPartTypesSpinner(it)
        }
        viewModel.deviceId.observe(viewLifecycleOwner) {
            onDeviceIdReceived(it)
        }
        /*viewModel.isLoading.observe(viewLifecycleOwner) {
            switchProgressBarVisibility(it)
        }*/
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.loadData()
    }

    private fun setupManufacturersSpinner(manufacturers: List<Manufacturer>) {
        manufacturerSpinnerAdapter = ManufacturerSpinnerAdapter(
            context = requireContext(),
            manufacturers = manufacturers,
            showAnyOption = true
        )
        spinnerManufacturer.adapter = manufacturerSpinnerAdapter
    }

    private fun setupDeviceTypesSpinner(deviceTypes: List<DeviceType>) {
        deviceTypeSpinnerAdapter = DeviceTypeSpinnerAdapter(
            context = requireContext(),
            deviceTypes = deviceTypes,
            isUkrainianLocale = LocalizationHelper.isUkrainianLocale(resources),
            showAnyOption = true
        )
        spinnerDeviceType.adapter = deviceTypeSpinnerAdapter
    }

    private fun setupPartTypesSpinner(partTypes: List<PartType>) {
        partTypeSpinnerAdapter = PartTypeSpinnerAdapter(
            context = requireContext(),
            partTypes = partTypes,
            isUkrainianLocale = LocalizationHelper.isUkrainianLocale(resources),
            showAnyOption = true
        )
        spinnerPartType.adapter = partTypeSpinnerAdapter
    }

    private fun onDeviceIdReceived(id: Long) {
        deviceId = id
        if (deviceId == NO_DEVICE_ID) {
            btnSearchByDevice.isEnabled = false
            textViewSaveDeviceFirst.visibility = View.VISIBLE
        } else {
            btnSearchByDevice.isEnabled = true
            textViewSaveDeviceFirst.visibility = View.GONE
        }
    }

    /*private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }*/

    companion object {

        private const val NO_DEVICE_ID = 0L
    }
}
