package com.bcp.challengebcp.model

data class InputExchangeModel(
    val idTypeMoneyEnter: String,
    val idTypeMoneyChange: String,
    val amountMoneyEnter: String,
    val typeMoneyEnter: String,
    val amountMoneyChange: String,
    val typeMoneyChange: String,
    val buyMoney: String? = "",
    val sellMoney: String? = ""
)