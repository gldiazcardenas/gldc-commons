package com.gldc.commons.bean.validation;

import com.gldc.commons.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Result of the validation process.
 * @author Gabriel Diaz, 17/12/2020.
 */
public interface Validation {

    List<ValidationIssue> getIssues();

    default List<FieldIssue> getFieldIssues() {
        return getIssues().stream()
                .filter(i -> i instanceof FieldIssue)
                .map(i -> (FieldIssue) i)
                .collect(Collectors.toList());
    }

    default boolean isEmpty() {
        return CollectionUtils.isEmpty(getIssues());
    }

    default boolean hasAnyError() {
        return getIssues().stream()
                .anyMatch(i -> i.getType() == ValidationIssue.Type.ERROR);
    }

    static Builder builder() {
        return new Builder();
    }

    class Builder {

        private final List<ValidationIssue> issues = new ArrayList<>();

        private Builder() {
            super();
        }

        public Builder merge(Validation validation) {
            this.issues.addAll(validation.getIssues());
            return this;
        }

        public Builder add(ValidationIssue issue) {
            this.issues.add(issue);
            return this;
        }

        public Builder addError(String message) {
            return add(ValidationIssue.error(message));
        }

        public Builder addWarning(String message) {
            return add(ValidationIssue.warning(message));
        }

        public Builder addFieldError(String field, String message) {
            return add(FieldIssue.error(field, message));
        }

        public Builder addFieldWarning(String field, String message) {
            return add(FieldIssue.warning(field, message));
        }

        public Validation build() {
            return () -> issues;
        }

    }

}