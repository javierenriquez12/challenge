package com.bcp.challengebcp.presentation.exchange.rate

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bcp.challengebcp.R
import com.bcp.challengebcp.databinding.FragmentExchangeRateBinding
import com.bcp.challengebcp.model.InputExchangeModel
import com.bcp.challengebcp.util.getViewModel
import kotlinx.android.synthetic.main.fragment_exchange_rate.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExchangeRateActivity.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExchangeRateFragment: Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var exchangeRateViewModel: ExchangeRateViewModel
    private lateinit var exchangeModel: InputExchangeModel
    private lateinit var binding : FragmentExchangeRateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_exchange_rate,container,false)
        binding.lifecycleOwner = this
        exchangeRateViewModel = this.getViewModel { ExchangeRateViewModel() }
        binding.inputExchangeTestData = exchangeRateViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputExchangeTestData?.testBinding()
    }

}