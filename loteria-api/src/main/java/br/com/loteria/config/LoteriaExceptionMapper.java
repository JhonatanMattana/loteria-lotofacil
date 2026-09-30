package br.com.loteria.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.loteria.exception.LoteriaException;
import br.com.loteria.response.ApiErrorResponse;

@Provider
public class LoteriaExceptionMapper implements ExceptionMapper<LoteriaException> {

	@Override
    public Response toResponse(LoteriaException ex) {

        ApiErrorResponse error = new ApiErrorResponse(
            ex.getMessage(),
            ex.getErrorCode(),
            ex.getStatus().getDescicao()
        );

        return Response
                .status(ex.getStatus().getStatusCode())
                .entity(error)
                .build();
    }

}
