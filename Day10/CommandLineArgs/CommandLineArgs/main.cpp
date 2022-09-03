//
//  main.cpp
//  CommandLineArgs
//
//  Created by Jinyi Zhou on 9/1/22.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    
    for (int i = 0; i < argc; i++) {
        std::cout << argv[i] << std::endl;
    }
    
    return 0;
}
