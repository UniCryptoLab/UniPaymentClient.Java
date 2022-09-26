package io.unipayment.client.example.controller;

import io.unipayment.client.Configuration;
import io.unipayment.client.UniPaymentClient;
import io.unipayment.client.UniPaymentException;
import io.unipayment.client.example.dto.CreateInvoiceRequestDto;
import io.unipayment.client.example.dto.QueryInvoiceRequestDto;
import io.unipayment.client.models.*;
import io.unipayment.client.models.enums.ConfirmSpeed;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        addOpenClientDetails(model);
        return "pages/create-invoice";
    }

    @PostMapping("/create-invoice")
    public String postCreateQuery(Model model, CreateInvoiceRequestDto createInvoiceRequestDto) {
        Configuration configuration = new Configuration();
        configuration.setClientId(createInvoiceRequestDto.getClientId());
        configuration.setClientSecret(createInvoiceRequestDto.getClientSecret());
        configuration.setBaseUrl(createInvoiceRequestDto.getApiHost());
        configuration.setDebug(true);
        configuration.setApiVersion(apiVersion);

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

        Response<InvoiceModel> response;
        try {
            response = UniPaymentClient.getInstance(configuration).createInvoice(configuration.getApiVersion(), createInvoiceRequest);
            if ("OK".equalsIgnoreCase(response.getCode()) && response.getData() != null) {
                return "redirect:" + response.getData().getInvoiceUrl();
            } else {
                model.addAttribute("error", true);
                model.addAttribute("msg", response.getMsg());
                addOpenClientDetails(model);
            }
            return "pages/create-invoice";
        } catch (UniPaymentException e) {
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
        configuration.setBaseUrl(queryInvoiceRequestDto.getApiHost());
        configuration.setApiVersion(apiVersion);
        configuration.setDebug(true);

        QueryInvoiceRequest queryInvoiceRequest = new QueryInvoiceRequest();
        queryInvoiceRequest.setOrderId(StringUtils.defaultString(queryInvoiceRequestDto.getOrderId(), null));
        queryInvoiceRequest.setInvoiceId(StringUtils.defaultString(queryInvoiceRequestDto.getInvoiceId(), null));
        queryInvoiceRequest.setPageNo(queryInvoiceRequestDto.getPageNo() != null ? queryInvoiceRequestDto.getPageNo() : 1);
        queryInvoiceRequest.setPageSize(queryInvoiceRequestDto.getPageSize() != null ? queryInvoiceRequestDto.getPageSize() : 10);
        queryInvoiceRequest.setIsAsc(queryInvoiceRequestDto.getIsAsc());
        queryInvoiceRequest.setStart(queryInvoiceRequestDto.getStart());
        queryInvoiceRequest.setEnd(queryInvoiceRequestDto.getEnd());
        queryInvoiceRequest.setStatus(queryInvoiceRequestDto.getStatus());
        Response<QueryResult<InvoiceModel>> response;
        try {
            response = UniPaymentClient.getInstance(configuration).queryInvoices(configuration.getApiVersion(), queryInvoiceRequest);
            if ("OK".equalsIgnoreCase(response.getCode())) {
                model.addAttribute("success", true);
                model.addAttribute("queryResult", response.getData().getModels());
                model.addAttribute("resultCount", response.getData().getTotal());
            }
        } catch (UniPaymentException e) {
            model.addAttribute("resultCount", 0);
        }
        addOpenClientDetails(model);
        return "pages/query-invoice";
    }

    /**
     * Add API Details to Model
     *
     * @param model {@link  Model}
     */
    private void addOpenClientDetails(Model model) {
        model.addAttribute("clientId", clientId);
        model.addAttribute("clientSecret", clientSecret);
        model.addAttribute("apiHost", apiHost);
    }
}
