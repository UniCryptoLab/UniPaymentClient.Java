package io.unipayment.sdk.example.controllers;

import io.unipayment.sdk.BillingAPI;
import io.unipayment.sdk.core.config.Configuration;
import io.unipayment.sdk.example.dto.CreateInvoiceRequestDto;
import io.unipayment.sdk.example.dto.QueryInvoiceRequestDto;
import io.unipayment.sdk.exception.UnipaymentSdkException;
import io.unipayment.sdk.model.*;
import io.unipayment.sdk.model.enums.ConfirmSpeed;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class PageController {

    private final BillingAPI billingAPI;
    private final Configuration configuration;

    public PageController(Configuration configuration) {
        this.billingAPI = BillingAPI.getInstance(configuration);
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
        addOpenClientDetails(model);
        return "pages/create-invoice";
    }

    @PostMapping("/create-invoice")
    public String postCreateQuery(Model model, CreateInvoiceRequestDto createInvoiceRequestDto) {
        Configuration configuration = new Configuration();
        configuration.setClientId(createInvoiceRequestDto.getClientId());
        configuration.setClientSecret(createInvoiceRequestDto.getClientSecret());
        configuration.setHost(createInvoiceRequestDto.getApiHost());
        configuration.setDebug(true);
        configuration.setApiVersion(this.configuration.getApiVersion());

        CreateInvoiceRequest createInvoiceRequest = CreateInvoiceRequest.builder()
                .appId(createInvoiceRequestDto.getAppId())
                .priceAmount(createInvoiceRequestDto.getPriceAmount())
                .priceCurrency(createInvoiceRequestDto.getPriceCurrency())
                .payCurrency(createInvoiceRequestDto.getPayCurrency())
                .payNetwork(createInvoiceRequestDto.getPayNetwork())
                .notifyURL(createInvoiceRequestDto.getNotifyUrl())
                .redirectURL(createInvoiceRequestDto.getRedirectUrl())
                .orderId(createInvoiceRequestDto.getOrderId())
                .title(createInvoiceRequestDto.getTitle())
                .description(createInvoiceRequestDto.getDescription())
                .lang(createInvoiceRequestDto.getLang())
                .extArgs(createInvoiceRequestDto.getExtArgs())
                .confirmSpeed(ConfirmSpeed.valueOf(createInvoiceRequestDto.getConfirmSpeed()))
                .build();

        ApiResponse<Invoice> response;
        try {
            response = billingAPI.createInvoice(createInvoiceRequest);
            if ("OK".equalsIgnoreCase(response.getCode()) && response.getData() != null) {
                return "redirect:" + response.getData().getInvoiceUrl();
            } else {
                model.addAttribute("error", true);
                model.addAttribute("msg", response.getMsg());
                addOpenClientDetails(model);
            }
            return "pages/create-invoice";
        } catch (UnipaymentSdkException e) {
            model.addAttribute("msg", e.getMessage());
            addOpenClientDetails(model);
            return "pages/create-invoice";
        }
    }

    @GetMapping("/query-invoice")
    public String queryQuery(QueryInvoiceRequestDto queryInvoiceRequestDto, Model model) {
        model.addAttribute("pageTitle", "Query Invoice");
        addOpenClientDetails(model);
        model.addAttribute("queryInvoiceRequestDto", queryInvoiceRequestDto);
        return "pages/query-invoice";
    }

    @PostMapping(value = "query-invoice", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String queryInvoice(@ModelAttribute QueryInvoiceRequestDto queryInvoiceRequestDto, Model model) {
        Configuration configuration = new Configuration();
        configuration.setClientId(queryInvoiceRequestDto.getClientId());
        configuration.setClientSecret(queryInvoiceRequestDto.getClientSecret());
        configuration.setHost(queryInvoiceRequestDto.getApiHost());
        configuration.setApiVersion(this.configuration.getApiVersion());
        configuration.setDebug(true);

        QueryInvoiceRequest queryInvoiceRequest = getQueryInvoiceRequest(queryInvoiceRequestDto);
        ApiResponse<QueryResult<Invoice>> response;
        try {
            response = billingAPI.queryInvoices(queryInvoiceRequest);
            if ("OK".equalsIgnoreCase(response.getCode())) {
                model.addAttribute("success", true);
                model.addAttribute("queryResult", response.getData().getModels());
                model.addAttribute("resultCount", response.getData().getTotal());
            }
        } catch (UnipaymentSdkException e) {
            model.addAttribute("resultCount", 0);
        }
        addOpenClientDetails(model);
        return "pages/query-invoice";
    }

    private static @NotNull QueryInvoiceRequest getQueryInvoiceRequest(QueryInvoiceRequestDto queryInvoiceRequestDto) {
        QueryInvoiceRequest queryInvoiceRequest = new QueryInvoiceRequest();
        queryInvoiceRequest.setOrderId(Objects.toString(queryInvoiceRequestDto.getOrderId(), null));
        queryInvoiceRequest.setInvoiceId(Objects.toString(queryInvoiceRequestDto.getInvoiceId(), null));
        queryInvoiceRequest.setPageNo(queryInvoiceRequestDto.getPageNo() != null ? queryInvoiceRequestDto.getPageNo() : 1);
        queryInvoiceRequest.setPageSize(queryInvoiceRequestDto.getPageSize() != null ? queryInvoiceRequestDto.getPageSize() : 10);
        queryInvoiceRequest.setIsAsc(queryInvoiceRequestDto.getIsAsc());
        queryInvoiceRequest.setStart(queryInvoiceRequestDto.getStart());
        queryInvoiceRequest.setEnd(queryInvoiceRequestDto.getEnd());
        queryInvoiceRequest.setStatus(queryInvoiceRequestDto.getStatus());
        return queryInvoiceRequest;
    }

    /**
     * Add API Details to Model
     *
     * @param model {@link  Model}
     */
    private void addOpenClientDetails(Model model) {
        model.addAttribute("clientId", configuration.getClientId());
        model.addAttribute("clientSecret", configuration.getClientSecret());
        model.addAttribute("apiHost", configuration.getHost());
        model.addAttribute("apiId", configuration.getAppId());
    }
}
