package my.pet.ticket.client.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
    return http.formLogin(httpSecurityFormLoginConfigurer -> {
      httpSecurityFormLoginConfigurer.loginPage("/login.html").failureUrl("/login?failure=true");
    }).logout(httpSecurityLogoutConfigurer -> {
      httpSecurityLogoutConfigurer.logoutSuccessUrl("/login?logout=true");
    }).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
      authorizationManagerRequestMatcherRegistry.requestMatchers("*").permitAll();
    }).build();
  }
}
