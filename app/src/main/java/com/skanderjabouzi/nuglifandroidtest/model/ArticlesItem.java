package com.skanderjabouzi.nuglifandroidtest.model;

import java.io.Serializable;

/**
 * Created by skanderjabouzi on 2017-09-18.
 */

public class ArticlesItem implements Serializable {
    private String id;
    private String title;
    private String channelName;
    private String publicationDate;

    public ArticlesItem(String id, String title, String channelName, String publicationDate) {
        this.id = id;
        this.title = title;
        this.channelName = channelName;
        this.publicationDate = publicationDate;
    }

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
