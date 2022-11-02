package com.example.synthesizer;

public class Filter implements AudioComponent { // changes frequency
    private double scale;
    private AudioComponent input;

    public Filter(double scale) {
        this.scale = scale;
        this.input = null;
    }

    @Override
    public AudioClip getClip() {
        System.out.println("filter get clip, scale is " + scale);
        if (this.input != null) {
            AudioClip original = this.input.getClip();
            AudioClip result = new AudioClip();
            for (int i = 0; i < AudioClip.totalSample; i++) {
                if ((result.getSample(i) * scale) > Short.MAX_VALUE) { // clamping max
                    result.setSample(i, Short.MAX_VALUE);
                } else if (result.getSample(i) < Short.MIN_VALUE) { // clamping min
                    result.setSample(i, Short.MIN_VALUE);
                } else {
                    result.setSample(i, (int) (original.getSample(i) * scale));
                }
            }
            return result;
        }
        return null;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input) { // use this method to connect to input(s)
        this.input  = input;
    }

    public void removeInput(AudioComponent input) {
        this.input = null;
    };
}