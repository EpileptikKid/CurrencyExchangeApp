package org.example.controller;

import org.example.model.Currency;
import org.example.model.CurrencyList;
import org.example.service.RestCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/currency_rates")
public class RestCurrencyController {

    private final RestCurrencyService service;

    @Autowired
    public RestCurrencyController(RestCurrencyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getCurrencyRates(
            @RequestParam(value = "json", required = false) String json) {

        CurrencyList currencies = service.findAllCurrencies();
        HttpHeaders headers = new HttpHeaders();
        if (currencies.getCurrencies().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no content");
        } else {
            if (json != null) {
                headers.setContentType(MediaType.APPLICATION_JSON);
                return new ResponseEntity<>(currencies.getCurrencies(), headers, HttpStatus.OK);
            } else {
                headers.setContentType(MediaType.APPLICATION_XML);
                return new ResponseEntity<>(currencies, headers, HttpStatus.OK);
            }
        }
    }


    @GetMapping("/{ccy}")
    public ResponseEntity<Currency> getCurrencyRateByCcy(@PathVariable String ccy,
                                                         @RequestParam(value = "json", required = false) String json) {
        HttpHeaders headers = new HttpHeaders();

        if (json != null) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        } else {
            headers.setContentType(MediaType.APPLICATION_XML);
        }

        Currency currency = service.getCurrencyByCcy(ccy);

        return currency != null ? new ResponseEntity<>(currency, headers, HttpStatus.OK)
                                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> addCurrencyRate(@RequestBody Currency currency) {
        boolean result = service.addCurrency(currency);

        return  result ? ResponseEntity.ok("added")
                       : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
    }

    @PutMapping("/{ccy}")
    public ResponseEntity<String> updateCurrencyRate(@RequestBody Currency currency,
                                                     @PathVariable String ccy) {
        if (service.isCurrencyExist(ccy)) {
            return service.updateCurrency(currency) ? ResponseEntity.ok("edited")
                                                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This currency does not exist");
        }
    }

    @DeleteMapping("/{ccy}")
    public ResponseEntity<String> deleteCurrencyRate(@PathVariable String ccy) {
        if (service.isCurrencyExist(ccy)) {
            return service.deleteCurrencyByCcy(ccy) ? ResponseEntity.ok("deleted")
                                                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This currency does not exist");
        }
    }

}
