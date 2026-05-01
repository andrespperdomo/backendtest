package com.product.infrastructure.config;
<<<<<<< HEAD
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
=======

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

>>>>>>> feature/create-product
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;

<<<<<<< HEAD
@OpenAPIDefinition(
    info = @Info(
        title = "Product API",
        version = "1.0",
        description = "Advanced Product Microservice"
    )
)
@SecurityScheme(
    securitySchemeName = "apiKey",
    type = SecuritySchemeType.APIKEY,
    in = SecuritySchemeIn.HEADER,
    apiKeyName = "X-API-KEY"
)
=======
@OpenAPIDefinition(info = @Info(title = "Product API", version = "1.0", description = "Hexagonal Architecture Microservice"))
@SecurityScheme(securitySchemeName = "apiKey", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, apiKeyName = "X-API-KEY")
>>>>>>> feature/create-product
public class OpenApiConfig {
}