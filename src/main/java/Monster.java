import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    public Monster(Position p) {
        super(p);
    }
    public Position move() {
        Random random = new Random();
        int direction = random.nextInt(4); // Generate a random number between 0 and 3 (inclusive)
        return switch (direction) {
            case 0 -> // North
                    new Position(super.getX(), super.getY() - 1);
            case 1 -> // East
                    new Position(super.getX() + 1, super.getY());
            case 2 -> // South
                    new Position(super.getX(), super.getY() + 1);
            case 3 -> // West
                    new Position(super.getX() - 1, super.getY());
            default -> super.getPosition();
        };
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#013220")); //dark green
        graphics.setBackgroundColor(TextColor.Factory.fromString("#c2b280")); //sand
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(super.getX(), super.getY()), "Δ");
    }
}
