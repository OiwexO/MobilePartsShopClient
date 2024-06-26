package com.iwex.mobilepartsshop.presentation.fragment.profile.orders.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.order.Order
import com.iwex.mobilepartsshop.presentation.utils.LocalizationHelper
import com.iwex.mobilepartsshop.presentation.viewmodel.profile.orders.details.OrderDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailsFragment : Fragment() {

    private val args by navArgs<OrderDetailsFragmentArgs>()

    private val viewModel: OrderDetailsViewModel by viewModels()

    private lateinit var orderItemsListAdapter: OrderItemsListAdapter
    private lateinit var textViewOrderId: TextView
    private lateinit var textViewCustomerId: TextView
    private lateinit var recyclerViewOrderItems: RecyclerView
    private lateinit var textViewAddress: TextView
    private lateinit var textViewOrderStatus: TextView
    private lateinit var btnCancelOrder: Button
    private lateinit var btnBack: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_order_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupRecyclerView()
        setClickListeners()
        observeViewModel()
    }

    private fun initViews(view: View) {
        textViewOrderId = view.findViewById(R.id.textViewOrderId)
        textViewCustomerId = view.findViewById(R.id.textViewCustomerId)
        recyclerViewOrderItems = view.findViewById(R.id.recyclerViewOrderItems)
        textViewAddress = view.findViewById(R.id.textViewAddress)
        textViewOrderStatus = view.findViewById(R.id.textViewOrderStatus)
        btnCancelOrder = view.findViewById(R.id.btnCancelOrder)
        btnBack = view.findViewById(R.id.btnBack)
        progressBar = view.findViewById(R.id.progressBarOrderDetailsFragment)
    }

    private fun setupRecyclerView() {
        orderItemsListAdapter = OrderItemsListAdapter()
        orderItemsListAdapter.isUkrainianLocale = LocalizationHelper.isUkrainianLocale(resources)
        recyclerViewOrderItems.adapter = orderItemsListAdapter
    }

    private fun setClickListeners() {
        btnCancelOrder.setOnClickListener {
            viewModel.cancelOrder()
        }
        btnBack.setOnClickListener {
            navigateToOrdersFragment()
        }
    }

    private fun navigateToOrdersFragment() {
        findNavController().navigate(R.id.action_orderDetailsFragment_to_ordersFragment)
    }

    private fun observeViewModel() {
        viewModel.order.observe(viewLifecycleOwner) {
            updateUi(it)
        }
        viewModel.canCancelOrder.observe(viewLifecycleOwner) {
            btnCancelOrder.isEnabled = it
        }
        viewModel.onSuccess.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.saved, Toast.LENGTH_SHORT).show()
            navigateToOrdersFragment()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            switchProgressBarVisibility(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.getOrder(args.orderId)
    }

    private fun updateUi(order: Order) {
        textViewOrderId.text = getString(R.string.order_id_placeholder, order.id)
        textViewCustomerId.text = getString(R.string.order_customer_id_placeholder, order.customerId)
        orderItemsListAdapter.submitList(order.orderItems)
        val address = order.shippingAddress
        val addressString = getString(
            R.string.order_full_address_placeholder,
            address.buildingNumber,
            address.street,
            address.city,
            address.state,
            address.country,
            address.postalCode.toString()
        )
        textViewAddress.text = addressString
        val statusStr = LocalizationHelper.getLocalizedOrderStatus(order.status, requireContext())
        textViewOrderStatus.text = getString(R.string.order_status_placeholder, statusStr)
    }

    private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
