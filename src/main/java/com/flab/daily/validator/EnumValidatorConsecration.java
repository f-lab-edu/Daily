package com.flab.daily.validator;

import com.flab.daily.type.MemberType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class EnumValidatorConsecration implements ConstraintValidator<EnumValidator, Enum<?>> {


    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value instanceof MemberType)
        {
            return true;
        }
        return false;
    }
}
