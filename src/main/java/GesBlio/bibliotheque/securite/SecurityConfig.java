package GesBlio.bibliotheque.securite;

import GesBlio.bibliotheque.entities.Client;
import GesBlio.bibliotheque.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private ClientService clientService;

    public SecurityConfig(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Client client = clientService.findByEmail(email);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                client.getAppRoles().forEach(roles -> {
                    authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
                });
                return new User(client.getEmail(), client.getPassword(), authorities);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .permitAll();
        http.authorizeHttpRequests()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll();
    }
}
