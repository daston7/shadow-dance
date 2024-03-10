import bagel.*;
import bagel.util.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Skeleton Code for SWEN20003 Project 1, Semester 2, 2023
 * Please enter your name below
 * @Dustin Josee Susilo 1360704
 */
public class ShadowDance extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW DANCE";
    private final Image BACKGROUND_IMAGE = new Image("res/background.png");
    private final Font titleFont = new Font("res/FSO8BITR.TTF", 64);
    private final Font instructionFont = new Font("res/FSO8BITR.TTF", 24);


    private static final Point TITLE_POINT = new Point(220, 250);
    private static final Point INSTRUCTION_MESSAGE_1 = new Point(TITLE_POINT.x + 100, TITLE_POINT.y + 190);
    private static final Point INSTRUCTION_MESSAGE_2 = new Point(INSTRUCTION_MESSAGE_1.x, INSTRUCTION_MESSAGE_1.y + 40);

    private boolean gamePaused = false;
    private boolean gameStarted = false;

    private Lane[] lanes = new Lane[4];
    //private Track track = new Track("res/track1.wav");
    private Score score = new Score();

    private int laneNum = 0;

    private int frame = 0;
    private int messageFrame = 0;

    private ArrayList<Note> notes = new ArrayList<>();

    public ShadowDance(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        readCSV();
    }

    /**
     * Method used to read file and create objects (you can change
     * this method as you wish).
     */
    private void readCSV() {
        try (BufferedReader br =
            new BufferedReader(new FileReader("res/level1.csv"))) {
            String text;
            while ((text = br.readLine()) != null) {
                String[] entry = text.split(",");
                if (entry[0].equals("Lane")) {
                    lanes[laneNum++] = new Lane(entry[1], Double.parseDouble(entry[2]));
                } else {
                    for (Lane lane : lanes) {
                        if (lane.getType().equals(entry[0])) {
                            notes.add(new Note(entry[0], entry[1], Double.parseDouble(entry[2]), lane.getX()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowDance game = new ShadowDance();
        game.run();
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {

        BACKGROUND_IMAGE.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }

        if (input.wasPressed(Keys.SPACE)) {
            gameStarted = true;
        }

        if (input.wasPressed(Keys.TAB)) {
            gamePaused = true;
        }
        if (gameStarted) {
            //track.run();
            frame++;

            for (Lane lane : lanes) {
                lane.drawLane(lane);
            }

            score.drawScore(score.getK());

            if (messageFrame > 0) {
                score.drawMessage(score.getMessage());
                messageFrame--;
            }


            for (int i = 0; i < notes.size(); i++) {
                if (frame >= notes.get(i).getFrame()) {
                    notes.get(i).movingNote(notes.get(i));
                    if (notes.get(i).getTypeNote().equals("Normal")) {
                        if (input.wasPressed(Keys.LEFT) && notes.get(i).getLaneType().equals("Left") && score.absoluteDistance(notes.get(i)) <= 200) {
                            score.calculateScore(notes.get(i));
                            messageFrame = 30;
                            notes.remove(i);
                        }
                        if (input.wasPressed(Keys.RIGHT) && notes.get(i).getLaneType().equals("Right") && score.absoluteDistance(notes.get(i)) <= 200) {
                            score.calculateScore(notes.get(i));
                            messageFrame = 30;
                            notes.remove(i);
                        }
                        if (input.wasPressed(Keys.UP) && notes.get(i).getLaneType().equals("Up") && score.absoluteDistance(notes.get(i)) <= 200) {
                            score.calculateScore(notes.get(i));
                            messageFrame = 30;
                            notes.remove(i);
                        }
                        if (input.wasPressed(Keys.DOWN) && notes.get(i).getLaneType().equals("Down") && score.absoluteDistance(notes.get(i)) <= 200) {
                            score.calculateScore(notes.get(i));
                            messageFrame = 30;
                            notes.remove(i);
                        }

                        if (notes.get(i).getY() > WINDOW_HEIGHT) {
                            score.calculateScore(notes.get(i));
                            messageFrame = 30;
                            notes.remove(i);
                        }
                    } else {
                        if (input.wasPressed(Keys.LEFT) && input.isDown(Keys.LEFT) && notes.get(i).getLaneType().equals("Left") && score.absoluteDistance(notes.get(i)) <= 200) {
                            score.calculateScore(notes.get(i));
                            messageFrame = 30;
                            notes.remove(i);
                        }
                        if (input.wasPressed(Keys.RIGHT) && input.isDown(Keys.RIGHT) && notes.get(i).getLaneType().equals("Right") && score.absoluteDistance(notes.get(i)) <= 200) {
                            score.calculateScore(notes.get(i));
                            messageFrame = 30;
                            notes.remove(i);
                        }
                        if (input.wasPressed(Keys.UP) && input.isDown(Keys.UP) && notes.get(i).getLaneType().equals("Up") && score.absoluteDistance(notes.get(i)) <= 200) {
                            score.calculateScore(notes.get(i));
                            messageFrame = 30;
                            notes.remove(i);
                        }
                        if (input.wasPressed(Keys.DOWN) && input.isDown(Keys.DOWN) && notes.get(i).getLaneType().equals("Down") && score.absoluteDistance(notes.get(i)) <= 200) {
                            score.calculateScore(notes.get(i));
                            messageFrame = 30;
                            notes.remove(i);
                        }

                        if (notes.get(i).getY() > WINDOW_HEIGHT) {
                            score.calculateScore(notes.get(i));
                            messageFrame = 30;
                            notes.remove(i);
                        }
                    }
                }
            }


        } else {
            titleFont.drawString(GAME_TITLE, TITLE_POINT.x, TITLE_POINT.y);
            instructionFont.drawString("PRESS SPACE TO START", INSTRUCTION_MESSAGE_1.x, INSTRUCTION_MESSAGE_1.y);
            instructionFont.drawString("USE ARROW KEYS TO PLAY", INSTRUCTION_MESSAGE_2.x, INSTRUCTION_MESSAGE_2.y);
        }
    }
}
