package org.example.controller;

import org.example.model.Currency;
import org.example.model.CurrencyExchangeOperation;
import org.example.service.ExchangeOperationService;
import org.example.service.RestCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserExchangeOperationController {

    private final ExchangeOperationService exchangeOperationService;
    private final RestCurrencyService currencyService;

    @Autowired
    public UserExchangeOperationController(ExchangeOperationService exchangeOperationService, RestCurrencyService currencyService) {
        this.exchangeOperationService = exchangeOperationService;
        this.currencyService = currencyService;
    }

    @GetMapping("/exchange")
    public String exchangePage(Model model) {
        List<Currency> currencies = currencyService.findAllCurrencies().getCurrencies();
        model.addAttribute("currencies", currencies);
        return "exchange";
    }

    @GetMapping("/journal")
    public String journalPage(Model model, @RequestParam(value = "groupBy", required = false) String groupBy) {
        if (groupBy == null) {
            List<CurrencyExchangeOperation> exchangeOperationList = exchangeOperationService.getAllOperations();
            model.addAttribute("exchangeOperationList", exchangeOperationList);
        } else {
            model.addAttribute("groupedOperations", exchangeOperationService.getAllOperationsGroupBy(groupBy));
        }
        return "journal";
    }

    @GetMapping("/currencies")
    public String currenciesPage(Model model) {
        model.addAttribute("currencies", currencyService.findAllCurrencies().getCurrencies());
        return "catalog";
    }

    @PostMapping()
    public RedirectView addExchangeLog(@RequestParam("currency") String currency,
                                       @RequestParam("operation") String operation,
                                       @RequestParam("rate") float rate,
                                       @RequestParam("amount") float amount) {
        exchangeOperationService.addExchangeOperation(currency, operation, rate, amount);
        return new RedirectView("/user/exchange");
    }
}
