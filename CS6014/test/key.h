//
// Created by Jinyi Zhou on 3/1/23.
//

#ifndef TEST_KEY_H
#define TEST_KEY_H


#include <string>
#include <cstdint>

class Key {
public:
    std::string password;
    uint8_t key[8];
public:
    Key(std::string password);
    const std::string &getPassword() const;
    const uint8_t *getKey() const;
};


#endif //TEST_KEY_H
