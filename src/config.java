
@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Autoriser les origines suivantes
        config.addAllowedOrigin("http://127.0.0.1:5500");
        config.addAllowedOrigin("http://localhost:5500");
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://127.0.0.1:3000");

        // Autoriser toutes les méthodes HTTP
        config.addAllowedMethod("");

        // Autoriser tous les headers
        config.addAllowedHeader("");

        // Autoriser les credentials (cookies, auth headers)
        config.setAllowCredentials(true);

        // Appliquer cette configuration à toutes les routes /api/*
        source.registerCorsConfiguration("/api/", config);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0); // Exécuter avant le filtre JWT (ordre 1)
        return bean;
    }
}