//
//  main.cpp
//  NumberRepresentations
//
//  Created by Jinyi Zhou on 9/7/22.
//

#include <iostream>
#include <cstdint>
#include <cmath>
#include <fstream>

bool approxEquals(double a, double b, double tolerance) {
    return fabs(a - b) < tolerance;
}

void loadCharacters(std::string s) {
    std::ifstream fin(s);
    char c;
    while (fin >> c) {
        std::cout << c << std::endl;
    }
}

int main(int argc, const char * argv[]) {
    int a[] = { 7, 2, -56, 32, 1, 7, -2, 7 };
    std::cout << "size: " << sizeof(a) << "\n";

    std::cout << sizeof(char) << std::endl;
    std::cout << sizeof(int8_t) << std::endl;
    std::cout << sizeof(int16_t) << std::endl;
    std::cout << sizeof(int64_t) << std::endl;
    std::cout << sizeof(uint8_t) << std::endl;
    std::cout << sizeof(uint16_t) << std::endl;
    std::cout << sizeof(uint64_t) << std::endl;
    
    std::cout << INT8_MAX << std::endl;
    std::cout << INT8_MIN << std::endl;
    std::cout << INT16_MAX << std::endl;
    std::cout << INT16_MIN << std::endl;
    std::cout << INT64_MAX << std::endl;
    std::cout << INT64_MIN << std::endl;
    
    std::cout << UINT8_MAX << std::endl;
    std::cout << 0xFF << std::endl;
    std::cout << UINT16_MAX << std::endl;
    std::cout << 0xFFFF << std::endl;
    std::cout << UINT64_MAX << std::endl;
    std::cout << 0xFFFFFFFFFFFFFFFF << std::endl;
    
    std::cout << INT64_MAX << std::endl;
    std::cout << INT64_MAX + 1 << std::endl;
    std::cout << INT64_MIN << std::endl;
    std::cout << INT64_MIN - 1 << std::endl;
    
    float fl = .1 + .2;
//    assert(fl == 0.3);
    std::cout << fl << std::endl;
    
    if (approxEquals(0.01, 0.01, 0.001)) {
        std::cout << "They are approximatelly equal!" << std::endl;
    } else {
        std::cout << "They are not approximatelly equal!" << std::endl;
    }
    
    loadCharacters("UTF-8-demo.txt");
    
    return 0;
}
