package com.bababansiwalanew.Util.dto;

/**
 * Created by Lalit on 08-04-2017.
 */

public class WalletResponse {
    private String RESPONSESTATUS;
    private String message;
    private String PrepaidWallet;
    private String UtilityWallet;

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

    public String getPrepaidWallet() {
        return PrepaidWallet;
    }

    public void setPrepaidWallet(String prepaidWallet) {
        PrepaidWallet = prepaidWallet;
    }

    public String getUtilityWallet() {
        return UtilityWallet;
    }

    public void setUtilityWallet(String utilityWallet) {
        UtilityWallet = utilityWallet;
    }
}
