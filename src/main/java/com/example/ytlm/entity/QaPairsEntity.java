package com.example.ytlm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "qa_pairs", schema = "learn", catalog = "ytlm")
public class QaPairsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pair_id")
    private int pairId;
    @Basic
    @Column(name = "section_id")
    private Integer sectionId;
    @Basic
    @Column(name = "question")
    private String question;
    @Basic
    @Column(name = "answer")
    private String answer;

    public int getPairId() {
        return pairId;
    }

    public void setPairId(int pairId) {
        this.pairId = pairId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QaPairsEntity that = (QaPairsEntity) o;

        if (pairId != that.pairId) return false;
        if (sectionId != null ? !sectionId.equals(that.sectionId) : that.sectionId != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pairId;
        result = 31 * result + (sectionId != null ? sectionId.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }
}
