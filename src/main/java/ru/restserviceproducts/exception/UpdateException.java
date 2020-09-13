package ru.restserviceproducts.exception;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;

public class UpdateException extends AmqpRejectAndDontRequeueException {
    public UpdateException(String message) {
        super(message);
    }
}
