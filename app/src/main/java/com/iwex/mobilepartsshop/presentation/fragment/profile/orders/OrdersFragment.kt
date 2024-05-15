package com.iwex.mobilepartsshop.presentation.fragment.profile.orders

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
import androidx.recyclerview.widget.RecyclerView
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.presentation.viewmodel.profile.orders.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private val viewModel: OrdersViewModel by viewModels()

    private lateinit var ordersListAdapter: OrdersListAdapter
    private lateinit var recyclerViewAssignedOrders: RecyclerView
    private lateinit var btnBack: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupRecyclerView()
        setClickListeners()
        observeViewModel()
    }

    private fun initViews(view: View) {
        recyclerViewAssignedOrders = view.findViewById(R.id.recyclerViewAssignedOrders)
        btnBack = view.findViewById(R.id.btnBack)
        progressBar = view.findViewById(R.id.progressBarAssignedOrdersFragment)
    }

    private fun setupRecyclerView() {
        ordersListAdapter = OrdersListAdapter()
        ordersListAdapter.onOpenOrderClickListener = {
            navigateToOrderDetailsFragment(it.id)
        }
        recyclerViewAssignedOrders.adapter = ordersListAdapter
    }

    private fun navigateToOrderDetailsFragment(orderId: Long) {
        findNavController().navigate(
            OrdersFragmentDirections.actionOrdersFragmentToOrderDetailsFragment(orderId)
        )
    }

    private fun setClickListeners() {
        btnBack.setOnClickListener {
            navigateToProfileFragment()
        }
    }

    private fun navigateToProfileFragment() {
        findNavController().navigate(R.id.action_ordersFragment_to_profileFragment)
    }

    private fun observeViewModel() {
        viewModel.orders.observe(viewLifecycleOwner) {
            ordersListAdapter.submitList(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            switchProgressBarVisibility(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.getOrders()
    }

    private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}