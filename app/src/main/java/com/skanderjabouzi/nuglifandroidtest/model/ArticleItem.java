package com.skanderjabouzi.nuglifandroidtest.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleItem {

    private Integer id;
    private String className;
    private String channelName;
    private String title;
    private String titleNoHTML;
    private String lead;
    private String leadNoHTML;
    private String type;
    private String location;
    private String datetime;
    private String modificationDate;
    private String byLine;
    private String lienURL;
    private String organisation;
    private String sharedId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleNoHTML() {
        return titleNoHTML;
    }

    public void setTitleNoHTML(String titleNoHTML) {
        this.titleNoHTML = titleNoHTML;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getLeadNoHTML() {
        return leadNoHTML;
    }

    public void setLeadNoHTML(String leadNoHTML) {
        this.leadNoHTML = leadNoHTML;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getByLine() {
        return byLine;
    }

    public void setByLine(String byLine) {
        this.byLine = byLine;
    }

    public String getLienURL() {
        return lienURL;
    }

    public void setLienURL(String lienURL) {
        this.lienURL = lienURL;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getSharedId() {
        return sharedId;
    }

    public void setSharedId(String sharedId) {
        this.sharedId = sharedId;
    }

}