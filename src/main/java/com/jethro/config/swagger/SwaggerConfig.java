package com.jethro.config.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: 沈佳俊
 * @CreateTime: 2019-05-05
 * @Description: swagger配置
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {
    @Value("${swagger.enabled}")
    Boolean swaggerEnabled;
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${swagger.info.title}")
    private String swaggerTitle;
    @Value("${swagger.info.desc}")
    private String desc;
    @Value("${swagger.info.termsOfServiceUrl}")
    private String termsOfServiceUrl;
    @Value("${swagger.info.version}")
    private String version;
    @Value("${swagger.info.contact.name}")
    private String contactName;
    @Value("${swagger.info.contact.url}")
    private String contactUrl;
    @Value("${swagger.info.contact.email}")
    private String contactEmail;
    @Value("${swagger.info.basePackage}")
    private String basePackage;

    public SwaggerConfig() {
    }

    @Bean
    public Docket createRestApi() {
        return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.apiInfo()).enable(this.swaggerEnabled.booleanValue()).select().apis(RequestHandlerSelectors.basePackage(this.basePackage)).apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).title(this.swaggerTitle).description(this.desc).termsOfServiceUrl(this.termsOfServiceUrl).contact(new Contact(this.contactName, this.contactUrl, this.contactEmail)).version(this.version).build();
    }

    public Boolean getSwaggerEnabled() {
        return this.swaggerEnabled;
    }

    public SwaggerConfig setSwaggerEnabled(Boolean swaggerEnabled) {
        this.swaggerEnabled = swaggerEnabled;
        return this;
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public SwaggerConfig setApplicationName(String applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    public String getSwaggerTitle() {
        return this.swaggerTitle;
    }

    public SwaggerConfig setSwaggerTitle(String swaggerTitle) {
        this.swaggerTitle = swaggerTitle;
        return this;
    }

    public String getDesc() {
        return this.desc;
    }

    public SwaggerConfig setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getTermsOfServiceUrl() {
        return this.termsOfServiceUrl;
    }

    public SwaggerConfig setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
        return this;
    }

    public String getVersion() {
        return this.version;
    }

    public SwaggerConfig setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getContactName() {
        return this.contactName;
    }

    public SwaggerConfig setContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public String getContactUrl() {
        return this.contactUrl;
    }

    public SwaggerConfig setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
        return this;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public SwaggerConfig setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public String getBasePackage() {
        return this.basePackage;
    }

    public SwaggerConfig setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }
}
