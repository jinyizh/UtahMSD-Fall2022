//
// Created by Jinyi Zhou on 3/1/23.
//

#ifndef TEST_RC4_H
#define TEST_RC4_H

#include <cstdint>
#include <string>

class rc4{
public:
    int i, j;
    uint8_t s[256];

public:
    rc4(std::string password);
    void swap(int a, int b);
    uint8_t genNextByte();
};


#endif //TEST_RC4_H
