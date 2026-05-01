package com.product.infrastructure.web.mapper;

import com.product.application.command.CreateProductCommand;
import com.product.domain.model.Product;
import com.product.infrastructure.web.request.CreateProductRequest;
import com.product.infrastructure.web.response.ProductResponse;
import jakarta.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-01T17:33:03-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@ApplicationScoped
public class ProductMapperImpl implements ProductMapper {

    @Override
    public CreateProductCommand toCommand(CreateProductRequest request) {
        if ( request == null ) {
            return null;
        }

        String name = null;
        Double price = null;
        String description = null;

        name = request.name;
        if ( request.price != null ) {
            price = request.price.doubleValue();
        }
        description = request.description;

        Double cost = null;

        CreateProductCommand createProductCommand = new CreateProductCommand( name, price, description, cost );

        return createProductCommand;
    }

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.data = productToData( product );

        return productResponse;
    }

    protected ProductResponse.Attributes productToAttributes(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse.Attributes attributes = new ProductResponse.Attributes();

        attributes.name = product.name();
        if ( product.price() != null ) {
            attributes.price = BigDecimal.valueOf( product.price() );
        }
        attributes.description = product.description();

        return attributes;
    }

    protected ProductResponse.Data productToData(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse.Data data = new ProductResponse.Data();

        data.attributes = productToAttributes( product );
        data.id = product.id();

        data.type = "products";

        return data;
    }
}
