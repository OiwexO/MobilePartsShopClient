package com.iwex.mobilepartsshop.presentation.fragment.parts.details

import androidx.lifecycle.ViewModel
import com.iwex.mobilepartsshop.domain.use_case.review.GetReviewsByPartIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PartDetailsViewModel @Inject constructor(
    private val getReviewsByPartIdUseCase: GetReviewsByPartIdUseCase,
    // add to cart use case
) : ViewModel() {

}