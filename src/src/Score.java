import bagel.*;
public class Score {

    private final static int WINDOW_HEIGHT = 768;
    private final int Y_STATIONARY_NOTES = 657;
    private String message = "";
    private int k = 0;

    private final Font messageFont = new Font("res/FSO8BITR.TTF", 40);
    private final Font scoreFont = new Font("res/FSO8BITR.TTF", 30);


    public double absoluteDistance(Note note) {
        return Math.abs(Y_STATIONARY_NOTES - note.getY());
    }

    public String getMessage() {
        return message;
    }

    public int getK() {
        return k;
    }

    public void drawScore(int k) {
        scoreFont.drawString("SCORE " + k, 35, 35);
    }

    public void drawMessage(String message) {
        messageFont.drawString(message, (Window.getWidth() - messageFont.getWidth(message))/2.0, (Window.getHeight() + 40)/2.0);
    }

    public void calculateScore(Note note) {
        if (absoluteDistance(note) <= 15) {
            message = "PERFECT";
            k += 10;

        } else if (absoluteDistance(note) > 15 && absoluteDistance(note) <= 50) {
            message = "GOOD";
            k += 5;
        } else if (absoluteDistance(note) > 50 && absoluteDistance(note) <= 100) {
            message = "BAD";
            k -= 1;
        } else if ((absoluteDistance(note) > 100 && absoluteDistance(note) <= 200)) {
            message = "MISS";
            k -= 5;
        }
    }

}
