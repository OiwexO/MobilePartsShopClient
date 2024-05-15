package com.iwex.mobilepartsshop.presentation.fragment.cart

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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.order.OrderItem
import com.iwex.mobilepartsshop.presentation.utils.LocalizationHelper
import com.iwex.mobilepartsshop.presentation.viewmodel.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private val viewModel: CartViewModel by viewModels()

    private val cartOrderItemsListAdapter = CartOrderItemsListAdapter()

    private lateinit var recyclerViewCartOrderItems: RecyclerView
    private lateinit var textViewOrderPrice: TextView
    private lateinit var btnPlaceOrder: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupRecyclerView()
        setClickListeners()
        observeViewModel()
    }

    private fun initViews(view: View) {
        recyclerViewCartOrderItems = view.findViewById(R.id.recyclerViewCartOrderItems)
        textViewOrderPrice = view.findViewById(R.id.textViewOrderPrice)
        btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder)
        progressBar = view.findViewById(R.id.progressBarCartFragment)
    }

    private fun setupRecyclerView() {
        cartOrderItemsListAdapter.isUkrainianLocale =
            LocalizationHelper.isUkrainianLocale(resources)
        cartOrderItemsListAdapter.onPartQuantityChanged = {
            viewModel.changePartQuantity(it.part.id, it.quantity)
        }
        recyclerViewCartOrderItems.adapter = cartOrderItemsListAdapter
        setOnItemHorizontalSwipeListener()
    }

    private fun setOnItemHorizontalSwipeListener() {
        val horizontalSwipeCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val orderItems = cartOrderItemsListAdapter.currentList
                val item = orderItems[viewHolder.adapterPosition]
                updatePlaceOrderButtonState(orderItems)
                viewModel.deletePartFromCart(item.part.id)
            }
        }
        val touchHelper = ItemTouchHelper(horizontalSwipeCallback)
        touchHelper.attachToRecyclerView(recyclerViewCartOrderItems)
    }

    private fun updatePlaceOrderButtonState(orderItems: List<OrderItem>) {
        btnPlaceOrder.isEnabled = orderItems.isNotEmpty()
    }

    private fun setClickListeners() {
        btnPlaceOrder.setOnClickListener {
            viewModel.placeOrder()
        }
    }

    private fun observeViewModel() {
        viewModel.orderItems.observe(viewLifecycleOwner) {
            cartOrderItemsListAdapter.submitList(it)
            viewModel.calculateOrderPrice()
            updatePlaceOrderButtonState(it)
        }
        viewModel.orderPrice.observe(viewLifecycleOwner) {
            textViewOrderPrice.text =
                requireContext().getString(R.string.price_placeholder, it.toString())
        }
        viewModel.onSuccess.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.saved, Toast.LENGTH_SHORT).show()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            switchProgressBarVisibility(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.loadOrderItems()
    }

    private fun switchProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}