package com.inventory.infrastructure.web.response;

public class InventoryResponse {

    public Data data;

    public static class Data {
        public String type;
        public String id;
        public Attributes attributes;
    }

    public static class Attributes {
        public String idProduct;
        public Integer quantity;

    }

}
