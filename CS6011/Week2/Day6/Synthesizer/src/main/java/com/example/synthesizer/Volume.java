package com.example.synthesizer;

public class Volume implements AudioComponent { // type of filters
    private final SineWave input;
    private final int scale;

    public Volume(SineWave input, int scale) {
        this.input = input;
        this.scale = scale;
    }
    @Override
    public AudioClip getClip() {
        AudioClip original = this.input.getClip();
        byte[] originalData = original.getData();
        AudioClip result = new AudioClip();
        for (int i = 0; i < AudioClip.totalSample; i++) {
            result.setSample(i, originalData[i] * scale);
            if (result.getSample(i) > Short.MAX_VALUE) result.setSample(i, Short.MAX_VALUE); // clamping max
            if (result.getSample(i) < Short.MIN_VALUE) result.setSample(i, Short.MIN_VALUE); // clamping min
        }
        return result;
    }

    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input) {
        assert false;
    }

}
