//
//  main.cpp
//  Practice
//
//  Created by Jinyi Zhou on 9/1/22.
//

#include <iostream>
#include <string>
#include <vector>

int main(int argc, const char * argv[]) {

    //2d array
    int a[5][5];
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 5; j++) {
            a[i][j] = i + j;
        }
    }
    
    if (argc > 0) {
        for (int i = 0; i < argc; i++) {
            std::cout << argv[i] << std::endl;
        }
    }
    
    if (argc > 0) {
        std::string firstArgument = argv[0];
        std::cout << firstArgument;
    }
        
    
    return 0;
}
