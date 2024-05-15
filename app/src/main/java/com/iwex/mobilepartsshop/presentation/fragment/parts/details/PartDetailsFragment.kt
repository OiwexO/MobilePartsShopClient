package com.iwex.mobilepartsshop.presentation.fragment.parts.details

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.presentation.fragment.parts.PartsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartDetailsFragment : Fragment() {

    private val args by navArgs<PartDetailsFragmentArgs>()

    private val viewModel: PartDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_part_details, container, false)
    }
}