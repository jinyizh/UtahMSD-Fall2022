//
//  main.cpp
//  StatusCheck2
//
//  Created by Jinyi Zhou on 9/7/22.
//

#include <iostream>
#include <fstream>
#include <string>
#include <vector>

bool isVowel(char character) {
    char c = tolower(character);
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
}

int numberOfVowels(std::string s) {
    int num = 0;
    for (int i = 0; i < s.size(); i++) {
        if (isVowel(s.at(i))) {
            num++;
        }
    }
    return num;
}

int main(int argc, const char * argv[]) {
    
//    Part 2:
//    change directory to the folder, and excute the following commands:
//    clang++ -std=c++11 -c main.cpp
//    clang++ -o main main.o my_lib.h my_lib.cpp
//    ./main
    
//    Part 3:
//    What's the difference between:
//    An array and a structure?
//    A structure can hold different types of variables, while array can only hold variables of same type.
//    Structures do not have the concept of pointers, while array is a pointer which points to the first element of the array.
//
//    An array and a vector?
//    An array has a fixed size, while a vector has a dynamic size.
//    An array is index-based, while a vector is not.
//    Accessing the elements of an array takes less time than accessing the elements of a vector.
    
    struct Dog {
        std::string name = "LittleDog";
        int age = 4;
        bool isVaccinated = true;
    };
    
    std::vector<Dog> dogs;
    
    std::ifstream fin("star_wars.txt");
    std::vector<std::string> words;
    std::string word;
    while (fin >> word) {
        words.push_back(word);
    }
    int numOfVowel = 0;
    for (int i = 0; i < word.size(); i++) {
        numOfVowel += numberOfVowels(words[i]);
    }
    std::cout << "the number of vowels in the file is " << numOfVowel << std::endl;
    
    return 0;
}
