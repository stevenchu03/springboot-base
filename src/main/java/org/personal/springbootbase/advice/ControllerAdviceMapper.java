package org.personal.springbootbase.advice;

import lombok.extern.slf4j.Slf4j;
import org.personal.springbootbase.dto.ErrorDto;
import org.personal.springbootbase.exception.BaseException;
import org.personal.springbootbase.exception.ClientException;
import org.personal.springbootbase.exception.ServerException;
import org.personal.springbootbase.type.ErrorType;
import org.personal.springbootbase.util.I18nUtil;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ControllerAdviceMapper {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErrorDto> handleClientException(ClientException e) {
        return buildFlatResponseError(e);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ErrorDto> handleServerException(ServerException e) {
        logError(e);
        return buildFlatResponseError(e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleDefaultException(Exception e) {
        final ServerException ex = new ServerException(ErrorType.GENERAL_ERROR, e);
        logError(ex);
        return buildFlatResponseError(ex);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorDto> handleInvalidParam(Exception e) {
        final ClientException ex = new ClientException(ErrorType.INVALID_PARAM, e, e.getMessage());
        return buildFlatResponseError(ex);
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ErrorDto> handleNoHandlerFoundException(NoHandlerFoundException e) {
        final ClientException ex = new ClientException(ErrorType.URL_NOT_FOUND, e, e.getMessage());
        return buildFlatResponseError(ex);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDto> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        final ClientException ex = new ClientException(ErrorType.METHOD_NOT_ALLOWED, e, e.getMessage());
        return buildFlatResponseError(ex);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDto> handleMissingQueryParamException(MissingServletRequestParameterException e) {
        final ClientException ex = new ClientException(ErrorType.INVALID_PARAM, e, e.getMessage());
        return buildFlatResponseError(ex);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final ErrorType errorType = ErrorType.INVALID_PARAM;

        final List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<ErrorDto.Error> errors = fieldErrors.stream().map(fieldError -> {
            final ErrorDto.Error errorResponse = new ErrorDto.Error();
            errorResponse.setCode(errorType.getErrorCode());
            errorResponse.setUserMessage(fieldError.getDefaultMessage());
            errorResponse.setInternalMessage("");

            return errorResponse;

        }).collect(Collectors.toList());

        final ErrorDto errorResponseDto = new ErrorDto();
        errorResponseDto.setErrors(errors);

        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(errorType.getStatusCode()));
    }

    private String translateMessage(BaseException e) {
        return I18nUtil.translate(e.getUserMessage(), e.getUserMessageArgs());
    }

    private void logError(BaseException e) {
        // output messages to stdout
        MDC.put("Response-User-Error-Message", translateMessage(e));
        MDC.put("Response-Error-Code", e.getErrorType().getErrorCode());

        log.error(e.getMessage(), e);
    }

    private ResponseEntity<ErrorDto> buildFlatResponseError(BaseException e) {
        final ErrorDto.Error errorResponse = new ErrorDto.Error();
        errorResponse.setCode(e.getErrorType().getErrorCode());
        errorResponse.setInternalMessage(e.getMessage());
        errorResponse.setUserMessage(I18nUtil.translate(e.getUserMessage(), e.getUserMessageArgs()));

        final ErrorDto errorResponseDto = new ErrorDto();
        errorResponseDto.setErrors(Collections.singletonList(errorResponse));

        return new ResponseEntity<>(errorResponseDto, HttpStatus.valueOf(e.getErrorType().getStatusCode()));
    }
}
