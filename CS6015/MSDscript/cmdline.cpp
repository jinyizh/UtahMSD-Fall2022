#include <iostream>
#include <vector>
#include "cmdline.h"

void use_arguments(int argc, char* argv[]) {
    bool test_seen = false;
    std::vector<std::string> argvs; // init string vec (argv is char* vec)
    for (int i  = 0; i < argc; i++) {
        argvs.__emplace_back(argv[i]); // suggested by clang to replace push_back
    }
    for (int i = 1; i < argc; i++) {
        if (argvs[i] == "--help") { // comparison of argv[i] and a string is not implicit
            std::cout << "available commands:" << std::endl;
            std::cout << "--test: the only command that is allowed" << std::endl;
            std::cout << "no other commands are allowed so far" << std::endl;
            exit(0);
        }
        else if (argvs[i] == "--test") {
            if (!test_seen) {
                std::cout << "Tests passed" << std::endl;
                test_seen = true;
            } else {
                std::cerr << "already tested" << std::endl;
                exit(1);
            }
        }
        else {
            std::cerr << "unrecognized argument inspected" << std::endl;
            exit(1);
        }
    }
}