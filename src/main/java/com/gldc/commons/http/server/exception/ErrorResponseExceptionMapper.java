package com.gldc.commons.http.server.exception;

import com.gldc.commons.http.ErrorResponse;
import com.gldc.commons.http.client.exception.ErrorResponseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Exception mapper for {@link ErrorResponseException} errors.
 *
 * @author Gabriel Diaz, 21/12/2020.
 */
public class ErrorResponseExceptionMapper implements ExceptionMapper<ErrorResponseException> {

    @Override
    public Response toResponse(ErrorResponseException e) {
        ErrorResponse response = e.getErrorResponse();
        return Response.status(response.getStatusCode()).entity(response).build();
    }

}
