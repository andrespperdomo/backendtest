package com.inventory.domain.model;

import com.inventory.infrastructure.persistence.InventoryEntity;

public class InventoryEntityMapper {

    public static InventoryEntity toEntity(Inventory inv) {
        InventoryEntity e = new InventoryEntity();
        // e.setId(inv.id());
        e.setProductId(inv.idProduct());
        e.setQuantity(inv.quantity());
        return e;
    }

    public static Inventory toDomain(InventoryEntity e) {
        return new Inventory(e.productId, e.quantity);
    }
}