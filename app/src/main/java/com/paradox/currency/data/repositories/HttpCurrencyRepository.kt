package com.paradox.currency.data.repositories

import android.util.Log
import com.paradox.currency.data.dto.ExchangeRateDTO
import com.paradox.currency.data.exceptions.GenericException
import com.paradox.currency.data.exceptions.NetworkException
import com.paradox.currency.data.repositories.api.ApiInterface
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * repository to handle external network call
 */
@Singleton
class HttpCurrencyRepository @Inject constructor() {

    private val tag = "httpRepository"

    suspend fun fetchAllCurrencies(): Map<String, String> {
        Log.d(tag, "fetching currencies from api...")

        // Create an instance of ApiInterface
        val apiInterface = ApiInterface.create()

        try {
            // Directly call the suspend function getCurrencies() which returns Map<String, String>
            val currencies = apiInterface.getCurrencies()

            Log.d(tag, "call executed, response body: $currencies")

            // Return the fetched currencies
            return currencies

        } catch (e: HttpException) {
            // Handle HTTP errors
            Log.w(tag, "a network error occurred during communication, throwing a NetworkException ${e.response()?.errorBody()}")
            throw NetworkException()

        } catch (e: IOException) {
            // Handle errors such as connection issues
            Log.w(tag, "an IO error occurred during communication, check your connectivity")
            throw NetworkException()

        } catch (e: Exception) {
            // Handle other unexpected errors
            Log.w(tag, "an unexpected error occurred, throwing a GenericException")
            throw GenericException()
        }
    }

    suspend fun fetchLatestExchangeRates(apiKey: String): ExchangeRateDTO {
        Log.d(tag, "Fetching exchange rates from API...")

        // Create an instance of ApiInterface
        val apiInterface = ApiInterface.create()

        try {
            // Directly call the suspend function getLatestExchangeRate() which returns ExchangeRateDTO
            val rates = apiInterface.getLatestExchangeRate(apiKey)

            Log.d(tag, "Call executed, response body: $rates")

            // Return the fetched exchange rates
            return rates

        } catch (e: HttpException) {
            // Handle HTTP errors
            Log.w(tag, "A network error occurred during communication, throwing a NetworkException ${e.response()?.errorBody()}")
            throw NetworkException()

        } catch (e: IOException) {
            // Handle errors such as connection issues
            Log.w(tag, "An IO error occurred during communication, check your connectivity")
            throw NetworkException()

        } catch (e: Exception) {
            // Handle other unexpected errors
            Log.w(tag, "An unexpected error occurred, throwing a GenericException")
            throw GenericException()
        }
    }

}