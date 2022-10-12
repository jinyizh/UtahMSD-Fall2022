package com.example.synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent{
    ArrayList<AudioComponent> inputs = new ArrayList<>();

    public Mixer(AudioComponent input) {
        this.inputs.add(input);
    }

    public Mixer(ArrayList<AudioComponent> inputs) { // another constructor
        this.inputs = inputs;
    }

    @Override
    public AudioClip getClip() {
        AudioClip result = new AudioClip();
        if (this.inputs.size() != 0) {
            for (AudioComponent input : inputs) { // writing for each loop inside turns out to be much slower!
                AudioClip original = input.getClip();
                for (int i = 0; i < AudioClip.totalSample; i++) {
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
