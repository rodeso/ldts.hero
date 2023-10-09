import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Arena {
    //attributes
    private final int width, height, coins_available, n_monsters;
    private int coins_collected;
    Hero hero;
    private final List<Wall> walls;
    private final List<Monster> monsters;
    private final List<Coin> coins;
    List<Coin> collectedCoins;

    //constructor
    public Arena(int w, int h, int c, int m) {
        width = w;
        height = h;
        coins_available = c;
        coins_collected = 0;
        n_monsters = m;
        Position position = new Position(w / 2, h / 2);
        hero = new Hero(position);
        this.walls = createWalls();
        this.monsters = createMonsters(m);
        this.coins = createCoins(c);
    }

    //getters
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getCoins_available() {
        return coins_available;
    }
    public int getCoins_collected() {
        return coins_collected;
    }
    //draw
    public void draw(TextGraphics graphics) {
        //draw background
        graphics.setBackgroundColor(TextColor.Factory.fromString("#c2b280")); //sand
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        //draw hero
        hero.draw(graphics);
        //draw walls
        for (Wall wall : walls)
            wall.draw(graphics);
        //draw monsters
        for (Monster monster : monsters)
            monster.draw(graphics);
        //draw coins
        for (Coin coin : coins)
            coin.draw(graphics);
    }
    //methods
    private boolean canMove(Position p) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(hero.getPosition()))
                return true;
            if (wall.getPosition().equals(p))
                return false;
        }
        return (width > p.getX() && height > p.getY() && p.getY() >= 0 && p.getX() >= 0);
    }
    private void moveHero(Position p) {
        if (canMove(p))
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
        moveMonsters();
        retrieveCoins();
        System.out.printf("Key Press: %s\n", key);
        System.out.printf("x: %d\n", hero.getX());
        System.out.printf("y: %d\n", hero.getY());
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
    private List<Monster> createMonsters(int n) {
        Random random = new Random();
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < n; i++)
            monsters.add(new Monster(new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1)));
        return monsters;
    }
    public void moveMonsters() {
        for (Monster monster : monsters) {
            Position p = monster.move();
            if (canMove(p))
                monster.setPosition(p);
        }
    }
    public int verifyMonsterCollision() {
        Position heroPosition = hero.getPosition();
        int i = 0;
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(heroPosition)) {
                i++;
            }
            Position heroPositionPX = new Position(hero.getX()+1, hero.getY());
            if (monster.getPosition().equals(heroPositionPX)) {
                i++;
            }
            Position heroPositionNX = new Position(hero.getX()-1, hero.getY());
            if (monster.getPosition().equals(heroPositionNX)) {
                i++;
            }
            Position heroPositionPY = new Position(hero.getX(), hero.getY()+1);
            if (monster.getPosition().equals(heroPositionPY)) {
                i++;
            }
            Position heroPositionNY = new Position(hero.getX(), hero.getY()-1);
            if (monster.getPosition().equals(heroPositionNY)) {
                i++;
            }
        }
        System.out.printf("monster collision: %d\n", i);
        return i;
    }
    private List<Coin> createCoins(int n) {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < n; i++)
            coins.add(new Coin(new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1)));
        return coins;
    }
    public void retrieveCoins() {
        Position heroPosition = hero.getPosition();
        List<Coin> collectedCoins = new ArrayList<>();

        for (Coin coin : coins) {
            if (coin.getPosition().equals(heroPosition)) {
                collectedCoins.add(coin);
                coins_collected++;
            }
        }
        coins.removeAll(collectedCoins);
    }

}
