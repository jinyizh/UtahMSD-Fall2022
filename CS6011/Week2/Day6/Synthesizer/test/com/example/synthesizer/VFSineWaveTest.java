package com.example.synthesizer;

import org.junit.jupiter.api.Test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import static org.junit.jupiter.api.Assertions.*;

class VFSineWaveTest {
    @Test
    void simpleTest() throws LineUnavailableException {
        Clip c = AudioSystem.getClip();
        AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);
        AudioComponent lr = new LinearRamp(50, 10000);

        VFSineWave vf = new VFSineWave(lr);

        AudioClip clip = vf.getClip();
        byte[] data = clip.getData();
        c.open(format16, data, 0, data.length); // Reads data from our byte array to play it.
        System.out.println("About to play...");
        c.start();
//        for (int i = 0; i < AudioClip.totalSample; i++) {
//            System.out.println(clip.getSample(i));
//        }
        c.loop(0);
        // Makes sure the program doesn't quit before the sound plays.
        while (c.getFramePosition() < AudioClip.totalSample || c.isActive() || c.isRunning()) {
//            System.out.println(c.getFramePosition());
        }
        System.out.println("Done.");
        c.close();
    }
}