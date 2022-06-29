package com.yyd.yyd.config;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.yyd.yyd.constants.icode.RestRespCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger3Config {

//    @Bean
//    public Docket defaultApi2() {
//        //设置请求头
////        List<RequestParameter> pars = new ArrayList<>();
////        RequestParameterBuilder builder = new RequestParameterBuilder();
////        pars.add(builder.name("orgId").description("机构或学校ID").in(ParameterType.HEADER).deprecated(false).build());
////        pars.add(builder.name("userId").description("用户ID").in(ParameterType.HEADER).deprecated(false).build());
//
//        return new Docket(DocumentationType.OAS_30)
//                .useDefaultResponseMessages(false)
//                .globalResponses(HttpMethod.GET, initGlobalResp())
//                .globalResponses(HttpMethod.POST, initGlobalResp())
//                .enable(true)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build();
////                .globalRequestParameters(pars);
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("yyd")
//                .description("yyd")
//                .version("1.0")
//                .build();
//    }
//
//    private List<Response> initGlobalResp() {
//        return Arrays.stream(RestRespCode.values()).map(x -> new ResponseBuilder()
//                .code(x.getCode())
//                .description(x.getMessage())
//                .build()).collect(Collectors.toList());
//    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yyd.yyd"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("yyd新系统")
                        .description("用于新项目")
                        .version("1.0")
                        .contact(new Contact("yyd", "https:www.baidu.com", "116217017@qq.com")
                        )
                        .license("The Apache License")
                        .licenseUrl("https:www.baidu.com")
                        .build());
    }
//    @Bean(value = "defaultApi2")
//    public Docket defaultApi2() {
//        Docket docket=new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
//                        .description("# swagger-bootstrap-ui-demo RESTful APIs")
//                        .termsOfServiceUrl("http://www.xx.com/")
//                        .contact(new Contact("yyd","https:www.baidu.com","116217017@qq.com"))
//                        .version("1.0")
//                        .build())
//                //分组名称
//                .groupName("2.X版本")
//                .select()
//                //这里指定Controller扫描包路径
//                .apis(RequestHandlerSelectors.basePackage("com.yyd.yyd"))
//                .paths(PathSelectors.any())
//                .build();
//        return docket;
//    }
//    @Bean(value = "defaultApi2")
//    public Docket defaultApi2() {
//        ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2)
//                .enableUrlTemplating(false)
//                .apiInfo(apiInfo())
//                // 选择那些路径和api会生成document
//                .select()
//                // 对所有api进行监控
//                .apis(RequestHandlerSelectors.any())
//                //这里可以自定义过滤
//                .paths(this::filterPath);
//
//        return builder.build();
//    }
//
//    private boolean filterPath(String path) {
//        boolean ret = path.endsWith("/error");
//        if (ret) {
//            return false;
//        }
//        //这块可以写其他的过滤逻辑
//        return true;
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("title")
//                .description("description")
//                .termsOfServiceUrl("https://www.baidu.com")
//                .version("1.0")
//                .contact(new Contact("nitric oxide", "www.baidu.com", "123@qq.com"))
//                .build();
//    }
}
