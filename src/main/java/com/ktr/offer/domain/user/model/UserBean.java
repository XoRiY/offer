package com.ktr.offer.domain.user.model;

import com.ktr.offer.domain.user.validation.annotation.IsAdult;
import com.ktr.offer.domain.user.validation.annotation.ValidCountry;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * @author Tahar Kerdoud
 * @apiNote Model to use for exchange beetwin ihm/controller and repository
 */
@Setter
@Getter
@AllArgsConstructor
@Builder
public class UserBean {

    @Positive
    private Long id;

    @NotBlank(message = "Name may not be Null or Blank")
    private String name;

    @NotNull(message = "Birth Date may not be Null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @IsAdult
    private LocalDate birthDate;

    @NotBlank(message = "Country Of Residence may not be Null or Blank")
    @ValidCountry
    private String countryOfResidence;

    private String phoneNumber;

    @JsonEnumDefaultValue
    private Gender gender;


}
