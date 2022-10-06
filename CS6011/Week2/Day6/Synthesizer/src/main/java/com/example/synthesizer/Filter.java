package com.example.synthesizer;

public class Filter {
    private int scale; // scales less than 1 make the sound quieter; scales greater than 1 make sounds louder.

    public Filter(int scale) {
        this.scale = scale;
    }
}
