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
		Patient patient1 = new Patient(null, "AAAA", new Date(), false, 123);
		Patient patient2 = new Patient(null, "BBBB", new Date(), false, 115);

		Patient patient3 = Patient.builder()
				.name("CCCC")
				.dateOfBirth(new Date())
				.score(155)
				.sick(true)
				.build();

		Patient patient4 = new Patient(null, "DDDD", new Date(), false, 145);
		Patient patient5 = new Patient(null, "EEEE", new Date(), false, 135);
		Patient patient6 = new Patient(null, "FFFF", new Date(), false, 125);

		// Saving patients to the repository
		patientRepository.save(patient1);
		patientRepository.save(patient2);
		patientRepository.save(patient3);
		patientRepository.save(patient4);
		patientRepository.save(patient5);
		patientRepository.save(patient6);

	}
}
