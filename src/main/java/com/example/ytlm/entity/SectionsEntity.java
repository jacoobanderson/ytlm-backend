package com.example.ytlm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sections", schema = "learn", catalog = "ytlm")
public class SectionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "section_id")
    private int sectionId;
    @Basic
    @Column(name = "url")
    private String url;

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectionsEntity that = (SectionsEntity) o;

        if (sectionId != that.sectionId) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sectionId;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
