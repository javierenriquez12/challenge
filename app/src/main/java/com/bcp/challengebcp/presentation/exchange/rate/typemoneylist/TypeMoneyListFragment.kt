package com.bcp.challengebcp.presentation.exchange.rate.typemoneylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcp.challengebcp.R
import com.bcp.challengebcp.databinding.FragmentTypeMoneyListBinding
import com.bcp.challengebcp.model.InputExchangeModel
import com.bcp.challengebcp.model.TypeMoneyModel
import com.bcp.challengebcp.presentation.exchange.ExchangeRateViewModel
import com.bcp.challengebcp.presentation.exchange.rate.ListenClickItemTypeMoney
import com.bcp.challengebcp.util.Properties
import com.bcp.challengebcp.util.getViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class TypeMoneyListFragment : Fragment(), ListenClickItemTypeMoney {

    private var exchangeRateViewModel: ExchangeRateViewModel? = null
    private lateinit var binding: FragmentTypeMoneyListBinding
    private lateinit var typeMoneyListViewModel: TypeMoneyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exchangeRateViewModel = activity?.run {
            ViewModelProvider(this).get(ExchangeRateViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_type_money_list, container, false)
        typeMoneyListViewModel = this.getViewModel { TypeMoneyListViewModel() }
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        typeMoneyListViewModel.getTypeMoneyList()
        val typeMoneyListener: ListenClickItemTypeMoney = this
        binding.rvTypeMoneyList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter =
                TypeMoneyAdapter(
                    typeMoneyListViewModel.typeMoneyListeLiveData.value?.typesMoney,
                    typeMoneyListener
                )
        }
    }

    override fun clickTypeMoney(typeMoney: TypeMoneyModel) {
        val model = exchangeRateViewModel?.inputExchangeRateLiveData?.value
        if (model?.idTypeMoneyEnter != typeMoney.idTypeMoneyEnter && model?.idTypeMoneyChange != typeMoney.idTypeMoneyEnter) {
            val newValueTypeMoney = InputExchangeModel(
                idTypeMoneyEnter = typeMoney.idTypeMoneyEnter,
                idTypeMoneyChange = model?.idTypeMoneyChange ?: "",
                amountMoneyChange = DecimalFormat(Properties.PATTER_DECIMAL_FORMATTER).format(
                    Properties.INIT_VALUE_MONEY
                ),
                amountMoneyEnter = DecimalFormat(Properties.PATTER_DECIMAL_FORMATTER).format(
                    Properties.INIT_VALUE_MONEY
                ),
                buyMoney = typeMoney.buyMoney,
                sellMoney = typeMoney.sellMoney,
                typeMoneyChange = model?.typeMoneyChange
                    ?: "",
                typeMoneyEnter = typeMoney.typeMoney
            )
            exchangeRateViewModel?.inputExchangeRateLiveData?.value = newValueTypeMoney
            typeMoneyListViewModel.saveNewTypeMoney(newValueTypeMoney)
            findNavController().popBackStack()
        } else {
            Snackbar.make(binding.clContainerTypeMoneyList,getString(R.string.snackbar_message_alert),Snackbar.LENGTH_SHORT).show()
        }
    }
}