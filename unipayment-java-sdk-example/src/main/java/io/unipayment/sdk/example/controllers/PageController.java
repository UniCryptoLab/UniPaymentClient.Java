package io.unipayment.sdk.example.controllers;

import io.unipayment.sdk.BillingAPI;
import io.unipayment.sdk.core.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class PageController {

    private final BillingAPI billingAPI;
    private final Configuration configuration;

    public PageController(Configuration configuration) {
        billingAPI = BillingAPI.getInstance(configuration);
        this.configuration = configuration;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/create-invoice";
    }

    @GetMapping("/create-invoice")
    public String createQuery(Model model) {
        model.addAttribute("pageTitle", "Create Invoice");
        model.addAttribute("appId", configuration.getAppId());
        return "pages/create-invoice";
    }
}
