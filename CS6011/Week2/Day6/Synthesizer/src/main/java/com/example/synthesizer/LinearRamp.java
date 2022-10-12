package com.example.synthesizer;

public class LinearRamp implements AudioComponent {
    int start; // in Hz
    int stop; // in Hz

    public LinearRamp(int start, int stop) {
        this.start = start;
        this.stop = stop;
    }

    @Override
    public AudioClip getClip() {
        AudioClip ac = new AudioClip();
        for (int i = 0; i < AudioClip.totalSample; i++) {
            ac.setSample(i, (this.start * (AudioClip.totalSample - i ) + this.stop * i ) / AudioClip.totalSample);
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
}
