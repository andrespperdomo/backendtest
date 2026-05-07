package com.product.infrastructure.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.product.application.command.CreateProductCommand;
import com.product.application.command.ListProductCommand;
import com.product.application.usecase.CreateProductUseCase;
import com.product.application.usecase.GetProductUseCase;
import com.product.application.usecase.ListProductUseCase;
import com.product.domain.model.Product;
import com.product.infrastructure.web.controller.ProductController;
import com.product.infrastructure.web.mapper.ListProductMapper;
import com.product.infrastructure.web.mapper.ProductMapper;
import com.product.infrastructure.web.request.CreateProductRequest;
import com.product.infrastructure.web.request.ListProductRequest;
import com.product.infrastructure.web.response.Meta;
import com.product.infrastructure.web.response.ProductListResponse;
import com.product.infrastructure.web.response.ProductResponse;
import com.product.shared.utils.PageResult;

import io.quarkus.test.junit.TestProfile;

@ExtendWith(MockitoExtension.class)
@TestProfile(ProductDBProfile.class)
class ProductControllerTest {

    @Mock
    CreateProductUseCase createProductUseCase;

    @Mock
    GetProductUseCase getProductUseCase;

    @Mock
    ListProductUseCase listProductUseCase;

    @Mock
    ProductMapper mapper;

    @Mock
    ListProductMapper listProductMapper;

    @InjectMocks
    ProductController controller;

    // Test data
    CreateProductRequest createRequest;
    CreateProductCommand createCommand;
    Product product;

    ProductResponse productResponse;
    ProductResponse.Data productData;
    ProductResponse.Attributes productAttributes;

    Meta meta;

    @BeforeEach
    void setUp() {

        // =========================
        // REQUEST + COMMAND
        // =========================
        createRequest = new CreateProductRequest();
        createCommand = new CreateProductCommand(
                "Laptop",
                1000.0,
                "Gaming",
                1000.0);

        // =========================
        // DOMAIN OBJECT
        // =========================
        product = new Product(
                "1",
                "Laptop",
                "Gaming",
                1000.0,
                1000.0);

        // =========================
        // RESPONSE (FIXED SAFE BUILD)
        // =========================
        productAttributes = new ProductResponse.Attributes();
        productAttributes.name = "Laptop";
        productAttributes.price = new BigDecimal("1000.00");
        productAttributes.description = "Gaming laptop";

        productData = new ProductResponse.Data();
        productData.type = "product";
        productData.id = "1";
        productData.attributes = productAttributes;

        productResponse = new ProductResponse();
        productResponse.data = productData;

        // =========================
        // META
        // =========================
        meta = new Meta(200, 0, 10);
    }

    // ===============================
    // CREATE
    // ===============================
    @Test
    void shouldCreateProductSuccessfully() {

        when(mapper.toCommand(createRequest)).thenReturn(createCommand);
        when(createProductUseCase.execute(createCommand)).thenReturn(product);
        when(mapper.toResponse(product)).thenReturn(productResponse);

        Response response = controller.create(createRequest);

        assertEquals(201, response.getStatus());
        assertEquals(productResponse, response.getEntity());

        verify(mapper).toCommand(createRequest);
        verify(createProductUseCase).execute(createCommand);
        verify(mapper).toResponse(product);
    }

    // ===============================
    // GET BY ID
    // ===============================
    @Test
    void shouldReturnProductWhenFound() {

        when(getProductUseCase.execute("1")).thenReturn(Optional.of(product));
        when(mapper.toResponse(product)).thenReturn(productResponse);

        Response response = controller.getById("1");

        assertEquals(200, response.getStatus());
        assertEquals(productResponse, response.getEntity());

        verify(getProductUseCase).execute("1");
        verify(mapper).toResponse(product);
    }

    @Test
    void shouldReturn404WhenProductNotFound() {

        when(getProductUseCase.execute("1")).thenReturn(Optional.empty());

        Response response = controller.getById("1");

        assertEquals(404, response.getStatus());

        verify(getProductUseCase).execute("1");
        verifyNoInteractions(mapper);
    }

    // ===============================
    // LIST PRODUCTS
    // ===============================
    @Test
    void shouldReturnProductList() {

        ListProductRequest request = new ListProductRequest("laptop", 0, 10);

        ListProductCommand command = new ListProductCommand("laptop", 0, 10);

        PageResult<Product> pageResult = new PageResult<>(
                List.of(product),
                0,
                10,
                1);

        ProductListResponse listResponse = new ProductListResponse(
                List.of(productResponse),
                meta);

        when(listProductMapper.toCommand(request)).thenReturn(command);
        when(listProductUseCase.execute(command)).thenReturn(pageResult);
        when(listProductMapper.toResponse(pageResult)).thenReturn(listResponse);

        Response response = controller.getListProduct(request);

        assertEquals(200, response.getStatus());
        assertEquals(listResponse, response.getEntity());

        verify(listProductMapper).toCommand(request);
        verify(listProductUseCase).execute(command);
        verify(listProductMapper).toResponse(pageResult);
    }
}