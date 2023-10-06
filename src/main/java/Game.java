import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    Arena arena;
    private Screen screen;
    public Game(int width, int height, int coins, int monsters) {
        try {
            arena = new Arena(width, height, coins, monsters); //creates new arena with the window size
            //window size
            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException {
        boolean flag = true;
        while (flag) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.EOF) {
                flag = false;
                System.out.println("Game exited");
            }
            if (arena.getCoins_available() == arena.getCoins_collected() && arena.getCoins_available() != 0) {
                flag = false;
                System.out.println("You collected all the coins!");
            }
            if (arena.verifyMonsterCollision() != 0) {
                flag = false;
                System.out.println("You died!");
            }
        }
    }
    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }
}
