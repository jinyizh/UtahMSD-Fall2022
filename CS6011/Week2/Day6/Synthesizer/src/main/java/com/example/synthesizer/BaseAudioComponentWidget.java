package com.example.synthesizer;

public class BaseAudioComponentWidget implements AudioComponent {

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

    @Override
    public void removeInput(AudioComponent input) {
        AudioComponent.super.removeInput(input);
    }
}
