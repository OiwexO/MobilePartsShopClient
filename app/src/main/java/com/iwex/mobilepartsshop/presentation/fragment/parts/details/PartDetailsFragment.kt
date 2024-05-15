package com.iwex.mobilepartsshop.presentation.fragment.parts.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.part.Part
import com.iwex.mobilepartsshop.domain.entity.review.Review
import com.iwex.mobilepartsshop.presentation.utils.LocalizationHelper
import com.iwex.mobilepartsshop.presentation.viewmodel.parts.details.PartDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartDetailsFragment : Fragment() {

    private val args by navArgs<PartDetailsFragmentArgs>()

    private val viewModel: PartDetailsViewModel by viewModels()

    private val reviewsListAdapter = ReviewsListAdapter()
    private lateinit var imageViewPartImage: ImageView
    private lateinit var textViewPartName: TextView
    private lateinit var textViewSpecifications: TextView
    private lateinit var textViewPartType: TextView
    private lateinit var textViewManufacturer: TextView
    private lateinit var textViewDeviceType: TextView
    private lateinit var textViewModels: TextView
    private lateinit var textViewPrice: TextView
    private lateinit var textViewLabelReviews: TextView
    private lateinit var btnAddToCart: Button
    private lateinit var btnWriteReview: Button
    private lateinit var recyclerViewReviews: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_part_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupRecyclerView()
        setClickListeners()
        setPartData(args.part)
        observeViewModel()
    }

    private fun initViews(view: View) {
        imageViewPartImage = view.findViewById(R.id.imageViewPartImage)
        textViewPartName = view.findViewById(R.id.textViewPartName)
        textViewSpecifications = view.findViewById(R.id.textViewSpecifications)
        textViewPartType = view.findViewById(R.id.textViewPartType)
        textViewManufacturer = view.findViewById(R.id.textViewManufacturer)
        textViewDeviceType = view.findViewById(R.id.textViewDeviceType)
        textViewModels = view.findViewById(R.id.textViewModels)
        textViewPrice = view.findViewById(R.id.textViewPrice)
        textViewLabelReviews = view.findViewById(R.id.textViewLabelReviews)
        btnAddToCart = view.findViewById(R.id.btnAddToCart)
        btnWriteReview = view.findViewById(R.id.btnWriteReview)
        recyclerViewReviews = view.findViewById(R.id.recyclerViewReviews)
        progressBar = view.findViewById(R.id.progressBarPartDetailsFragment)
    }

    private fun setupRecyclerView() {
        recyclerViewReviews.adapter = reviewsListAdapter
    }

    private fun setClickListeners() {
        btnAddToCart.setOnClickListener {

        }
        btnWriteReview.setOnClickListener {

        }
    }

    private fun setPartData(part: Part) {
        val context = requireContext()
        Glide.with(context)
            .load(part.imageUrl)
            .into(imageViewPartImage)
        textViewPartName.text = part.name
        textViewSpecifications.text = part.specifications
        val localizedPartType = LocalizationHelper.getLocalizedString(
            resources,
            part.partType.nameEn,
            part.partType.nameUk
        )
        textViewPartType.text =
            context.getString(R.string.part_type_placeholder, localizedPartType)
        textViewManufacturer.text =
            context.getString(R.string.manufacturer_placeholder, part.manufacturer.name)
        val localizedDeviceType = LocalizationHelper.getLocalizedString(
            resources,
            part.deviceType.nameEn,
            part.deviceType.nameUk
        )
        textViewDeviceType.text =
            context.getString(R.string.device_type_placeholder, localizedDeviceType)
        val deviceModelsStr = part.deviceModels.joinToString(separator = DEVICE_MODELS_DELIMITER)
        textViewModels.text = context.getString(R.string.device_models_placeholder, deviceModelsStr)
        textViewPrice.text = context.getString(R.string.price_placeholder, part.price.toString())
    }

    private fun observeViewModel() {
        viewModel.reviews.observe(viewLifecycleOwner) {
            setReviewsData(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            switchProgressBarVisibility(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.loadReviews(args.part.id)
    }

    private fun setReviewsData(reviews: List<Review>) {
        textViewLabelReviews.text =
            requireContext().getString(R.string.reviews_placeholder, reviews.size)
        reviewsListAdapter.submitList(reviews)
    }

    private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    companion object {

        private const val DEVICE_MODELS_DELIMITER = ","
    }
}