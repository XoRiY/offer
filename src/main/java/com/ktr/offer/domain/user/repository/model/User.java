package com.ktr.offer.domain.user.repository.model;

import com.ktr.offer.domain.user.validation.annotation.IsAdult;
import com.ktr.offer.domain.user.validation.annotation.ValidCountry;
import com.ktr.offer.domain.user.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Tahar Kerdoud
 * @apiNote Entity, used by repository to save/find object in/from DB
 */
@Setter
@Getter
@Entity(name = "USER")
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "name may not be Null")
    @NotEmpty(message = "name may not be Empty")
    private String name;

    @NotNull(message = "birthDate may not be null")
    @IsAdult
    private LocalDate birthDate;

    @NotNull(message = "countryOfResidence may not be null")
    @NotBlank
    @ValidCountry
    private String countryOfResidence;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Tolerate
    public User() {
        // to solve conflict with lombok builder
    }

}
