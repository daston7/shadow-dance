import bagel.*;
import java.util.ArrayList;

public class Note {
    private final Image noteLeft = new Image("res/noteLeft.png");
    private final Image noteRight = new Image("res/noteRight.png");
    private final Image noteUp = new Image("res/noteUp.png");
    private final Image noteDown = new Image("res/noteDown.png");

    private final Image holdNoteLeft = new Image("res/holdNoteLeft.png");
    private final Image holdNoteRight = new Image("res/holdNoteRight.png");
    private final Image holdNoteDown = new Image("res/holdNoteDown.png");
    private final Image holdNoteUp = new Image("res/holdNoteUp.png");

    private String typeNote;
    private double frame;
    private String laneType;
    private double x;
    private double y = 100;

    public Note(String laneType, String typeNote, double frame, double x) {
        this.laneType = laneType;
        this.typeNote = typeNote;
        this.frame = frame;
        this.x = x;
    }

    private void drawNormalNote(Note note) {
        if (note.laneType.equals("Left")) {
            noteLeft.draw(note.x, y);
        } else if (note.laneType.equals("Right")) {
            noteRight.draw(note.x, y);
        } else if (note.laneType.equals("Up")) {
            noteUp.draw(note.x, y);
        } else if (note.laneType.equals("Down")) {
            noteDown.draw(note.x, y);
        }
    }

    private void drawHoldNote(Note note) {
        if (note.laneType.equals("Left")) {
            holdNoteLeft.draw(note.x, y);
        } else if (note.laneType.equals("Right")) {
            holdNoteRight.draw(note.x, y);
        } else if (note.laneType.equals("Up")) {
            holdNoteUp.draw(note.x, y);
        } else if (note.laneType.equals("Down")) {
            holdNoteDown.draw(note.x, y);
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getFrame() {
        return frame;
    }

    public String getLaneType() {
        return laneType;
    }

    public String getTypeNote() {
        return typeNote;
    }

    public void movingNote(Note note) {
        if (note.typeNote.equals("Normal")) {
            drawNormalNote(note);
        } else {
            drawHoldNote(note);
        }
        note.y += 2;
    }
}
