package com.example.synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent{
    ArrayList<AudioComponent> inputs = new ArrayList<>();

    public Mixer(AudioComponent input) {
        this.inputs.add(input);
    }

    public Mixer(ArrayList<AudioComponent> inputs) { // more convenient
        this.inputs = inputs;
    }

    @Override
    public AudioClip getClip() {
        AudioClip result = new AudioClip();
        if (this.inputs != null && this.inputs.size() != 0) {
            for (int i = 0; i < AudioClip.totalSample; i++) {
                for (AudioComponent input : inputs) {
                    AudioClip original = input.getClip();
                    if (result.getSample(i) + original.getSample(i) > Short.MAX_VALUE) { // clamping max
                        result.setSample(i, Short.MAX_VALUE);
                    } else if (result.getSample(i) + original.getSample(i) < Short.MIN_VALUE) { // clamping min
                        result.setSample(i, Short.MIN_VALUE);
                    } else {
                        result.setSample(i, result.getSample(i) + original.getSample(i));
                    }
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
    public void connectInput(AudioComponent input) { // connect to 1 input every time
        this.inputs.add(input);
    }
}
