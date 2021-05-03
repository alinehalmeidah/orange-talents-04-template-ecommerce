package br.com.orangetalents.mercadolivre.utils.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.ArrayList;
import java.util.List;

public class ErroHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
        List<ErroResponse> errors = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ObjectError> objectErrors = exception.getBindingResult().getGlobalErrors();

        fieldErrors.forEach(fieldError -> {
            errors.add(new ErroResponse(fieldError.getField(),
                    messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())));
        });

        objectErrors.forEach(objectError -> {
            errors.add(new ErroResponse(objectError.getObjectName(),
                    messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
        });

        return errors;
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    public ErroResponse handle(AuthenticationException exception){
        return new ErroResponse("auth", exception.getMessage());
    }
}