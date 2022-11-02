package com.example.synthesizer;

public class SineWave implements AudioComponent {
    int frequency; // in Hz
    final int maxValue = Short.MAX_VALUE; // max volume

    public SineWave(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public AudioClip getClip() {
        System.out.println("sine wave get clip");
        AudioClip ac = new AudioClip();
        for (int i = 0; i < AudioClip.totalSample; i++) {
            ac.setSample(i, (int) (this.maxValue * Math.sin(2 * Math.PI * this.frequency * i / AudioClip.sampleRate)));
        }
        return ac;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        assert false; // !hasInput
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}