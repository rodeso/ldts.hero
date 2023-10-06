import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position;
    public Hero(Position p) {
        position = p;
    }
    public void setPosition(Position p) {
        position = p;
    }
    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }
    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }
    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }
    public void draw(Screen screen) {
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('X')[0]);
    }

}