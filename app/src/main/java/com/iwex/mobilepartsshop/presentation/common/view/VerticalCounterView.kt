package com.iwex.mobilepartsshop.presentation.common.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import com.iwex.mobilepartsshop.R

class VerticalCounterView : LinearLayout {

    var onCountChanged: ((Int) -> Unit)? = null

    var minValue = DEFAULT_MIN_VALUE
        set(value) {
            if (value <= currentValue) {
                field = value
            } else {
                Log.e(TAG, "setMinValue: value should be <= $currentValue (count)")
            }
        }

    var maxValue = DEFAULT_MAX_VALUE
        set(value) {
            if (value >= currentValue) {
                field = value
            } else {
                Log.e(TAG, "setMinValue: value should be >= $currentValue (count)")
            }
        }

    private var currentValue = DEFAULT_VALUE

    private lateinit var editTextCounter: EditText
    private lateinit var btnIncrement: ImageButton
    private lateinit var btnDecrement: ImageButton

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context)
    }

    fun setCount(value: Int) {
        if (value in minValue..maxValue) {
            currentValue = value
            updateCounter()
        } else {
            currentValue = minValue
            Log.e(TAG, "setCount: value should be in $minValue .. $maxValue range")
        }
    }

    fun getCount() = currentValue

    private fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.vertical_counter_view, this)
        editTextCounter = findViewById(R.id.editTextCounter)
        btnIncrement = findViewById(R.id.btnIncrement)
        btnDecrement = findViewById(R.id.btnDecrement)
        setEditTextChangeListener()
        setClickListeners()
        updateCounter()
    }

    private fun setEditTextChangeListener() {
        editTextCounter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                s?.toString()?.toIntOrNull()?.let { newValue ->
                    if (newValue in minValue..maxValue) {
                        currentValue = newValue
                        onCountChanged?.invoke(currentValue)
                    } else {
                        // If the entered value is out of bounds, reset to the previous value
                        updateCounter()
                    }
                }
            }
        })
    }

    private fun setClickListeners() {
        btnIncrement.setOnClickListener { increment() }
        btnDecrement.setOnClickListener { decrement() }
    }

    private fun updateCounter() {
        editTextCounter.setText((currentValue).toString())

    }

    private fun increment() {
        if (currentValue < maxValue) {
            currentValue++
            updateCounter()
        }
    }

    private fun decrement() {
        if (currentValue > minValue) {
            currentValue--
            updateCounter()
        }
    }

    companion object {

        private const val DEFAULT_VALUE = 0

        private const val DEFAULT_MIN_VALUE = 0

        private const val DEFAULT_MAX_VALUE = 1000

        private const val TAG = "VerticalCounterView"
    }
}
