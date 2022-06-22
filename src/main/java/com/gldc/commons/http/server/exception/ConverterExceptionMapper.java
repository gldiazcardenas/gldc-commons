package com.gldc.commons.http.server.exception;

import com.gldc.commons.bean.mapping.service.ConverterException;
import com.gldc.commons.http.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Mapper to convert {@link ConverterException} to a proper response object.
 *
 * @author Gabriel Diaz, 22/12/2020.
 */
public class ConverterExceptionMapper implements ExceptionMapper<ConverterException> {

    @Override
    public Response toResponse(ConverterException e) {
        ErrorResponse errorResponse = new ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
    }

}
