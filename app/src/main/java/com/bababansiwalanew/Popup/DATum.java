package com.bababansiwalanew.Popup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DATum {

        @SerializedName("ID")
        @Expose
        private Integer iD;
        @SerializedName("ImageURl")
        @Expose
        private String imageURl;
        @SerializedName("Content")
        @Expose
        private String content;
        @SerializedName("Headings")
        @Expose
        private String headings;
        @SerializedName("popSts")
        @Expose
        private Object popSts;
        @SerializedName("SetTime")
        @Expose
        private Integer setTime;

        public Integer getID() {
            return iD;
        }

        public void setID(Integer iD) {
            this.iD = iD;
        }

        public String getImageURl() {
            return imageURl;
        }

        public void setImageURl(String imageURl) {
            this.imageURl = imageURl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadings() {
            return headings;
        }

        public void setHeadings(String headings) {
            this.headings = headings;
        }

        public Object getPopSts() {
            return popSts;
        }

        public void setPopSts(Object popSts) {
            this.popSts = popSts;
        }

        public Integer getSetTime() {
            return setTime;
        }

        public void setSetTime(Integer setTime) {
            this.setTime = setTime;
        }

    }


