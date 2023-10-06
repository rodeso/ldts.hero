import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Wall extends Element{
    public Wall(Position p) {
        super(p);
    }
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#7f7f7f"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF3333"));
        graphics.putString(new TerminalPosition(super.getX(), super.getY()), "#");
    }

}
