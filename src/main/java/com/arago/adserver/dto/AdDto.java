package com.arago.adserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class AdDto {

    private String id;

    @NotBlank(message = "Title cannot be blank")
    @NotNull(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "URL cannot be blank")
    @NotNull(message = "URL is required")
    private String url;

    public AdDto() {
    }

    public AdDto(String id, String title, String description, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public AdDto(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
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
        AdDto adDTO = (AdDto) o;
        return Objects.equals(id, adDTO.id) && Objects.equals(title, adDTO.title) && Objects.equals(description, adDTO.description) && Objects.equals(url, adDTO.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, url);
    }
}
