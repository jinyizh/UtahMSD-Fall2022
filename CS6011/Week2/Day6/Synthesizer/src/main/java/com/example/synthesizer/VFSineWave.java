package com.example.synthesizer;

import java.util.ArrayList;

public class VFSineWave implements AudioComponent {
    final int maxValue = Short.MAX_VALUE; // max volume
    AudioComponent input;

    public VFSineWave(AudioComponent input) {
        this.input = input;
    }

    @Override
    public AudioClip getClip() {
        AudioClip result = new AudioClip();
        if (this.input != null) {
            AudioClip original = input.getClip();
            float phase = 0; // be careful with the datatype
            for (int i = 0; i < AudioClip.totalSample; i++) {
                phase += (2 * Math.PI * original.getSample(i)) / AudioClip.sampleRate;
                result.setSample(i, (int) (this.maxValue * Math.sin(phase)));
            }
            return result;
        }
        return null;
    }

    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input) {
        this.input = input;
    }
}
