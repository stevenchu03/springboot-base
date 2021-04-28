package org.personal.springbootbase.exception;

import org.personal.springbootbase.type.ErrorType;
import org.personal.springbootbase.util.I18nUtil;

public class ServerException extends BaseException {

    public ServerException(ErrorType errorType, String message, Object... args) {
        super(I18nUtil.translate(message, args));
        setDefaultParam(errorType);
    }

    public ServerException(ErrorType errorType, Throwable e) {
        super(e);
        setDefaultParam(errorType);
    }

    private void setDefaultParam(ErrorType errorType) {
        this.userMessage = "general_error";
        this.errorType = errorType;
    }
}
