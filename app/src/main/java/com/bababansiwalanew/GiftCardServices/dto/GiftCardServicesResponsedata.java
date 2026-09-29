package com.bababansiwalanew.GiftCardServices.dto;

 public  class GiftCardServicesResponsedata extends GiftCardServicesResponse {
    private String service_type;
    private String service_provider;
    private String service_desc;
    private String provider_key;
    private String image;

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_provider() {
        return service_provider;
    }

    public void setService_provider(String service_provider) {
        this.service_provider = service_provider;
    }

    public String getService_desc() {
        return service_desc;
    }

    public void setService_desc(String service_desc) {
        this.service_desc = service_desc;
    }

    public String getProvider_key() {
        return provider_key;
    }

    public void setProvider_key(String provider_key) {
        this.provider_key = provider_key;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
