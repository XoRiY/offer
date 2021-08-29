package com.airfrance.offer.domain.common;


import javax.naming.OperationNotSupportedException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.stream.Collectors;

public class OfferValidation {


    static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    static Validator validator = factory.getValidator();

    public static <T> List<String> validate(T t) {

        if (t == null ){
            return List.of("object may not be null");
        }

        return validator.validate(t).stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    private OfferValidation() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

}
