package com.paradox.currency.data

/**
 * model class to handle currencies value in UI, used mainly by adapter [com.paradox.currency.ui.adapters.CurrenciesAdapter]
 */
data class CurrencyValue(

    val symbol: String,

    val amount: Double,
)