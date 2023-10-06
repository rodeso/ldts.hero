import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        Game joguinho = new Game();
        try {
            joguinho.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
