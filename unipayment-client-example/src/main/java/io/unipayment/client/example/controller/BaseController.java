package io.unipayment.client.example.controller;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseController {
    @Value("${clientId}")
    protected String clientId;
    @Value("${clientSecret}")
    protected String clientSecret;
    @Value("${apiHost}")
    protected String apiHost;
    @Value("${apiVersion}")
    protected String apiVersion;
    @Value("${appId}")
    protected String appId;
}
