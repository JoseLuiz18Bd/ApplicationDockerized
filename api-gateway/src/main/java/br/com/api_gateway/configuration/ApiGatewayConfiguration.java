//package br.com.api_gateway.configuration;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ApiGatewayConfiguration {
//	
//	@Bean
//	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(p -> p.path("/get")
////							.filters(f -> f
////								.addRequestHeader("Hello", "World")
////								.addRequestParameter("Hello", "World"))
//				        .uri("http://httpbin.org:80"))
//				.route(p -> p.path("/cambio-service/**") //todas as requisições para este serviço,
//						.uri("lb://cambio-service")) // será redirecionada para o loadballance deste mesmo serviço (eureka load ballance
//				.route(p -> p.path("/book-service/**")
//						.uri("lb://book-service"))
//				.build();
//	}
//
//}
