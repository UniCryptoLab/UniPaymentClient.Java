# UniPayment Java SDK

[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](https://github.com/UniCryptoLab/UniPaymentClient.Python/blob/main/UniPaymentClient/LICENSE.txt)

A Java SDK for the [UniPayment APIs](https://unipayment.readme.io/reference/overview).

This SDK provides a convenient abstraction of UniPayment's Gateway API and allows developers to focus on payment
flow/e-commerce integration rather than on the specific details of client-server interaction using the raw API.

## Getting Started

[Integrate Tutorial](https://help.unipayment.io/en/articles/7851188-integrate-with-payment-gateway)

Before using the UniPayment API, sign up for your [API key](https://console.unipayment.io/).

You can also use our test tokens for testing and
integration. [Documentation](https://help.unipayment.io/en/articles/8263248-how-to-use-testcoin).

## Installation

### Manual

1. Download the package and extract it into a local directory or clone the repo.
2. Copy unipayment-java-sdk.
3. Build the project using maven
```sh
cd <project_directory>//UniPaymentClient.Java
mvn clean install
```

4. To use the SDK in your project you will need to add the dependencies to your maven pom.xml file
```xml
<dependency>
    <groupId>io.unipayment</groupId>
    <artifactId>unipayment-java-sdk</artifactId>
    <version>2.0.0</version>
</dependency>
```

## UniPayment SDK Configuration

You can load the configurations using the following options:

1. Programmatically - Initializing the Configuration Object

```Java
import io.unipayment.sdk.core.config.Configuration;
import io.unipayment.client.UniPaymentClient;

Configuration configuration = Configuration.builder()
        .clientId("client id")
        .clientSecret("client secret")
        .host("https://sandbox-api.unipayment.io")
        .apiVersion("1.0")
        .appId("app id")
        .debug(true) //If you wish to print the request/response logs
        .build();
```

2. Loading from properties file.

The name of the property file should be **unipayment-sdk.properties** which can be placed in the user directory

    ######## API Credentials for the SDK
    unipayment.api.host=
    unipayment.api.version=1.0
    unipayment.api.clientId=
    unipayment.api.clientSecret=
    unipayment.api.appId=
    
    ######## Debug Configuration
    unipayment.api.debug=false

3. Loading configurations from another path.

You will need to create a PropertyConfiguration object and pass the file path or an FileInputStream

```Java
import io.unipayment.sdk.core.config;

import java.io.File;

PropertyConfiguration configuration = new PropertyConfiguration(new File("<ABSOLUTE_PATH_OF_PROPERTY_FILE>"));
```

## Authentication

> Reference：https://unipayment.readme.io/reference/authentication

### Obtaining An Access Token

To authenticate your application, you need to obtain an access token by making a request to our OAuth 2.0 token
endpoint. This request must include your client_id, client_secret, and the grant_type.

> How to obtain an access token: https://unipayment.readme.io/reference/access-token

Note: If access token is expired, an exception will be thrown by the SDK.

## Create an invoice

> Reference：https://unipayment.readme.io/reference/create_invoice

```java
import io.unipayment.sdk.model.ApiResponse;
import io.unipayment.sdk.model.Invoice;
import io.unipayment.sdk.model.*;

BillingAPI billingAPI  = BillingAPI.getInstance(configuration);
CreateInvoiceRequest createInvoiceRequest = CreateInvoiceRequest.builder()
        .appId(configuration.getAppId())
        .priceAmount(2.0)
        .priceCurrency("USD")
        .orderId(orderId)
        .lang("en")
        .extArgs("Merchant Pass Through Data")
        .build();

ApiResponse<Invoice> apiResponse = billingAPI.createInvoice(createInvoiceRequest);
if(apiResponse.getCode().equals("OK")){
    // handle business logic
}
```

### Create Invoice Response

```json
{
  "msg": "",
  "code": "OK",
  "data": {
    "app_id": "df01ae1f-8c31-4ecd-8ab1-9e31289d4823",
    "payment_method_type": "UNKNOWN",
    "invoice_id": "88144dSLPujPsUJYakkJdx",
    "order_id": "ORDER_1721023791051",
    "price_amount": 2.0,
    "price_currency": "USD",
    "network": null,
    "address": null,
    "pay_amount": 0.0,
    "pay_currency": null,
    "exchange_rate": 0.0,
    "paid_amount": 0.0,
    "refunded_price_amount": 0.0,
    "create_time": "2024-07-15T06:09:54",
    "expiration_time": "2024-07-15T06:29:54",
    "confirm_speed": "Medium",
    "status": "New",
    "error_status": "None",
    "invoice_url": "https://sandbox.api.unipayment.com/i/88144dSLPujPsUJYakkJdx"
  }
}
```

## Handle IPN

> Reference：https://unipayment.readme.io/reference/ipn-check

> Invoice Status: https://unipayment.readme.io/reference/invoice-status

IPNs (Instant Payment Notifications) are sent to the notify_url when order status is changed to paid, confirmed and
complete.

```java
import io.unipayment.sdk.model.ApiResponse;
import io.unipayment.sdk.model.Invoice;
import io.unipayment.sdk.model.*;

CommonAPI commonAPI = CommonAPI.getInstance(configuration);

String notify = "ipn notify from unipayment";
ApiResponse<Invoice> apiResponse = commonAPI.checkIPN(notify);
if(apiResponse.getCode().equals("OK")){
    // handle business logic
}
```

IPN notify

``` json
{
  "ipn_type": "invoice",
  "event": "invoice_expired",
  "app_id": "cee1b9e2-d90c-4b63-9824-d621edb38012",
  "invoice_id": "3Q7fyLnB2YNhUDW1fFNyEz",
  "order_id": "20",
  "price_amount": 6.0,
  "price_currency": "SGD",
  "network": null,
  "address": null,
  "pay_currency": null,
  "pay_amount": 0.0,
  "exchange_rate": 0.0,
  "paid_amount": 0.0,
  "confirmed_amount": 0.0,
  "refunded_price_amount": 0.0,
  "create_time": "2022-09-12T03:36:03",
  "expiration_time": "2022-09-12T03:41:03",
  "status": "Expired",
  "error_status": "None",
  "ext_args": null,
  "transactions": null,
  "notify_id": "8ccd2b61-226b-48e5-99b8-acb1f350313e",
  "notify_time": "2022-09-12T03:56:10.5852752Z"
}
```

## Run Example

1. Get source code form GitHub

```bash
git clone https://github.com/UniCryptoLab/UniPaymentClient.Java.git
```

2. Run example in Intellij IDE

## License

MIT License

Copyright (c) 2024 UniPayment

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.