package io.unipayment.sdk.client.encoder;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.jackson.JacksonEncoder;
import io.unipayment.sdk.core.Constants;
import io.unipayment.sdk.exception.UnipaymentSdkException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.StringJoiner;

public final class JsonEncoder extends JacksonEncoder {

    private final Validator validator;

    public JsonEncoder() {
        super(Constants.getMapper());
        try (ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory()) {
            this.validator = validationFactory.getValidator();
        } catch (Exception e) {
            throw new UnipaymentSdkException(e.getMessage(), e);
        }
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (object instanceof String) {
            template.body((String) object);
        } else {
            Set<ConstraintViolation<Object>> constraints = validator.validate(object);
            if (!constraints.isEmpty()) {
                StringJoiner joiner = new StringJoiner(", ");
                constraints.forEach(e -> joiner.add(e.getPropertyPath() + " " + e.getMessage()));
                throw new UnipaymentSdkException("Invalid Request payload. Reason: " + joiner);
            }
            super.encode(object, bodyType, template);
        }
    }
}
