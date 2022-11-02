package com.example.synthesizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

class FilterTest {
    @Test
    void simpleTest() throws LineUnavailableException {
        Clip c = AudioSystem.getClip();
        AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);
        AudioComponent gen = new SineWave(440);
        double scale = 0.1;
        Filter vl = new Filter(scale);
        vl.connectInput(gen);

        AudioClip clip = vl.getClip();
        byte[] data = clip.getData();
        c.open(format16, data, 0, data.length); // Reads data from our byte array to play it.
        System.out.println("About to play...");
        c.start();
//        c.loop(2);
        // Makes sure the program doesn't quit before the sound plays.
        while (c.getFramePosition() < AudioClip.totalSample || c.isActive() || c.isRunning()) {
//            System.out.println(c.getFramePosition()); // Do nothing while we wait for the note to play.
        }
        System.out.println("Done.");
        c.close();
    }

    @Test
    void thoroughTest() throws LineUnavailableException {
        AudioComponent gen = new SineWave(220);
        double scale = 0.1;
        Filter vl = new Filter(scale);
        vl.connectInput(gen);
        for (int i = 0; i < AudioClip.totalSample; i += 20) { // would be too slow if i++
            Assertions.assertEquals((int) Math.abs(vl.getClip().getSample(i) - scale * gen.getClip().getSample(i)), 0);
        }
    }
}