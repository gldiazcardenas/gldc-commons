package com.gldc.commons.bean.validation.service;

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

    private final List<ValidationIssue> issues;

    public ValidatorException(ValidationIssue... issues) {
        this(Arrays.asList(issues));
    }

    public ValidatorException(Collection<ValidationIssue> issues) {
        super("[" + Joiner.on("],[").join(issues) + "]");
        this.issues = new ArrayList<>(issues);
    }

    public List<ValidationIssue> getIssues() {
        return issues;
    }
}
