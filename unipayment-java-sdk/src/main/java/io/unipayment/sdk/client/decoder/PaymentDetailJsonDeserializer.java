package io.unipayment.sdk.client.decoder;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.unipayment.sdk.core.Constants;
import io.unipayment.sdk.model.BankPaymentMethodDetail;
import io.unipayment.sdk.model.CryptoPaymentMethodDetail;
import io.unipayment.sdk.model.InternalPaymentMethodDetail;
import io.unipayment.sdk.model.PaymentMethodDetail;

import java.io.IOException;

public final class PaymentDetailJsonDeserializer extends StdDeserializer<PaymentMethodDetail> {

    public PaymentDetailJsonDeserializer() {
        this(null);
    }

    public PaymentDetailJsonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public PaymentMethodDetail deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String detail = node.toString();
        ObjectMapper mapper = Constants.getMapper();
        if (node.get("uid") != null) {
            return mapper.readValue(detail, InternalPaymentMethodDetail.class);
        } else if (node.get("address") != null) {
            return mapper.readValue(detail, CryptoPaymentMethodDetail.class);
        } else {
            return mapper.readValue(detail, BankPaymentMethodDetail.class);
        }
    }
}
