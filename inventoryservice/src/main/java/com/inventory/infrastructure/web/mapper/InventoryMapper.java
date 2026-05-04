package com.inventory.infrastructure.web.mapper;

import com.inventory.application.command.UpdateInventoryCommand;
import com.inventory.domain.model.Inventory;
import com.inventory.infrastructure.web.request.UpdateInventoryRequest;
import com.inventory.infrastructure.web.response.InventoryResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface InventoryMapper {
    UpdateInventoryCommand toCommand(UpdateInventoryRequest request);

    // 🔹 Domain → JSON API Response
    @Mapping(target = "data.type", constant = "Inventory")
    @Mapping(target = "data.id", source = "id")
    @Mapping(target = "data.attributes.idProduct", source = "idProduct")
    @Mapping(target = "data.attributes.quantity", source = "quantity")
    InventoryResponse toResponse(Inventory product);
}
