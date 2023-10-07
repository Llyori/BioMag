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
            public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                //ici je récupère les 2 deux méthodes d'authentification et j'utilise celle valide
                Client client = clientService.findByEmail(userName);
                Client client1 = clientService.findByFirstName(userName);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                //si l'email est passé on l'utilise
                if(client != null){
                    client.getAppRoles().forEach(roles -> {
                        authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
                    });
                    if(client.isEnabled())
                        return new User(client.getEmail(), client.getPassword(), authorities);
                    else
                        return null;

                }
                //si le prénom est passé on l'utilise
                else if (client1 != null) {
                    client1.getAppRoles().forEach(roles -> {
                        authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
                    });
                    if(client1.isEnabled())
                        return new User(client1.getFirstName(), client1.getPassword(), authorities);
                    else
                        return null;
                }
                return null;
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/other/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error=true")
                .permitAll();
        http.authorizeHttpRequests()
                .antMatchers("/livre/**", "/index").authenticated()
                .anyRequest().permitAll();
    }
}
