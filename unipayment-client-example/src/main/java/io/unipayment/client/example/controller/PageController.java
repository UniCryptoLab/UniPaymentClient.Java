package io.unipayment.client.example.controller;

import io.unipayment.client.Configuration;
import io.unipayment.client.UniPaymentClient;
import io.unipayment.client.UniPaymentException;
import io.unipayment.client.example.dto.CreateInvoiceDto;
import io.unipayment.client.models.CreateInvoiceRequest;
import io.unipayment.client.models.InvoiceModel;
import io.unipayment.client.models.Response;
import io.unipayment.client.models.enums.ConfirmSpeed;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController extends BaseController {

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
        model.addAttribute("appId", appId);
        model.addAttribute("apiKey", apiKey);
        model.addAttribute("apiHost", apiHost);
        return "pages/create-invoice";
    }

    @PostMapping("/create-invoice")
    public String postCreateQuery(Model model, CreateInvoiceDto createInvoiceDto) {
        Configuration configuration = new Configuration();
        configuration.setAppId(createInvoiceDto.getAppId());
        configuration.setApiKey(createInvoiceDto.getApiKey());
        configuration.setBaseUrl(createInvoiceDto.getApiHost());
        configuration.setDebug(true);
        configuration.setApiVersion("1.0");

        CreateInvoiceRequest createInvoiceRequest = CreateInvoiceRequest.builder()
                .priceAmount(createInvoiceDto.getPriceAmount())
                .priceCurrency(createInvoiceDto.getPriceCurrency())
                .payCurrency(createInvoiceDto.getPayCurrency())
                .notifyURL(createInvoiceDto.getNotifyUrl())
                .redirectURL(createInvoiceDto.getRedirectUrl())
                .orderId(createInvoiceDto.getOrderId())
                .title(createInvoiceDto.getTitle())
                .description(createInvoiceDto.getDescription())
                .lang(createInvoiceDto.getLang())
                .extArgs(createInvoiceDto.getExtArgs())
                .confirmSpeed(ConfirmSpeed.valueOf(createInvoiceDto.getConfirmSpeed()))
                .build();

        Response<InvoiceModel> response;
        try {
            response = UniPaymentClient.getInstance(configuration).createInvoice(configuration.getApiVersion(), createInvoiceRequest);
            if ("OK".equalsIgnoreCase(response.getCode()) && response.getData() != null) {
                return "redirect:" + response.getData().getInvoiceUrl();
            }
            model.addAttribute("msg", response.getMsg());
            return "pages/create-invoice";
        } catch (UniPaymentException e) {
            model.addAttribute("msg", e.getMessage());
            return "pages/create-invoice";
        }
    }

    @GetMapping("/query-invoice")
    public String queryQuery(Model model) {
        model.addAttribute("pageTitle", "Query Invoice");
        model.addAttribute("appId", appId);
        model.addAttribute("apiKey", apiKey);
        model.addAttribute("apiHost", apiHost);
        return "pages/query-invoice";
    }
}
