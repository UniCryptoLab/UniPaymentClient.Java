package io.unipayment.client.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "UniPayment APIs", version = "1.0",
        license = @License(name = "UniPayment", url = "https://unipayment.readme.io/reference/overview")))
public class UniPaymentClientExample {

    public static void main(String[] args) {
        SpringApplication.run(UniPaymentClientExample.class, args);
    }

}
