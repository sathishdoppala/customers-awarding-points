package com.customer.awarding.model;


/**
 * @author Doppala Satish
 */

public class HealthCheck {

    public HealthCheck(String environment, String message) {
        this.environment = environment;
        this.message = message;
    }

    private String environment;
    private String message;


    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
