package com.bcp.challengebcp.components.inputExchange

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bcp.challengebcp.R

class InputExchangeRate @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var edtMoneyTop: EditText
    private var edtMoneyBottom: EditText
    private var txtTypeMoneyTop: TextView
    private var txtTypeMoneyBottom: TextView
    private var imageChangeTypeMoney: ImageButton
    init {
        val view = inflate(context, R.layout.component_exchange_rate, this)
        edtMoneyTop = view.findViewById(R.id.edt_input_exchange_rate_money_top)
        edtMoneyBottom = view.findViewById(R.id.edt_input_exchange_rate_money_bottom)
        txtTypeMoneyTop = view.findViewById(R.id.txt_input_exchange_rate_type_money_top)
        txtTypeMoneyBottom = view.findViewById(R.id.txt_input_exchange_rate_type_money_bottom)
        imageChangeTypeMoney = view.findViewById(R.id.ib_input_exchange_rate_change_type_money)
    }

    fun getMoneyTop() = edtMoneyTop

    fun getMoneyBottom() = edtMoneyBottom

    fun txtTypeMoneyTop() = txtTypeMoneyTop

    fun txtTypeMoneyBottom() = txtTypeMoneyBottom

}