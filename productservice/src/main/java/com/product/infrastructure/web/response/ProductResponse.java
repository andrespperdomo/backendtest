package com.product.infrastructure.web.response;

import java.math.BigDecimal;

public class ProductResponse {

    public Data data;

    public static class Data {
        public String type;
        public String id;
        public Attributes attributes;
    }

    public static class Attributes {
        public String name;
        public BigDecimal price;
        public String description;
    }

}
