package com.example.synthesizer;

public class SineWave implements AudioComponent {
    int frequency; // in Hz
    final int maxValue = Short.MAX_VALUE; // maximum volume
//    int maxValue;

    @Override
    public AudioClip getClip() {
        AudioClip ac = new AudioClip();
        // sample[i] = maxValue * sine(2 * pi * frequency * i / sampleRate) -- sine curve within 1 second
        for (int i = 0; i < AudioClip.sampleRate * AudioClip.duration; i++) {
            ac.setSample(i, (int)(this.maxValue * Math.sin(2 * Math.PI * this.frequency * i /AudioClip.sampleRate)));
        }
        return ac;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        // !hasInput
    }

    public SineWave(int frequency) {
        this.frequency = frequency;
    }
}
