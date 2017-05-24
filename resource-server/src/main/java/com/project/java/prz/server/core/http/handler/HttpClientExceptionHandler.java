package com.project.java.prz.server.core.http.handler;

import com.project.java.prz.common.core.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by phendzel on 5/24/2017.
 */
public class HttpClientExceptionHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode() != HttpStatus.OK;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if(clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND)
            throw new GeneralException(GeneralException.FailReason.RESOURCE_NOT_FOUND);
    }

}