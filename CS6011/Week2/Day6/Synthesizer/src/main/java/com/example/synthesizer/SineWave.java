package com.example.synthesizer;

public class SineWave implements AudioComponent {
    int frequency;
    int maxValue = Short.MAX_VALUE; // maximum volume

    @Override
    public AudioClip getClip() {

        return null;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {

    }

    public SineWave(int frequency) {
        this.frequency = frequency;
    }
}
