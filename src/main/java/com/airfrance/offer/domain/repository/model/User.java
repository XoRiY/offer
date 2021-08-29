package com.airfrance.offer.domain.repository.model;

import com.airfrance.offer.domain.model.Gender;
import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Setter
@Getter
@Entity(name = "USER")
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "name may not be Null")
    @NotEmpty(message = "name may not be Empty")
    private String name;

    @NotNull(message = "birthDate may not be null")
    @Past
    private LocalDate birthDate;

    @NotNull(message = "countryOfResidence may not be null")
    @NotBlank
    private String countryOfResidence;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Tolerate
    public User(){
        // to solve conflict with lombok builder
    }

}
