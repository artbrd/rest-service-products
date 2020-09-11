package ru.restserviceproducts.exception.rabbit;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;

public class SaveException extends AmqpRejectAndDontRequeueException {

    public SaveException(String message) {
        super(message);
    }
}
