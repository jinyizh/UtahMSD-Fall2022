#include <iostream>
#include <string>
#include "cmdline.h"

void use_arguments(int argc, char* argv[]) {
    bool test_seen = false;
    for (int i = 0; i < argc; i++) {
        if (argv[i] == "--help") {
            std::cout << "available commands:" << std::endl;
            std::cout << "--test: the only command that is allowed" << std::endl;
            std::cout << "no other commands are allowed so far" << std::endl;
            exit(0);
        }
        else if (argv[i] == "--test") {
            if (!test_seen) {
                std::cout << "Tests passed" << std::endl;
                test_seen = true;
            } else {
                std::cerr << "already tested";
                exit(1);
            }
        }
        else {
            std::cerr << "unrecognized argument inspected" << std::endl;
            exit(1);
        }
    }
}