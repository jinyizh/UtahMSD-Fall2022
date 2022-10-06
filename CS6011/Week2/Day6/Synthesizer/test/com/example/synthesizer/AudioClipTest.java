package com.example.synthesizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudioClipTest {

    @Test
    void getSample() {
        AudioClip ac = new AudioClip();
        ac.setSample(0, 1); // little end
        ac.setSample(1, 0); // big end
        int result = ac.getSample(0);
        Assertions.assertEquals(result, 1); // big end + little end
    }

    @Test
    void setSample() {
        AudioClip ac = new AudioClip();
        ac.setSample(0, 1);
        ac.setSample(1, 0);
        Assertions.assertEquals(ac.getSample(0), 1);
    }

    @Test
    void testAll() {
        AudioClip ac = new AudioClip();
        for (int i = Short.MIN_VALUE; i <= Short.MAX_VALUE; i++) {
            ac.setSample(0, 1);
        }
    }
}