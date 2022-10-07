package com.example.synthesizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*; // unused

class AudioClipTest {
    @Test
    void testAll() {
        AudioClip ac = new AudioClip();
        for (int i = 0; i < AudioClip.totalSample; i++) {
            // generate random number: Math.random() * (max - min + 1) + min
            int rand = (int) (Math.random() * (Short.MAX_VALUE - Short.MIN_VALUE + 1) + Short.MIN_VALUE);
            ac.setSample(i, rand);
            Assertions.assertEquals(ac.getSample(i), rand);
        }
    }
}