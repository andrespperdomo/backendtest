package com.inventory.infrastructure.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;

@OpenAPIDefinition(info = @Info(title = "Product API", version = "1.0", description = "Hexagonal Architecture Microservice"))
@SecurityScheme(securitySchemeName = "apiKey", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, apiKeyName = "X-API-KEY")
public class OpenApiConfig {
}