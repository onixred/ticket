package my.pet.ticket.client;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class HttpSecurityConfig {

  @Bean
  public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
    return http.cors(withDefaults())
        .csrf(withDefaults())
        .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
        .formLogin(form -> form
            .loginPage("/login")
            .usernameParameter("email")
            .failureUrl("/login?loginError=true"))
        .logout(logout -> logout
            .logoutSuccessUrl("/login?logoutSuccess=true")
            .deleteCookies("JSESSIONID"))
        .exceptionHandling(exception -> exception
            .authenticationEntryPoint(
                new LoginUrlAuthenticationEntryPoint("/login?loginRequired=true")))
        .build();
  }

}