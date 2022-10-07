package com.example.synthesizer;

public class Mixer implements AudioComponent{
    @Override
    public AudioClip getClip() {
        return null;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {

    }
}
