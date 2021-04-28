package org.personal.springbootbase.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.personal.springbootbase.type.ErrorType;

@Getter
@NoArgsConstructor
public abstract class BaseException extends RuntimeException {

    protected ErrorType errorType;
    protected String userMessage;
    protected Object[] userMessageArgs;

    protected BaseException(Throwable e) {
        super(e);
    }

    protected BaseException(String message) {
        super(message);
    }
}
