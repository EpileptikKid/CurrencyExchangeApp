package org.example.controller;

import org.example.model.Currency;
import org.example.service.ExchangeOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserExchangeOperationController {

    private final ExchangeOperationService service;

    @Autowired
    public UserExchangeOperationController(ExchangeOperationService service) {
        this.service = service;
    }

    @GetMapping("/exchange")
    public String exchangePage(Model model) {
        List<Currency> currencies = service.getAllCurrencies();
        model.addAttribute("currencies", currencies);
        return "exchange";
    }

    @GetMapping("/journal")
    public String journalPage(Model model, @RequestParam(value = "groupBy", required = false) String groupBy) {
        if (groupBy == null) {
            model.addAttribute("exchangeOperationList", service.getAllOperations());
        } else {
            model.addAttribute("groupedOperations", service.getAllOperationsGroupBy(groupBy));
        }
        return "journal";
    }

    @GetMapping("/currencies")
    public String currenciesPage(Model model) {
        model.addAttribute("currencies", service.getAllOperations());
        return "catalog";
    }

    @PostMapping()
    public RedirectView addExchangeLog(@RequestParam("currency") String currency,
                                       @RequestParam("operation") String operation,
                                       @RequestParam("rate") float rate,
                                       @RequestParam("amount") float amount) {
        service.addExchangeOperation(currency, operation, rate, amount);
        return new RedirectView("/user/exchange");
    }
}
