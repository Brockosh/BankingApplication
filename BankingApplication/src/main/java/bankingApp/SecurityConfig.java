//package bankingApp;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
////@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests(authorizeRequests ->
////                        authorizeRequests
////                                .antMatchers("/", "/error", "/webjars/**").permitAll()
////                                .anyRequest().authenticated()
////                )
////                .oauth2Login(withDefaults());
//    }
//}