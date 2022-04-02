package io.unipayment.client.example.controller;

import io.unipayment.client.Configuration;
import io.unipayment.client.UniPaymentClient;
import io.unipayment.client.UniPaymentException;
import io.unipayment.client.example.dto.QueryInvoiceDto;
import io.unipayment.client.models.InvoiceModel;
import io.unipayment.client.models.QueryInvoiceRequest;
import io.unipayment.client.models.QueryResult;
import io.unipayment.client.models.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class UniPaymentRestController {

    @PostMapping(value = "query-invoice", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Response<QueryResult<InvoiceModel>> queryInvoice(QueryInvoiceDto queryInvoiceDto) {
        Configuration configuration = new Configuration();
        configuration.setAppId(queryInvoiceDto.getAppId());
        configuration.setApiKey(queryInvoiceDto.getApiKey());
        configuration.setBaseUrl(queryInvoiceDto.getApiHost());
        configuration.setApiVersion("1.0");
        configuration.setDebug(true);

        QueryInvoiceRequest queryInvoiceRequest = new QueryInvoiceRequest();
        queryInvoiceRequest.setOrderId(queryInvoiceDto.getOrderId());
        Response<QueryResult<InvoiceModel>> response;
        try {
            response = UniPaymentClient.getInstance(configuration).queryInvoices(configuration.getApiVersion(), queryInvoiceRequest);
        } catch (UniPaymentException e) {
            response = new Response<>();
            response.setData(new QueryResult<>());
        }
        return response;
    }

}
