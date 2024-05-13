package com.iwex.mobilepartsshop.presentation.fragment.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.user.User
import com.iwex.mobilepartsshop.presentation.OnLoggedOutListener
import com.iwex.mobilepartsshop.presentation.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var onLoggedOutListener: OnLoggedOutListener

    private lateinit var imageViewAvatar: ImageView
    private lateinit var textViewUserId: TextView
    private lateinit var textViewUsername: TextView
    private lateinit var textViewFullName: TextView
    private lateinit var btnChangePersonalInfo: Button
    private lateinit var btnManageAssignedOrders: Button
    private lateinit var btnLogout: Button
    private lateinit var progressBar: ProgressBar

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLoggedOutListener) {
            onLoggedOutListener = context
        } else {
            throw RuntimeException("Activity ${context::class.java.canonicalName} should implement OnLoggedOutListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setClickListeners()
        observeViewModel()
    }

    private fun initViews(view: View) {
        imageViewAvatar = view.findViewById(R.id.imageViewAvatar)
        textViewUserId = view.findViewById(R.id.textViewUserId)
        textViewUsername = view.findViewById(R.id.textViewUsername)
        textViewFullName = view.findViewById(R.id.textViewFullName)
        btnChangePersonalInfo = view.findViewById(R.id.btnChangePersonalInfo)
        btnManageAssignedOrders = view.findViewById(R.id.btnManageAssignedOrders)
        btnLogout = view.findViewById(R.id.btnLogout)
        progressBar = view.findViewById(R.id.progressBarProfileFragment)
    }

    private fun setClickListeners() {
        btnChangePersonalInfo.setOnClickListener {
            navigateToPersonalInfoFragment()
        }
        btnManageAssignedOrders.setOnClickListener {
            navigateToAssignedOrdersFragment()
        }
        btnLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun navigateToPersonalInfoFragment() {

    }

    private fun navigateToAssignedOrdersFragment() {
        findNavController().navigate(R.id.ordersFragment)
    }

    private fun observeViewModel() {
        viewModel.user.observe(viewLifecycleOwner) {
            updateUi(it)
        }
        viewModel.onLogout.observe(viewLifecycleOwner) {
            onLoggedOutListener.onLoggedOut()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.getUser()
    }

    private fun updateUi(user: User) {
        textViewUserId.text = getString(R.string.user_id_placeholder, user.id)
        textViewUsername.text = getString(R.string.username_placeholder, user.username)
        textViewFullName.text =
            getString(R.string.full_name_placeholder, user.firstname, user.lastname)
    }
}