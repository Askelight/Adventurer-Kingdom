/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author hour
 */
public class Sound {
    
    private static HashMap<String, Clip> clips;
    private static int gap;

    public static void init() {
        clips = new HashMap();
        gap = 0;
    }

    public static void load(String s, String n) {
        if (clips.get(n) != null) {
            return;
        }
        try {
            InputStream in = Sound.class.getResourceAsStream(s);
            InputStream bin = new BufferedInputStream(in);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bin);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED, 
                baseFormat.getSampleRate(), 
                16, 
                baseFormat.getChannels(), 
                baseFormat.getChannels() * 2, 
                baseFormat.getSampleRate(), 
                false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            Clip clip = AudioSystem.getClip();
            clip.open(dais);
            clips.put(n, clip);
        }
            catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void play(String s) {
        play(s, gap);
    }

    public static void play(String s, int i) {
        Clip c = (Clip)clips.get(s);
        if (c == null) {
            return;
        }
        if (c.isRunning()) {
            c.stop();
        }
        c.setFramePosition(i);
        while (!c.isRunning()) {
            c.start();
        }
    }

    public static void stop(String s) {
        if (clips.get(s) == null) {
            return;
        }
        if (((Clip)clips.get(s)).isRunning()) {
            ((Clip)clips.get(s)).stop();
        }
    }

    public static void resume(String s) {
        if (((Clip)clips.get(s)).isRunning()) {
            return;
        }
        ((Clip)clips.get(s)).start();
    }

    public static void resumeLoop(String s) {
        Clip c = (Clip)clips.get(s);
        if (c == null) {
            return;
        }
        c.loop(-1);
    }

    public static void loop(String s) {
        loop(s, gap, gap, ((Clip)clips.get(s)).getFrameLength() - 1);
    }

    public static void loop(String s, int frame) {
        loop(s, frame, gap, ((Clip)clips.get(s)).getFrameLength() - 1);
    }

    public static void loop(String s, int start, int end) {
        loop(s, gap, start, end);
    }

    public static void loop(String s, int frame, int start, int end) {
        Clip c = (Clip)clips.get(s);
        if (c == null) {
            return;
        }
        if (c.isRunning()) {
            c.stop();
        }
        c.setLoopPoints(start, end);
        c.setFramePosition(frame);
        c.loop(-1);
    }

    public static void setPosition(String s, int frame) {
        ((Clip)clips.get(s)).setFramePosition(frame);
    }

    public static int getFrames(String s) {
        return ((Clip)clips.get(s)).getFrameLength();
    }

    public static int getPosition(String s) {
        return ((Clip)clips.get(s)).getFramePosition();
    }

    public static void close(String s) {
        stop(s);
        ((Clip)clips.get(s)).close();
    }

    public static void setVolume(String s, float f) {
        Clip c = (Clip)clips.get(s);
        if (c == null) {
            return;
        }
        FloatControl vol = (FloatControl)c.getControl(FloatControl.Type.MASTER_GAIN);
        vol.setValue(f);
    }

    public static boolean isPlaying(String s) {
        Clip c = (Clip)clips.get(s);
        if (c == null) {
            return false;
        }
        return c.isRunning();
    }
}
