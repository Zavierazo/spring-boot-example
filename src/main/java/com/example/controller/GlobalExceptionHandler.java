package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.xml.sax.SAXParseException;

import com.example.exception.ClientException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String exception(Exception ex, final HttpServletRequest request) {
        log.error("Error ", ex);
        return ex.getMessage();
    }

    @ExceptionHandler({
            ClientException.class, MissingServletRequestParameterException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String clientException(Exception ex, final HttpServletRequest request) {
        log.error("Client exception on {}?{}: {}", request.getRequestURL(), request.getQueryString(), ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(ClientAbortException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String clientAbortException(Exception ex, final HttpServletRequest request) {
        log.error("Client abort exception from {}", request.getRemoteAddr());
        return ex.getMessage();
    }

    @ExceptionHandler(SAXParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String templateInputException(Exception ex, final HttpServletRequest request) {
        log.error("TemplateInputException: {}", ex.getMessage());
        return ex.getMessage();
    }

}
