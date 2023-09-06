package GesBlio.bibliotheque;

import GesBlio.bibliotheque.entities.AppRoles;
import GesBlio.bibliotheque.entities.Client;
import GesBlio.bibliotheque.services.ClientService;
import GesBlio.bibliotheque.services.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class BibliothequeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliothequeApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start (ClientService clientService, RoleService roleService){
		return args -> {
			roleService.add(new AppRoles(null, "USER"));
			roleService.add(new AppRoles(null, "ADMIN"));
			roleService.add(new AppRoles(null, "SUPER_ADMIN"));

			//clientService.add(new Client(null, "Ulrich", "Bolan", 49966233949966233L, "ungapmen@gmail.com", "1234", "655335466",null, null, new ArrayList<>()));
			clientService.add(new Client(null, "Llyori", "Bill", 489662339000000000L, "sa", "1234", "655335466",null,null, false, new ArrayList<>()));

			clientService.addRoletoUser("sa", "SUPER_ADMIN");
			clientService.addRoletoUser("sa", "ADMIN");
			clientService.addRoletoUser("sa", "USER");
			//clientService.addRoletoUser("ungapmen@gmail.com", "USER");
		};
	}

}
