package com.product.infrastructure.unit;

import java.util.Map;

public class ProductDBProfile implements io.quarkus.test.junit.QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(
                "quarkus.datasource.jdbc.url",
                "jdbc:postgresql://localhost:5432/products_db",
                "quarkus.datasource.username",
                "postgres",
                "quarkus.datasource.password",
                "postgres");
    }
}
