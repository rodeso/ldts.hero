import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        Game joguinho = new Game(90, 30, 15, 20); //settings
        try {
            joguinho.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
