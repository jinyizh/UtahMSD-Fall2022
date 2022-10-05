package com.example.synthesizer;

public class AudioClip {
    static int duration = 2; // seconds
    static int sampleRate = 44100; // num of samples (2 bytes per sample) per second
    private byte[] byteArray; // 2 bytes (16bits) is 1 sample
    public AudioClip(){
/*        duration = 2;
        sampleRate = 44100;
        byteArray = new byte[duration * sampleRate];*/
        duration = 0;
        sampleRate = 0;
        this.byteArray = new byte[0];
    }
    public int getSample(int index) {
        int firstByte = byteArray[2 * index];
        int secondByte = byteArray[2 * index + 1];
        firstByte &= 0x00000000FF;
        secondByte <<= 8;
        return firstByte | secondByte;
    }
    public void setSample(int index, int value) {
        byte firstByte = (byte) value;
        byte secondByte = (byte) (value >> 8);
        byteArray[index * 2] = firstByte;
        byteArray[index * 2 + 1] = secondByte;
    }

}
