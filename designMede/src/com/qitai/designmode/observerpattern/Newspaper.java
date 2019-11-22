package com.qitai.designmode.observerpattern;

/**
 * 报纸实体类
 */
public class Newspaper {
    private String newsOfficeName;
    private String publishDate;

    Newspaper(String newsOfficeName,String publishDate){
        this.newsOfficeName = newsOfficeName;
        this.publishDate = publishDate;
    }

    public void setNewsOfficeName(String newsOfficeName) {
        this.newsOfficeName = newsOfficeName;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getNewsOfficeName() {
        return newsOfficeName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    @Override
    public String toString() {
        return "出版社:"+newsOfficeName+"      出版时间："+publishDate;
    }
}
