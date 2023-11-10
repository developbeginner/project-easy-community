package developbeginner.projecteasycommunity.config;

import developbeginner.projecteasycommunity.service.MemberDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private MemberDetailService memberDetailService;

    public SecurityConfig(MemberDetailService memberDetailService) {
        this.memberDetailService = memberDetailService;
    }

    private static final String[] LINKS_ANONYMOUS = {"/member/login", "/member/register"};
    private static final String[] LINKS_ADMIN = {};
    private static final String[] LINKS_USER = {};
    private static final String[] LINKS_PUBLIC = {"/home"};

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(LINKS_ADMIN).hasRole("ADMIN")
                        .requestMatchers(LINKS_USER).hasRole("USER")
                        .requestMatchers(LINKS_ANONYMOUS).anonymous()
                        .requestMatchers(LINKS_PUBLIC).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((login) -> login
                        .loginPage("/member/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler((request, response, authentication) -> {
                            response.sendRedirect("/blog/home");
                        })
                        .failureUrl("/member/login")
                )
                .logout((logout) -> logout
                        .logoutUrl("/member/logout")
                        .logoutSuccessUrl("/member/login")
                )
                .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(memberDetailService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
}
