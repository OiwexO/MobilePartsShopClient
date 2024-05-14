package com.iwex.mobilepartsshop.presentation.viewmodel.parts

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iwex.mobilepartsshop.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartsFragment : Fragment() {

    private val viewModel: PartsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_parts, container, false)
    }
}