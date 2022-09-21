//
//  main.cpp
//  STLAndMyVector
//
//  Created by Jinyi Zhou on 9/21/22.
//

#include "MyVector.hpp"
#include <iostream>
#include <numeric>

int main() {
    int data[5] = {4, 2, 1, 3, 5};
    MyVector<int> vec1(data, 5);
    
    std::cout << "print all the elements in vec1: " << std::endl;
    for (int i : vec1) {
        std::cout << i << std::endl;
    }
    
    std::sort(vec1.begin(), vec1.end());
    std::cout << "after sorting, the elements in vec1 are: " << std::endl;
    for (int i : vec1) {
        std::cout << i << std::endl;
    }
    
    int* min = std::min_element(vec1.begin(), vec1.end());
    std::cout << "the minimum element in vec1 is: " << std::endl;
    std::cout << *min << std::endl;
    
    int sum = std::accumulate(vec1.begin(), vec1.end(), 0);
    std::cout << "the sum of the elements in vec1 is: " << std::endl;
    std::cout << sum << std::endl;
    
    std::cout << "the number of even numbers in vec1 is: " << std::endl;
    std::cout << std::count_if(vec1.begin(), vec1.end(), [](int num){ return num % 2 == 0; }) << std::endl;
    
    return 0;
}
