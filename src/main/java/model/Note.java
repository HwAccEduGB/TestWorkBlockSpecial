package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Note {
    private String ID;
    private String titleNote;
    private String bodyNote;
    private String date;

    public Note() {
    }

    public Note(String ID, String titleNote, String bodyNote, String date) {
        this.ID = ID;
        this.titleNote = titleNote;
        this.bodyNote = bodyNote;
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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

    @Override
    public String toString() {
        return "Note{" +
                "ID='" + ID + '\'' +
                ", titleNote='" + titleNote + '\'' +
                ", bodyNote='" + bodyNote + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
