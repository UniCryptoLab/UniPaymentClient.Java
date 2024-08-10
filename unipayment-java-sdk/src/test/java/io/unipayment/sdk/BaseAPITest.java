package io.unipayment.sdk;

import io.unipayment.sdk.core.config.Configuration;
import io.unipayment.sdk.core.config.PropertyConfiguration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class BaseAPITest {
    protected static final Configuration configuration = new PropertyConfiguration();
}
