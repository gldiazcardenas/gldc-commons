package com.gldc.commons.http.server.exception;

import com.gldc.commons.bean.validation.service.ValidatorException;
import com.gldc.commons.http.ValidationResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Mapper for validation exceptions.
 *
 * @author Gabriel Diaz, 21/12/2020.
 */
public class ValidatorExceptionMapper implements ExceptionMapper<ValidatorException> {

    @Override
    public Response toResponse(ValidatorException e) {
        ValidationResponse response = new ValidationResponse(e.getValidation());
        return Response.status(response.getStatusCode()).entity(response).build();
    }

}
