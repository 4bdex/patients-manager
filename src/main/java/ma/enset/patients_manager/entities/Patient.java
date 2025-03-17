package ma.enset.patients_manager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 4, max = 40, message = "Name must be between 4 and 40 characters")
    private String name;

    @NotNull(message = "Date of birth cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private boolean isSick;

    @DecimalMin(value = "100", message = "Score must be at least 100")
    private int score;
}