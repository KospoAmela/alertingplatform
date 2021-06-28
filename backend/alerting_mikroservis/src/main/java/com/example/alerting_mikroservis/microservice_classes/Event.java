package com.example.alerting_mikroservis.microservice_classes;

import java.sql.Timestamp;
import java.util.UUID;

public class Event {
    private Timestamp time;
    private UUID userId;
    private boolean successfulLogin;

    public Event(UUID userId, boolean successfulLogin) {
        this.userId = userId;
        this.successfulLogin = successfulLogin;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public boolean isSuccessfulLogin() {
        return successfulLogin;
    }

    public void setSuccessfulLogin(boolean successfulLogin) {
        this.successfulLogin = successfulLogin;
    }

    public UUID getUserId() {
        return userId;
    }
}

