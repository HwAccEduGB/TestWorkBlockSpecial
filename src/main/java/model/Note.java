package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class Note {
    private int ID;
    @JsonProperty("Заголовок_заметки")
    private String titleNote;
    @JsonProperty("Текст_заметки")
    private String bodyNote;
    @JsonProperty("Дата_создания-правки")
    private String date;

    public Note() {
    }

    public Note(int ID, String titleNote, String bodyNote, String date) {
        this.ID = ID;
        this.titleNote = titleNote;
        this.bodyNote = bodyNote;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitleNote() {
        return titleNote;
    }

    public void setTitleNote(String titleNote) {
        this.titleNote = titleNote;
    }

    public String getBodyNote() {
        return bodyNote;
    }

    public void setBodyNote(String bodyNote) {
        this.bodyNote = bodyNote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
