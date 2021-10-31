package com.android.mvvm_bottom_nav.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {

    public Book(@NonNull String title) {
        this.title = title;
    }

    public Book(@NonNull String title,
                @NonNull String author,
                @NonNull String description,
                @NonNull String opinion) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.opinion = opinion;
    }

    public int STATUS_UNKNOWN = 0;
    public int STATUS_TO_READ = 1;
    public int STATUS_ONGOING = 2;
    public int STATUS_IS_READ = 3;
    public int STATUS_IS_PAUSED = 4;

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String title;
    private String author;
    private int category;
    private String description;
    private String opinion;
    private int status = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
