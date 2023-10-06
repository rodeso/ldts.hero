import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final int width, height;
    Hero hero;
    private final List<Wall> walls;

    public Arena(int w, int h) {
        width = w;
        height = h;
        Position position = new Position(w / 2, h / 2);
        hero = new Hero(position);
        this.walls = createWalls();
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
        hero.draw(graphics);
        //draw walls
        for (Wall wall : walls)
            wall.draw(graphics);

    }

    private boolean canHeroMove(Position p) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(p))
                return false;

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
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(new Position(c, 0)));
            walls.add(new Wall(new Position(c, height - 1)));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(new Position(0, r)));
            walls.add(new Wall(new Position(width - 1, r)));
        }
        return walls;
    }
}
