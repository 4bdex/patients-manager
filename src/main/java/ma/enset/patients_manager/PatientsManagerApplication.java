package ma.enset.patients_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ma.enset.patients_manager.entities.Patient;
import ma.enset.patients_manager.repository.PatientRepository;
import java.util.Date;


@SpringBootApplication
public class PatientsManagerApplication implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(PatientsManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Creating and saving patients using different methods
		Patient patient1 = new Patient(null, "A", new Date(), false, 123);
		Patient patient2 = new Patient(null, "B", new Date(), false, 115);
		Patient patient3 = Patient.builder()
				.name("C")
				.dateOfBirth(new Date())
				.score(155)
				.isSick(true)
				.build();

		// Saving patients to the repository
		patientRepository.save(patient1);
		patientRepository.save(patient2);
		patientRepository.save(patient3);

	}
}
