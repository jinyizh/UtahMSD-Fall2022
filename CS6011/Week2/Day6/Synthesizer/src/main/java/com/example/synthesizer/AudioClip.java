package com.example.synthesizer;

public class AudioClip {
    static int duration = 2; // seconds
    static int sampleRate = 44100; // num of samples (2 bytes per sample) per second
    private byte[] byteArray; // 2 bytes (16bits) is 1 sample
    public int getSample(int index) { // index of the "shortArray", not the actual byteArray
        int firstByte = byteArray[index * 2]; // littleEnd
        int secondByte = byteArray[index * 2 + 1]; // bigEnd
        firstByte &= 0x00000000FF;
        secondByte <<= 8;
        return firstByte | secondByte; // in the form of bigEnd+LittleEnd (little endian)
    }
    public void setSample(int index, int value) {
        byte firstByte = (byte) value;
        byte secondByte = (byte) (value >> 8);
        byteArray[index * 2] = firstByte;
        byteArray[index * 2 + 1] = secondByte;
    }
}
