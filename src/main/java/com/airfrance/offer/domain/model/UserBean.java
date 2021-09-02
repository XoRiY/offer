package com.airfrance.offer.domain.model;

import com.airfrance.offer.domain.common.validation.annotation.IsAdult;
import com.airfrance.offer.domain.common.validation.annotation.ValidCountry;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @apiNote  Model to use for exchange beetwin ihm/controller and repository
 *
 * @author Tahar Kerdoud
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
