package di.uniba.map.game;

import javax.sound.sampled.*;
import java.io.File;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;
public class MusicPlayer {
    String audioFilePath = "resources/music/Menu song.wav"; // Percorso assoluto del tuo file audio
    AudioInputStream audioStream;
    private Clip audioClip;

    public static void play(){
        String audioFilePath = "resources/music/Menu song.wav"; // Percorso assoluto del tuo file audio

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(audioFilePath));
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);

            Scanner scanner = new Scanner(System.in);            

            System.out.println("Riproduzione audio...");
            audioClip.start();

            // Attendi fino a quando il clip non è terminato
            while (!audioClip.isRunning()) {
                Thread.sleep(10);
            }
            while (audioClip.isRunning()) {

                Thread.sleep(10);
            }

            scanner.close();

            audioClip.close();
            audioStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop(Clip audioClip){

        audioClip.stop();

    }
    
}
