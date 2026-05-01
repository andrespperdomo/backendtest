package com.product.infrastructure.web.controller;

import java.util.logging.Logger;
import com.product.application.command.CreateProductCommand;
import com.product.application.usecase.CreateProductUseCase;
import com.product.domain.model.Product;
//import com.product.dto.PaginatorDTO;
//import com.product.dto.ProductDTO;
//import com.product.infrastructure.web.mapper.ListProductMapper;
import com.product.infrastructure.web.mapper.ProductMapper;
import com.product.infrastructure.web.request.CreateProductRequest;
//import com.product.infrastructure.web.request.ListProductRequest;
//import com.product.infrastructure.web.response.ProductListResponse;
import com.product.infrastructure.web.response.ProductResponse;
import com.product.shared.utils.PageResult;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    CreateProductUseCase createProductUseCase;

    @Inject
    ProductMapper mapper;

    Logger LOG = Logger.getLogger(ProductController.class.getName());

    @POST
    public Response create(@Valid CreateProductRequest request) {
        CreateProductCommand command = mapper.toCommand(request);

        Product product = createProductUseCase.execute(command);
        ProductResponse response = mapper.toResponse(product);

        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

}
