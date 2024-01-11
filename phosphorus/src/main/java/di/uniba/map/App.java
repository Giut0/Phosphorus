package di.uniba.map;

import java.io.IOException;
import java.io.File;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import di.uniba.Utils;
import di.uniba.map.game.MusicPlayer;
import di.uniba.map.game.PhosphorusGame;
import di.uniba.map.game.UI;
import di.uniba.map.parser.Parser;
import di.uniba.map.parser.ParserOutput;

import java.util.Scanner;

import javax.sound.sampled.*;
import java.io.File;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

        PhosphorusGame game = new PhosphorusGame();      

        UI.printTitle(System.out);
        UI.printMainMenu(System.out);

        String audioFilePath = "resources/music/Menu song.wav"; // Percorso assoluto del tuo file audio

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(audioFilePath));
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);

            Scanner MusicScanner = new Scanner(System.in);

            audioClip.start();

            // Attendi fino a quando il clip non è terminato
            while (!audioClip.isRunning()) {
                Thread.sleep(10);
            }
            while (audioClip.isRunning()) {
                game.initGame();
                Thread.sleep(10);
            }

            MusicScanner.close();

            audioClip.close();
            audioStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
