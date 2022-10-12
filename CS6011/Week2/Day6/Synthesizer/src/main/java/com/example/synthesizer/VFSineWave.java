package com.example.synthesizer;

import java.util.ArrayList;

public class VFSineWave implements AudioComponent {
    int frequency; // in Hz
    final int maxValue = Short.MAX_VALUE; // max volume
    ArrayList<AudioComponent> inputs = new ArrayList<>();

    public VFSineWave(AudioComponent input) {
        this.inputs.add(input);
    }

    @Override
    public AudioClip getClip() {
        AudioClip result = new AudioClip();
        if (this.inputs.size() != 0) {
            for (AudioComponent input : inputs) { // writing for each loop inside turns out to be much slower!
                AudioClip original = input.getClip();
                int phase = 0;
                for (int i = 0; i < AudioClip.totalSample; i++) {
                    phase += (2 * Math.PI * original.getSample(i)) / AudioClip.sampleRate;
                    result.setSample(i, (int) (this.maxValue * Math.sin(phase)));
                }
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
        this.inputs.add(input);
    }
}
