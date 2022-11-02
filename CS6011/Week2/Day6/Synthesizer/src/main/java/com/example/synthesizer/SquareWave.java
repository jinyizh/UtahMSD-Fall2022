package com.example.synthesizer;

public class SquareWave implements AudioComponent {
    int frequency; // in Hz
    final int maxValue = Short.MAX_VALUE; // max volume

    public SquareWave(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public AudioClip getClip() {
        System.out.println("square wave get clip");
        AudioClip ac = new AudioClip();
        for (int i = 0; i < AudioClip.totalSample; i++) {
            if (((double) this.frequency * i / AudioClip.sampleRate) % 1 > 0.5) { // beware of the location of casting
                ac.setSample(i, this.maxValue);
            } else {
                ac.setSample(i, -this.maxValue);
            }
        }
        return ac;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        assert false;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}