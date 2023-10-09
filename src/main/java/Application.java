import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        Game joguinho = new Game(80, 25, 50, 30); //settings
        try {
            joguinho.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
