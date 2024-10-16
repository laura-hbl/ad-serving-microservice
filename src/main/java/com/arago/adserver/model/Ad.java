package com.arago.adserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

@RedisHash("ad")
public class Ad {

    @Id
    private String id;
    private String title;
    private String description;
    private String url;

    public Ad() {
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
