package com.iwex.mobilepartsshop.presentation.fragment.parts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.part.Part
import com.iwex.mobilepartsshop.presentation.utils.LocalizationHelper
import com.iwex.mobilepartsshop.presentation.viewmodel.parts.PartsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartsFragment : Fragment() {

    private val args by navArgs<PartsFragmentArgs>()

    private val viewModel: PartsViewModel by viewModels()

    private lateinit var partsListAdapter: PartsListAdapter
    private lateinit var recyclerViewParts: RecyclerView
    private lateinit var btnBack: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_parts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupRecyclerView()
        setClickListeners()
        observeViewModel()
    }

    private fun initViews(view: View) {
        recyclerViewParts = view.findViewById(R.id.recyclerViewParts)
        btnBack = view.findViewById(R.id.btnBack)
        progressBar = view.findViewById(R.id.progressBarPartsFragment)
    }

    private fun setupRecyclerView() {
        partsListAdapter = PartsListAdapter(LocalizationHelper.isUkrainianLocale(resources))
        partsListAdapter.onPartClickListener = {
            navigateToPartDetailsFragment(it)
        }
        recyclerViewParts.adapter = partsListAdapter
    }

    private fun navigateToPartDetailsFragment(part: Part) {
        findNavController().navigate(
            PartsFragmentDirections.actionPartsFragmentToPartDetailsFragment(part)
        )
    }

    private fun setClickListeners() {
        btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_partsFragment_to_searchPartsFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.parts.observe(viewLifecycleOwner) {
            partsListAdapter.submitList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            switchProgressBarVisibility(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.loadParts(
            recommendationByCriteriaRequest = args.recommendationByCriteriaRequest,
            recommendationByDeviceRequest = args.recommendationByDeviceRequest
        )
    }

    private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}