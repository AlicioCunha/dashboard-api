package com.moneyapi.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class MoneyExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String invalidMessageUser = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
        String invalidMessageDeveloper = ex.getCause().toString();
        List<moneyErrorMessage> errorMessageList = Arrays.asList(new moneyErrorMessage(invalidMessageUser, invalidMessageDeveloper));

        return handleExceptionInternal(ex, errorMessageList, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<moneyErrorMessage> errorMessageList = createdListMoneyErrorMessage(ex.getBindingResult());
        return handleExceptionInternal(ex, errorMessageList, headers, HttpStatus.BAD_REQUEST, request);
    }

    public static class moneyErrorMessage {
        private String messageUser;
        private String messageDeveloper;

        public moneyErrorMessage(String messageUser, String messageDeveloper) {
            this.messageUser = messageUser;
            this.messageDeveloper = messageDeveloper;
        }

        public String getMessageUser() {
            return messageUser;
        }

        public String getMessageDeveloper() {
            return messageDeveloper;
        }
    }

    private List<moneyErrorMessage> createdListMoneyErrorMessage(BindingResult bindingResult) {
        List<moneyErrorMessage> errorMessages = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String devMessage = fieldError.toString();
            errorMessages.add(new moneyErrorMessage(userMessage, devMessage));
        }

        return errorMessages;
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){

        String invalidMessageUser = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        String invalidMessageDeveloper = ex.toString();
        List<moneyErrorMessage> errorMessageList = Arrays.asList(new moneyErrorMessage(invalidMessageUser, invalidMessageDeveloper));

        return handleExceptionInternal(ex, errorMessageList, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }
}
