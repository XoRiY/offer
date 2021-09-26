package com.ktr.offer.domain.user.validation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeValidator implements ConstraintValidator<IsAdult, LocalDate> {

    @Override
    public void initialize(IsAdult constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        return birthDate != null && ChronoUnit.YEARS.between(birthDate, LocalDate.now()) > 18;
    }
}
