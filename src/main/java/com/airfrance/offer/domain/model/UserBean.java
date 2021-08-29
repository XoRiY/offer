package com.airfrance.offer.domain.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


import javax.validation.constraints.*;
import java.time.LocalDate;

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
    private LocalDate birthDate;

    @NotBlank(message = "Country Of Residence may not be Null or Blank")
    private String countryOfResidence;

    private String phoneNumber;

    @JsonEnumDefaultValue
    private Gender gender;


}
