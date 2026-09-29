package com.bababansiwalanew.popupdataresponse;

 public class DataObject {

    private String ID;
    private String ImageURl;
    private String Content;
    private String Headings;
    private String SetTime;
    private String popSts;

    public String getImageURl() {
        return ImageURl;
    }

    public void setImageURl(String imageURl) {
        ImageURl = imageURl;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getHeadings() {
        return Headings;
    }

    public void setHeadings(String headings) {
        Headings = headings;
    }

    public String getPopSts() {
        return popSts;
    }

    public void setPopSts(String popSts) {
        this.popSts = popSts;
    }

    public String getSetTime() {
        return SetTime;
    }

    public void setSetTime(String setTime) {
        SetTime = setTime;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

}
