package org.example.controller;

import org.example.model.Currency;
import org.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;

    @Autowired
    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/journal")
    public String journalPage(Model model) {
        model.addAttribute("exchangeOperationList", service.getAllOperations());
        return "admin_journal";
    }

    @GetMapping("/currencies")
    public String currenciesPage(Model model) {
        model.addAttribute("currencies", service.getAllCurrencies());
        return "admin_catalog";
    }

    @GetMapping("/currencies/{ccy}")
    public String currenciesEditPage(@PathVariable String ccy, Model model) {
        model.addAttribute("currency", service.getCurrencyByCcy(ccy));
        return "edit_currency";
    }

    @GetMapping("/currencies/add")
    public String addCurrencyPage(Model model) {
        model.addAttribute("addedCurrencies", new Currency());
        return "add_currency";
    }

    @PostMapping("/journal/delete")
    public String deleteExchangeOperationById(@RequestParam("id") int id, Model model) {
        service.deleteExchangeOperationById(id);
        model.addAttribute("exchangeOperationList", service.getAllOperations());
        return "admin_journal";
    }

    @PostMapping("/journal/restore")
    public String restoreExchangeOperationById(@RequestParam("id") int id, Model model) {
        service.restoreExchangeOperationById(id);
        model.addAttribute("exchangeOperationList", service. getAllOperations());
        return "admin_journal";
    }

    @PostMapping("/currencies/update")
    public String updateCurrencyByCcy(@ModelAttribute("addedCurrencies") Currency currency, Model model) {
        service.updateCurrencyByCcy(currency);
        model.addAttribute("currencies", service.getAllCurrencies());
        return "admin_catalog";
    }

    @PostMapping("currencies/add")
    public String addCurrency(@ModelAttribute("addedCurrencies") Currency currency, Model model) {
        service.addCurrency(currency);
        model.addAttribute("currencies", service.getAllCurrencies());
        return "admin_catalog";
    }

    @PostMapping("/currencies/delete")
    public String deleteCurrencyByCcy(@RequestParam("ccy") String ccy, Model model) {
        service.deleteCurrencyByCcy(ccy);
        model.addAttribute("currencies", service.getAllCurrencies());
        return "admin_catalog";
    }
}
