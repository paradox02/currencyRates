package com.paradox.currency.data.repositories

import com.paradox.currency.data.entities.Currency
import com.paradox.currency.data.entities.ExchangeRate
import com.paradox.currency.data.dao.CurrencyDao
import com.paradox.currency.data.dao.ExchangeRateDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * repository to handle local data
 */
@Singleton
class LocalCurrencyRepository @Inject constructor(
    private val exchangeRateDao: ExchangeRateDao,
    private val currencyDao: CurrencyDao,
) {


    fun getCurrencies(): List<Currency> {
        return currencyDao.getAll()
    }

    fun getRatesWithTimeGreaterThan(minTime: Long): List<ExchangeRate> {
        return exchangeRateDao.getAllGreaterThanTimestamp(minTime)
    }

    fun saveCurrencies(currencies: List<Currency>) {
        currencyDao.insertAll(currencies)
    }

    fun saveExchangeRates(exchangeRateList: List<ExchangeRate>) {
        exchangeRateDao.insertAll(exchangeRateList)
    }

}