package Controller;

import java.io.*;
import Model.*;

public class FileUtil {
    private static final String GAME_FILE_PATH = "files/game.txt";

    public static void save(GameState gameState) throws IOException {
        File file = new File(GAME_FILE_PATH);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(gameState);
        oos.flush();
        oos.close();
    }

    public static GameState load() throws IOException, ClassNotFoundException {
        File file = new File(GAME_FILE_PATH);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        GameState gameState = (GameState) ois.readObject();
        ois.close();
        return gameState;
    }

}
