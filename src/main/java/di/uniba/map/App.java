package di.uniba.map;

import java.io.IOException;
import java.io.File;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import di.uniba.map.game.GameTimer;
import di.uniba.map.game.PhosphorusGame;
import java.util.Scanner;

import javax.sound.sampled.*;


public class App {
    public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

        PhosphorusGame game = new PhosphorusGame();     

        String audioFilePath = "resources/music/Short_circuit.wav"; // Percorso assoluto del tuo file audio
        GameTimer timer = new GameTimer();

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(audioFilePath));
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);

            Scanner MusicScanner = new Scanner(System.in);

            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

            // Attendi fino a quando il clip non è terminato
            while (!audioClip.isRunning()) {
                Thread.sleep(10);
            }
            while (audioClip.isRunning()) {
                game.initGame(System.out, audioClip, timer);
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
