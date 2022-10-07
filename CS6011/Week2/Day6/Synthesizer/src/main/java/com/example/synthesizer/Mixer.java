package com.example.synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent{
    ArrayList<AudioComponent> inputs = new ArrayList<>();

    @Override
    public AudioClip getClip() {
        AudioClip result = new AudioClip();
        if (this.inputs != null) {
            for (int i = 0; i < AudioClip.totalSample; i++) {
                for (AudioComponent input : inputs) {
                    if (result.getSample(i) + input.getClip().getSample(i) > Short.MAX_VALUE) { // clamping max
                        result.setSample(i, Short.MAX_VALUE);
                    } else if (result.getSample(i) + input.getClip().getSample(i) < Short.MIN_VALUE) { // clamping min
                        result.setSample(i, Short.MIN_VALUE);
                    } else {
                        result.setSample(i, result.getSample(i) + input.getClip().getSample(i));
                    }
                }
            }
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
