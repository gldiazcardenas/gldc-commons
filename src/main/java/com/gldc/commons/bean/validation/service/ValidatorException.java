package com.gldc.commons.bean.validation.service;

import com.gldc.commons.bean.validation.Validation;
import com.gldc.commons.bean.validation.ValidationIssue;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Exception thrown when a validation error occurs.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public final class ValidatorException extends RuntimeException {

    private final Validation validation;

    public ValidatorException(Validation validation) {
        super(validation.toString());
        this.validation = validation;
    }

    public Validation getValidation() {
        return validation;
    }

}
