package com.bcp.challengebcp.presentation.exchange.rate

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bcp.challengebcp.R
import com.bcp.challengebcp.components.inputExchange.InputExchangeClick
import com.bcp.challengebcp.databinding.FragmentExchangeRateBinding
import com.bcp.challengebcp.model.InputExchangeModel
import com.bcp.challengebcp.presentation.exchange.ExchangeRateViewModel
import com.bcp.challengebcp.util.Properties
import java.text.DecimalFormat


class ExchangeRateFragment : Fragment(), InputExchangeClick {
    companion object {
        private const val textUbicateTop = 0
        private const val textUbicateBottom = 1
    }

    private var exchangeRateViewModel: ExchangeRateViewModel? = null
    private lateinit var binding: FragmentExchangeRateBinding
    private var isBuy = false
    private val formatter = DecimalFormat(Properties.PATTER_DECIMAL_FORMATTER)
    private var flagTextUbicate = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_exchange_rate, container, false)
        binding.lifecycleOwner = this
        binding.executeClick = this
        binding.inputExchangeTestData = exchangeRateViewModel
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exchangeRateViewModel = activity?.run {
            ViewModelProvider(this).get(ExchangeRateViewModel::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exchangeRateViewModel?.inputExchangeRateLiveData?.observe(requireActivity(), Observer {
            observeChangeInputs()
            binding.txtComponentExchangeTextSellBuy.text =
                context?.getString(R.string.exchange_rate_sell_buy_text, it.buyMoney, it.sellMoney)
        })
        longClickChangeTypeMoney()
    }

    private fun longClickChangeTypeMoney() {
        binding.lytComponentExchangeRate.ibInputExchangeRateChangeTypeMoney.setOnLongClickListener {
            findNavController().navigate(R.id.action_exchangeRateActivity2_to_typeMoneyList)
            true
        }
    }

    override fun clickChange() {
        rotateImageChange()
        eventReverse()
    }

    private fun observeChangeInputs() {
        observeTopMoneyChanges()
        observeBottomMoneyChanges()
    }

    private fun observeBottomMoneyChanges() {
        binding.lytComponentExchangeRate.edtInputExchangeRateMoneyBottom.onFocusChangeListener =
            OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    flagTextUbicate = textUbicateBottom
                }
            }
        binding.lytComponentExchangeRate.edtInputExchangeRateMoneyTop.onFocusChangeListener =
            OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    flagTextUbicate = textUbicateTop
                }
            }
        binding.lytComponentExchangeRate.edtInputExchangeRateMoneyBottom.addTextChangedListener(
            object :
                TextWatcher {
                override fun afterTextChanged(money: Editable?) {
                    if (flagTextUbicate == textUbicateBottom) {
                        if (money?.toString()!!.isNotEmpty()) {
                            val model =
                                binding.inputExchangeTestData!!.inputExchangeRateLiveData.value
                            val calculate = if (isBuy)
                                money.toString().toDouble() / model!!.sellMoney!!.toDouble()
                            else
                                money.toString()
                                    .toDouble() * model!!.buyMoney!!.toDouble()
                            binding.lytComponentExchangeRate.edtInputExchangeRateMoneyTop.setText(
                                formatter.format(
                                    if (calculate > 0) calculate else Properties.INIT_VALUE_MONEY
                                )
                            )
                        } else {
                            binding.lytComponentExchangeRate.edtInputExchangeRateMoneyTop.setText(
                                formatter.format(
                                    Properties.INIT_VALUE_MONEY
                                )
                            )
                        }
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

            })
    }

    private fun observeTopMoneyChanges() {
        binding.lytComponentExchangeRate.edtInputExchangeRateMoneyTop.addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(money: Editable?) {
                if (flagTextUbicate == textUbicateTop) {
                    if (money?.toString()!!.isNotEmpty()) {
                        val model = binding.inputExchangeTestData!!.inputExchangeRateLiveData.value
                        val calculate = if (isBuy)
                            money.toString()
                                .toDouble() * model!!.buyMoney!!.toDouble()
                        else
                            money.toString().toDouble() / model!!.sellMoney!!.toDouble()
                        binding.lytComponentExchangeRate.edtInputExchangeRateMoneyBottom.setText(
                            formatter.format(
                                if (calculate > 0) calculate else Properties.INIT_VALUE_MONEY
                            )
                        )
                    } else {
                        binding.lytComponentExchangeRate.edtInputExchangeRateMoneyBottom.setText(
                            formatter.format(
                                Properties.INIT_VALUE_MONEY
                            )
                        )
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun eventReverse() {
        val model: InputExchangeModel? =
            binding.inputExchangeTestData?.inputExchangeRateLiveData?.value
        model?.let {
            isBuy = !isBuy
            changeValuesExchangeRate(model)
        }
    }

    private fun changeValuesExchangeRate(model: InputExchangeModel) {
        val bottomValue =
            if (isBuy) model.amountMoneyEnter.toDouble() * model.buyMoney!!.toDouble()
            else model.amountMoneyEnter.toDouble() / model.sellMoney!!.toDouble()
        binding.inputExchangeTestData?.inputExchangeRateLiveData?.value =
            InputExchangeModel(
                model.idTypeMoneyChange,
                model.idTypeMoneyEnter,
                binding.lytComponentExchangeRate.textMoneyTop ?: "",
                model.typeMoneyChange,
                formatter.format(bottomValue),
                model.typeMoneyEnter,
                model.buyMoney,
                model.sellMoney
            )
    }

    private fun rotateImageChange() {
        val obtainRotation =
            binding.lytComponentExchangeRate.ibInputExchangeRateChangeTypeMoney.rotation
        if (obtainRotation == Properties.ROTATE_ROUND) {
            binding.lytComponentExchangeRate.ibInputExchangeRateChangeTypeMoney.rotation =
                Properties.ROTATE_INITIAL
        } else {
            binding.lytComponentExchangeRate.ibInputExchangeRateChangeTypeMoney.rotation =
                Properties.ROTATE_ROUND
        }
    }

}