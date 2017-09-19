package com.skanderjabouzi.nuglifandroidtest.helper;


import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListHelper {
    private class ChannelComparator implements Comparator<ArticlesItem> {
        @Override
        public int compare(ArticlesItem o1, ArticlesItem o2) {
            return o1.getChannelName().compareTo(o2.getChannelName());
        }
    }

    private class dateComparator implements Comparator<ArticlesItem> {
        @Override
        public int compare(ArticlesItem o1, ArticlesItem o2) {
            return o1.getPublicationDate().compareTo(o2.getPublicationDate());
        }
    }

    public List<ArticlesItem> sortByDate(List<ArticlesItem> list) {
        Collections.sort(list, new dateComparator());
        return list;
    }

    public List<ArticlesItem> sortByChannel(List<ArticlesItem> list) {
        Collections.sort(list, new ChannelComparator());
        return list;
    }
}
