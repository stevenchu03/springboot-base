package org.personal.springbootbase.dto;

import lombok.Data;
import org.personal.springbootbase.type.ErrorType;

import java.util.List;

@Data
public class ErrorDto {

    private List<Error> errors;

    @Data
    public static class Error {

        /**
         * userMessage value is provided by ErrorType(message)
         *
         * @see ErrorType
         */
        private String userMessage;

        /**
         * provided by exception message
         */
        private String internalMessage;

        /**
         * code value is provided by ErrorType(errorCode)
         *
         * @see ErrorType
         */
        private String code;

        private String moreInfo = "";
    }
}
