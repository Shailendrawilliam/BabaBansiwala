package com.bababansiwalanew.Api.Object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Banners {
    @SerializedName("resourceUrl")
    @Expose
    public String resourceUrl;
    @SerializedName("fileName")
    @Expose
    public String fileName;

    public String getResourceUrl() {
        return resourceUrl;
    }

    public String getFileName() {
        return fileName;
    }
}
