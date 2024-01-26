package utn.frc.backend.curso3k2.TPIntegradorGrupo19.apiGateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class GWConfig {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${api-gw.url-microservicio-estaciones}") String uriEstaciones,
                                        @Value("${api-gw.url-microservicio-alquileres}") String uriAlquileres) {
        return builder.routes()

                .route(p -> p.path("/api/alquileres/**").uri(uriAlquileres))
                .route(p -> p.path("/api/estaciones/**").uri(uriEstaciones))
                .build();

    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws
            Exception {
        http.authorizeExchange(exchanges -> exchanges

                        .pathMatchers(HttpMethod.POST, "/api/estaciones/**")
                        .hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/api/alquileres/**")
                        .hasRole("ADMIN")

                        .pathMatchers(HttpMethod.GET, "/api/estaciones/**")
                        .hasRole("CLIENTE")
                        .pathMatchers(HttpMethod.POST, "/api/alquileres/**")
                        .hasRole("CLIENTE")

                        .anyExchange()
                        .authenticated()
                ).oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(Customizer.withDefaults()))
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
                new ReactiveJwtGrantedAuthoritiesConverterAdapter(grantedAuthoritiesConverter));
        return jwtAuthenticationConverter;
    }


}
