import bagel.*;

public class Lane {
    private final Image laneLeft = new Image("res/laneLeft.png");
    private final Image laneRight = new Image("res/laneRight.png");
    private final Image laneUp = new Image("res/laneUp.png");
    private final Image laneDown = new Image("res/laneDown.png");

    private String type;
    private double x;

    public Lane(String type, double x) {
        this.type = type;
        this.x = x;
    }

    public String getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public void drawLane(Lane lane) {
        if (lane.type.equals("Left")) {
            laneLeft.draw(lane.x, 384);
        } else if (lane.type.equals("Right")) {
            laneRight.draw(lane.x, 384);
        } else if (lane.type.equals("Up")) {
            laneUp.draw(lane.x, 384);
        } else if (lane.type.equals("Down")) {
            laneDown.draw(lane.x, 384);
        }
    }

    @Override
    public String toString() {
        return "Lane{" +
                "type='" + type + '\'' +
                ", x=" + x +
                '}';
    }
}
