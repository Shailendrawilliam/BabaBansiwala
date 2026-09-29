package com.bababansiwalanew.Register.dto;

/**
 * Created by Lalit on 09-03-2017.
 */

public class RegisterResponse {

    private String RESPONSESTATUS;
    private String message;

    public String getRESPONSESTATUS() {
        return RESPONSESTATUS;
    }

    public void setRESPONSESTATUS(String RESPONSESTATUS) {
        this.RESPONSESTATUS = RESPONSESTATUS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
