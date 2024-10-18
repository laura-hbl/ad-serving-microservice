package com.arago.adserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;
import java.util.UUID;

//timeToLive is set to 5 minutes for testing purposes.
@RedisHash(value = "ad", timeToLive = 300)
public class Ad {

    @Id
    private String id;
    private String title;
    private String description;
    private String url;

    public Ad() {
    }

    public Ad(String title, String description, String url) {
        this(UUID.randomUUID().toString(), title, description, url);
    }

    public Ad(String id, String title, String description, String url) {
        this.id = require(id, "ID cannot be null or empty");
        this.title = require(title, "Title cannot be null or empty");
        this.description = description;
        this.url = require(url, "URL cannot be null or empty");
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    private static String require(String value, String message){
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return id == ad.id && Objects.equals(title, ad.title) && Objects.equals(description, ad.description) && Objects.equals(url, ad.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, url);
    }

}
