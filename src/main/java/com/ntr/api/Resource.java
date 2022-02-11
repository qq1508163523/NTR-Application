package com.ntr.api;


public enum Resource{
    IMG_URL("http://127.0.0.1/imageFile/"),
    VIDEO_URL("http://127.0.0.1/videoFile/"),
    IMG_PATH("E:\\web_root\\anime_web\\imageFile\\"),
    VIDEO_PATH("E:\\web_root\\anime_web\\videoFile\\");

    private final String pathName;

    Resource(String pathName){
        this.pathName = pathName;
    }

    public String getRoot() {
        return this.pathName;
    }
}
