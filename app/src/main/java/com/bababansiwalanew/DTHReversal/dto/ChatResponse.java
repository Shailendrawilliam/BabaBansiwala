package com.bababansiwalanew.DTHReversal.dto;

import java.util.ArrayList;

/**
 * Created by Lalit on 26-04-2017.
 */

public class ChatResponse {

    private String RESPONSESTATUS;
    private String message;
    private ArrayList<ChatResponseObject> Response;

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

    public ArrayList<ChatResponseObject> getResponse() {
        return Response;
    }

    public void setResponse(ArrayList<ChatResponseObject> response) {
        Response = response;
    }
}
