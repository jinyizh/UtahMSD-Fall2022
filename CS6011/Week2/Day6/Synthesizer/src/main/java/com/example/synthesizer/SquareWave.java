package com.example.synthesizer;

public class SquareWave implements AudioComponent {
    int frequency; // in Hz
    final int maxValue = Short.MAX_VALUE; // max volume

    @Override
    public AudioClip getClip() {
        AudioClip ac = new AudioClip();
        for (int i = 0; i < AudioClip.totalSample; i++) {
            if ((double) (this.frequency * i / AudioClip.sampleRate) % 1 > 0.5) {
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

    }
}
