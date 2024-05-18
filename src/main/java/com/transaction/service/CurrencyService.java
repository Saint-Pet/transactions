package com.transaction.service;

import com.transaction.models.Currency;
import com.transaction.repository.CurrencyRepository;
import com.transaction.dto.CurrencyDTO.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String EXCHANGE_RATE_API_URL = "https://v6.exchangerate-api.com/v6/faa9720162c45c55f7dac6ab/latest/";

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Optional<Currency> getCurrencyByCode(String code) {
        return currencyRepository.findById(code);
    }

    public Currency createCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Currency updateCurrency(String code, Currency currencyDetails) {
        Currency currency = currencyRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Currency not found with code: " + code));
        currency.setCurrencyName(currencyDetails.getCurrencyName());
        currency.setSymbol(currencyDetails.getSymbol());
        return currencyRepository.save(currency);
    }

    public void deleteCurrency(String code) {
        Currency currency = currencyRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Currency not found with code: " + code));
        currencyRepository.delete(currency);
    }

    public Double getExchangeRate(String fromCurrency, String toCurrency) {
        String url = EXCHANGE_RATE_API_URL + fromCurrency;
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

        if (response == null || response.getConversion_rates() == null || !response.getConversion_rates().containsKey(toCurrency)) {
            throw new IllegalArgumentException("Exchange rate not found for currencies: " + fromCurrency + " to " + toCurrency);
        }

        return response.getConversion_rates().get(toCurrency);
    }

    public Double convertCurrency(String fromCurrency, String toCurrency, Double amount) {
        Double rate = getExchangeRate(fromCurrency, toCurrency);
        return amount * rate;
    }
}
