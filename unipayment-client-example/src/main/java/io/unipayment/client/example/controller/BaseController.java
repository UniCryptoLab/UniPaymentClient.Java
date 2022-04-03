package io.unipayment.client.example.controller;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseController {
    @Value("${app.id}")
    protected String appId;
    @Value("${api.key}")
    protected String apiKey;
    @Value("${api.host}")
    protected String apiHost;
    @Value("${api.version}")
    protected String apiVersion;
}
