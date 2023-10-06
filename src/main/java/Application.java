import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        Game joguinho = new Game(150, 50);
        try {
            joguinho.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
