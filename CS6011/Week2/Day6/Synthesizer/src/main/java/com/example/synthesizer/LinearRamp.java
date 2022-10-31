package com.example.synthesizer;

public class LinearRamp implements AudioComponent {
    float start; // in Hz
    float stop; // in Hz

    public LinearRamp(float start, float stop) {
        this.start = start;
        this.stop = stop;
    }

    @Override
    public AudioClip getClip() {
        AudioClip ac = new AudioClip();
        for (int i = 0; i < AudioClip.totalSample; i++) {
            ac.setSample(i,
                    (int) ((this.start * (AudioClip.totalSample - i ) + this.stop * i ) / AudioClip.totalSample));
        }
        return ac;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public void setStop(float stop) {
        this.stop = stop;
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
