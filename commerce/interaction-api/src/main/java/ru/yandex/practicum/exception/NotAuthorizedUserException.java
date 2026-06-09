package ru.yandex.practicum.exception;

import org.springframework.http.HttpStatus;

public class NotAuthorizedUserException extends RuntimeException {
//    cause	{...}
//    stackTrace	[...]
    HttpStatus httpStatus;
    String userMessage;
    String message;
    //    suppressed	[...]
    String localizedMessage;
}
