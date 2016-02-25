package bac.model;

import bac.model.enums.EValidationType;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OQ")
@Table(name="open_question")
public class OpenQuestion extends Question {

    @Enumerated(EnumType.STRING)
    @Column(name = "validation_type")
    private EValidationType validationType;

    @Column(name = "is_long")
    private boolean isLong;

    public OpenQuestion(){}

    public OpenQuestion(String text, boolean mandatory, int position, boolean deleted, Page page, EValidationType validationType, boolean isLong) {
        super(text, mandatory, position, deleted, page);
        this.validationType = validationType;
        this.isLong = isLong;
    }

    public EValidationType getValidationType() {
        return validationType;
    }

    public void setValidationType(EValidationType validationType) {
        this.validationType = validationType;
    }

    public boolean getIsLong() {
        return isLong;
    }

    public void setIsLong(boolean isLong) {
        this.isLong = isLong;
    }
}
