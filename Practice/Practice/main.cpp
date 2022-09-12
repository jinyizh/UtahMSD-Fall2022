//
//  main.cpp
//  Practice
//
//  Created by Jinyi Zhou on 9/1/22.
//

#include <iostream>
#include <vector>
#include <fstream>

struct student {
    std::string name;
    float height;
};

std::vector<student> readData(std::string filename) {
    std::ifstream ifstream(filename);
    std::string word1;
    int num = -1;
    ifstream >> word1;
    num = stoi(word1);
}

int main(int argc, const char * argv[]) {
    std::vector<int> ints = {1, 2, 3, 4, 5};
    return 0;
}
