package ma.enset.patients_manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.enset.patients_manager.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // find by name
    Page<Patient> findByNameContains(String keyword, Pageable pageable);
    // find by name using JPQL
    @Query("select p from Patient p where p.name like :x")
    Page<Patient> search(@Param("x") String keyword, Pageable pageable);
}