package org.personal.springbootbase.exception;

import org.personal.springbootbase.type.ErrorType;

public class ClientException extends BaseException {

    public ClientException(ErrorType errorType, String userMessage, Object... userMessageArgs) {
        super("");
        setDefaultParam(errorType, userMessage, userMessageArgs);
    }

    public ClientException(ErrorType errorType, Throwable e, String userMessage, Object... userMessageArgs) {
        super(e);
        setDefaultParam(errorType, userMessage, userMessageArgs);
    }

    private void setDefaultParam(ErrorType errorType, String userMessage, Object... userMessageArgs) {
        this.errorType = errorType;
        this.userMessage = userMessage;
        this.userMessageArgs = userMessageArgs;
    }
}
