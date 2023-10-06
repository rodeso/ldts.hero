import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public class Arena {
    private int width, height;
    Hero hero;
    public Arena(int w, int h) {
        width = w;
        height = h;
        Position position = new Position(w/2, h/2);
        hero = new Hero(position);
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void draw(TextGraphics graphics) {
        //draw background
        graphics.setBackgroundColor(TextColor.Factory.fromString("#c2b280")); //sand
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        //draw hero
        graphics.setForegroundColor(TextColor.Factory.fromString("#ff4500")); //orange-red
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(hero.getX(), hero.getY()), "X");
    }
    private boolean canHeroMove(Position p) {
        return (width > p.getX() && height > p.getY() && p.getY() >= 0 && p.getX() >= 0);
    }
    private void moveHero(Position p) {
        if (canHeroMove(p))
            hero.setPosition(p);
    }
    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            case EOF:
                break;
        }
        System.out.println(key);
        System.out.println(hero.getX());
        System.out.println(hero.getY());
    }
}
