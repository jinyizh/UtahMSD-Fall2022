#include <cstdlib>
#include <iostream>
#include <vector>
#include "Malloc.h"

int main() { // run to test

    Malloc malloc1;
    std::chrono::time_point<std::chrono::system_clock> start, end;
    const char* test = "master of software development";
    std::cout << "the test tring is: " << test << std::endl;

    start = std::chrono::system_clock::now();
    char* ptr = (char*) malloc1.allocate(sizeof(test));
    strcpy(ptr, test);
    malloc1.deallocate(ptr);
    end = std::chrono::system_clock::now();

    std::chrono::duration<double> durations = end - start;
    std::cout << "\nnow testing own malloc():" << std::endl;
    auto milliseconds = std::chrono::duration_cast<std::chrono::microseconds>(durations).count();
    std::cout << "own malloc-free running time is " << milliseconds << " milliseconds" << std::endl;

    start = std::chrono::system_clock::now(); // re-init
    char* ptr1 = (char*) malloc(sizeof(test));
    strcpy(ptr1, test);
    free(ptr1);
    end = std::chrono::system_clock::now();

    std::chrono::duration<double> durations1 = end - start;
    std::cout << "\nnow testing system malloc():" << std::endl;
    auto milliseconds1 = std::chrono::duration_cast<std::chrono::microseconds>(durations1).count();
    std::cout << "system malloc-free running time is " << milliseconds1 << " milliseconds" << std::endl;

    return 0;
}
