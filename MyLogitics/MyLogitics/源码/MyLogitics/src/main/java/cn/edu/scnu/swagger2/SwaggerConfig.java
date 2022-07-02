package cn.edu.scnu.swagger2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //开启Swagger2的自动配置
public class SwaggerConfig {
	 /**
     * 创建一个Docket对象
     * 调用select()方法，
     * 生成ApiSelectorBuilder对象实例，该对象负责定义外漏的API入口
     * 通过使用RequestHandlerSelectors和PathSelectors来提供Predicate，在此我们使用any()方法，将所有API都通过Swagger进行文档管理
     * @return
     */
    @Bean
    public Docket createRestApi() {
    	// 设置要显示swagger的环境(需要入参Environment environment)
//    	   Profiles of = Profiles.of("dev", "test");
//    	   // 判断当前是否处于该环境
//    	   // 通过 enable() 接收此参数判断是否要显示
//    	   boolean b = environment.acceptsProfiles(of);
    	 //在配置好的配置类中增加此段代码即可
    	ParameterBuilder ticketPar = new ParameterBuilder();
    	List<Parameter> pars = new ArrayList<Parameter>();
    	ticketPar.name("tokenName").description("登录校验")//name表示名称，description表示描述
    	.modelRef(new ModelRef("string")).parameterType("header")
    	.required(false).defaultValue("Bearer ").build();//required表示是否必填，defaultvalue表示默认值
    	pars.add(ticketPar.build());//添加完此处一定要把下边的带***的也加上否则不生效

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true) //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("cn.edu.scnu.controller"))
                // 配置如何通过path过滤,即这里只扫描请求以/kuang开头的接口
                //.paths(PathSelectors.ant("/kuang/**"))
//                any() // 任何请求都扫描
//                none() // 任何请求都不扫描
//                regex(final String pathRegex) // 通过正则表达式控制
//                ant(final String antPattern) // 通过ant()控制
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);//************把消息头添加
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                //简介
                .description("xfeng")
                //服务条款
                .termsOfServiceUrl("服务条款:无")
                //作者个人信息
                .contact(new Contact("xfeng","","382055041@qq.com"))
                //版本
                .version("1.0")
                .build();
    }


}
