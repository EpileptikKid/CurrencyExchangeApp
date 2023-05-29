package org.example.controller;

import org.example.DTO.CurrencyNBU;
import org.example.model.Currency;
import org.example.model.CurrencyList;
import org.example.service.RestCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

        List<Currency> currencies = service.findAllCurrencies();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(checkMediaType(json));

        return new ResponseEntity<>(
                json == null ? new CurrencyList(currencies)
                             : currencies,
                headers,
                HttpStatus.OK);
    }


    @GetMapping("/{ccy}")
    public ResponseEntity<Currency> getCurrencyRateByCcy(@PathVariable String ccy,
                                                         @RequestParam(value = "json", required = false) String json) {

        Currency currency = service.getCurrencyByCcy(ccy);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(checkMediaType(json));

        return currency == null ? ResponseEntity.notFound().build()
                                : new ResponseEntity<>(currency, headers, HttpStatus.OK);
    }

    @GetMapping("/pb")
    public ResponseEntity<List<Currency>> getCurrenciesPB() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
        ResponseEntity<List<Currency>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Currency>>() {});
        List<Currency> currencies = response.getBody();
        return new ResponseEntity<>(currencies, headers, HttpStatus.OK);
    }

    @GetMapping("/nbu")
    public ResponseEntity<List<CurrencyNBU>> getCurrenciesNBU() {
        String url = "https://bank.gov.ua/NBU_Exchange/exchange?json";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<CurrencyNBU> currencies = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CurrencyNBU>>() {})
                .getBody();
        return new ResponseEntity<>(currencies, headers, HttpStatus.OK);
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

    private MediaType checkMediaType(String checkStr) {
        return checkStr == null ? MediaType.APPLICATION_XML
                                : MediaType.APPLICATION_JSON;
    }

}
