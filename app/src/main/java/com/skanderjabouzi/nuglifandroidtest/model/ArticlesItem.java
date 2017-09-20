package com.skanderjabouzi.nuglifandroidtest.model;

import java.io.Serializable;

public class ArticlesItem implements Serializable {
    private String id;
    private String title;
    private String channelName;
    private String publicationDate;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getPublicationDate() {
        return publicationDate;
    }
}
