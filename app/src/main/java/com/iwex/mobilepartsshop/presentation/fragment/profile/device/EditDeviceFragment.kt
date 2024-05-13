package com.iwex.mobilepartsshop.presentation.fragment.profile.device

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshop.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshop.presentation.utils.LocalizationHelper
import com.iwex.mobilepartsshop.presentation.viewmodel.profile.device.EditDeviceViewModel

class EditDeviceFragment : Fragment() {

    private val viewModel: EditDeviceViewModel by viewModels()

    private lateinit var textViewManufacturerLabel: TextView
    private lateinit var spinnerManufacturer: Spinner
    private lateinit var textViewDeviceTypeLabel: TextView
    private lateinit var spinnerDeviceType: Spinner
    private lateinit var tilModel: TextInputLayout
    private lateinit var editTextModel: TextInputEditText
    private lateinit var tilSpecifications: TextInputLayout
    private lateinit var editTextSpecifications: TextInputEditText
    private lateinit var btnSaveDevice: Button
    private lateinit var btnCancel: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_edit_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setClickListeners()
        observeViewModel()
    }

    private fun initViews(view: View) {
        textViewManufacturerLabel = view.findViewById(R.id.textViewManufacturerLabel)
        spinnerManufacturer = view.findViewById(R.id.spinnerManufacturer)
        textViewDeviceTypeLabel = view.findViewById(R.id.textViewDeviceTypeLabel)
        spinnerDeviceType = view.findViewById(R.id.spinnerDeviceType)
        tilModel = view.findViewById(R.id.tilModel)
        editTextModel = view.findViewById(R.id.editTextModel)
        tilSpecifications = view.findViewById(R.id.tilSpecifications)
        editTextSpecifications = view.findViewById(R.id.editTextSpecifications)
        btnSaveDevice = view.findViewById(R.id.btnSaveDevice)
        btnCancel = view.findViewById(R.id.btnCancel)
        progressBar = view.findViewById(R.id.progressBarEditDeviceFragment)
    }

    private fun setClickListeners() {
        btnSaveDevice.setOnClickListener {
            editDevice()
        }
        btnCancel.setOnClickListener {
            navigateToProfileFragment()
        }
    }

    private fun editDevice() {
        clearErrors()
        //TODO
    }

    private fun clearErrors() {
        tilModel.error = null
        tilSpecifications.error = null
    }

    private fun observeViewModel() {
        viewModel.manufacturers.observe(viewLifecycleOwner) {
            setupManufacturersSpinner(it)
        }
        viewModel.deviceTypes.observe(viewLifecycleOwner) {
            setupDeviceTypesSpinner(it)
        }
        viewModel.formState.observe(viewLifecycleOwner) { state ->
            if (!state.isDataValid) {
                state.manufacturerError?.let { showError(it) }
                state.deviceTypeError?.let { showError(it) }
                state.modelError?.let { showError(it, tilModel) }
                state.specificationsError?.let { showError(it, tilSpecifications) }
            }
        }
        viewModel.onSuccess.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.saved, Toast.LENGTH_SHORT).show()
            navigateToProfileFragment()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            switchProgressBarVisibility(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupManufacturersSpinner(manufacturers: List<Manufacturer>) {
        spinnerManufacturer.adapter = ManufacturerSpinnerAdapter(requireContext(), manufacturers)
    }

    private fun setupDeviceTypesSpinner(deviceTypes: List<DeviceType>) {
        spinnerDeviceType.adapter = DeviceTypeSpinnerAdapter(
            requireContext(),
            deviceTypes,
            LocalizationHelper.isUkrainianLocale(resources)
        )
    }

    private fun showError(stringId: Int, textInputLayout: TextInputLayout? = null) {
        if (textInputLayout == null) {
            Toast.makeText(requireContext(), stringId, Toast.LENGTH_LONG).show()
            return
        }
        textInputLayout.error = getString(stringId)
    }

    private fun navigateToProfileFragment() {
        findNavController().navigate(R.id.action_editDeviceFragment_to_profileFragment)
    }

    private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}