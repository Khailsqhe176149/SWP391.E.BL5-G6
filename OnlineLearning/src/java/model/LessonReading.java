/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class LessonReading {
    private int readingId;
    private int lessonId;
    private String title;
    private String description;
    private String readingURL; // URL của tài liệu đọc

    // Constructors, getters, and setters
    public LessonReading(int readingId, int lessonId, String title, String description, String readingURL) {
        this.readingId = readingId;
        this.lessonId = lessonId;
        this.title = title;
        this.description = description;
        this.readingURL = readingURL;
    }

    public int getReadingId() {
        return readingId;
    }

    public void setReadingId(int readingId) {
        this.readingId = readingId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReadingURL() {
        return readingURL;
    }

    public void setReadingURL(String readingURL) {
        this.readingURL = readingURL;
    }
}

