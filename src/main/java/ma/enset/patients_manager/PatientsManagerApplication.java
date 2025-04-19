package ma.enset.patients_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.enset.patients_manager.entities.Patient;
import ma.enset.patients_manager.repository.PatientRepository;
import ma.enset.patients_manager.security.service.AccountService;

import java.util.Date;

@SpringBootApplication
public class PatientsManagerApplication {
	// @Autowired
	// private PatientRepository patientRepository;
	// @Autowired
	// private JdbcUserDetailsManager jdbcUserDetailsManager;
	// @Autowired
	// private AccountService accountService;


	public static void main(String[] args) {
		SpringApplication.run(PatientsManagerApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// 	// Creating and saving patients using different methods
	// 	Patient patient1 = new Patient(null, "AAAA", new Date(), false, 123);
	// 	Patient patient2 = new Patient(null, "BBBB", new Date(), false, 115);

	// 	Patient patient3 = Patient.builder()
	// 			.name("CCCC")
	// 			.dateOfBirth(new Date())
	// 			.score(155)
	// 			.sick(true)
	// 			.build();

	// 	Patient patient4 = new Patient(null, "DDDD", new Date(), false, 145);
	// 	Patient patient5 = new Patient(null, "EEEE", new Date(), false, 135);
	// 	Patient patient6 = new Patient(null, "FFFF", new Date(), false, 125);

	// 	// Saving patients to the repository
	// 	patientRepository.save(patient1);
	// 	patientRepository.save(patient2);
	// 	patientRepository.save(patient3);
	// 	patientRepository.save(patient4);
	// 	patientRepository.save(patient5);
	// 	patientRepository.save(patient6);

	// 	PasswordEncoder passwordEncoder = passwordEncoder();

	// 	try {
	// 		if (!jdbcUserDetailsManager.userExists("user")) {
	// 			jdbcUserDetailsManager.createUser(
	// 					User.withUsername("user")
	// 							.password(passwordEncoder.encode("1234"))
	// 							.roles("USER")
	// 							.build());
	// 		}
			
	// 		if (!jdbcUserDetailsManager.userExists("admin")) {
	// 			jdbcUserDetailsManager.createUser(
	// 					User.withUsername("admin")
	// 							.password(passwordEncoder.encode("1234"))
	// 							.roles("USER", "ADMIN")
	// 							.build());
	// 		}
	// 	} catch (Exception e) {
	// 		System.err.println("user already exists");
	// 	}

	

	// }

	@Bean
	public CommandLineRunner start(AccountService accountService) {
		return args -> {
				accountService.addNewRole("USER");
				accountService.addNewRole("ADMIN");
				accountService.addNewUser("admin", "1234", "admin@email.com" , "1234");
				accountService.addNewUser("user", "1234", "user@email.com" , "1234");
				accountService.addRoleToUser("admin", "ADMIN");
				accountService.addRoleToUser("admin", "USER");
				accountService.addRoleToUser("user", "USER");
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
