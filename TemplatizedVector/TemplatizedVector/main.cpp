//
//  main.cpp
//  TemplatizedVector
//
//  Created by Jinyi Zhou & Junming Jin on 9/15/22.
//

#include "MyVector.hpp"
#include <iostream>
#include <string>
#include <vector>

void testInt() {
    // test makeVector
    MyVector<int> vec1(5);
    assert(vec1.getCapacity() == 5);
    assert(vec1.getSize() == 0);
    
    // test pushBack
    int data1[5] = {1, 2, 3, 4, 5};
    for (int n : data1) {
        vec1.pushBack(n);
    }
    assert(vec1.getSize() == 5);
    
    // test growVector
    vec1.pushBack(6);
    assert(vec1.getCapacity() == 10);
    assert(vec1.getSize() == 6);
    
    // test set & get
    vec1.set(3, 10);
    assert(vec1.get(3) == 10);
    
    // test popBack
    int data2[6] = {6, 5, 10, 3, 2, 1};
    for (int i = 0; i < 3; i++) {
        assert(vec1.popBack() == data2[i]);
        assert(vec1.getSize() == 5 - i);
    }
    
    // test copy constructor and []
    MyVector vec2(vec1);
    assert(vec2 == vec1); // test ==
    vec2[0] = 100;
    assert(vec2 != vec1); // test !=
    assert(vec1 < vec2); // test <
    assert(vec2 > vec1); // test >
    assert(vec1 <= vec2); // test <=
    assert(vec2 >= vec1); // test >=
    
    // test < with some edge cases
    int data3[4] = {9, 9, 4, 5};
    int data4[4] = {9, 9, 4, 2};
    int data5[5] = {9, 9, 4, 2, 8};
    
    MyVector<int> vec3(data3, 4);
    MyVector<int> vec4(data4, 4);
    MyVector<int> vec5(data5, 5);
    assert(vec4 < vec3);
    assert(vec4 < vec5);
    
    // test operator =
    MyVector<int> vec6;
    vec6 = vec2; // this is not assignment! this initializes a MyVector
    assert(vec6 == vec2);
    
    std::cout << "All tests passed for MyVector<int>!\n";
}

void testDouble() {
    // test makeVector
    MyVector<double> vec1(5);
    assert(vec1.getCapacity() == 5);
    assert(vec1.getSize() == 0);
    
    // test pushBack
    double data1[5] = {1.2, 2.3, 3.1, 4.6, 5.9};
    for (double n : data1) {
        vec1.pushBack(n);
    }
    assert(vec1.getSize() == 5);
    
    // test growVector
    vec1.pushBack(6.7);
    assert(vec1.getCapacity() == 10);
    assert(vec1.getSize() == 6);
    
    // test set & get
    vec1.set(3, 10.1);
    assert(vec1.get(3) == 10.1);
    
    // test popBack
    double data2[6] = {6.7, 5.9, 10.1};
    for (int i = 0; i < 3; i++) {
        assert(vec1.popBack() == data2[i]);
        assert(vec1.getSize() == 5 - i);
    }
    
    // test copy constructor and []
    // now vec1 is {1.2, 2.3, 3.1}
    MyVector vec2(vec1);
    assert(vec2 == vec1); // test ==
    vec2[0] = 7.7;
    assert(vec2 != vec1); // test !=
    assert(vec1 < vec2); // test <
    assert(vec2 > vec1); // test >
    assert(vec1 <= vec2); // test <=
    assert(vec2 >= vec1); // test >=
    
    // test < with some edge cases
    double data3[4] = {9.1, 9.4, 4.1, 5.2};
    double data4[4] = {9.1, 9.4, 4.1, 2.01};
    double data5[5] = {9.1, 9.4, 4.1, 2.01, 8.1};
    
    MyVector<double> vec3(data3, 4);
    MyVector<double> vec4(data4, 4);
    MyVector<double> vec5(data5, 5);
    assert(vec4 < vec3);
    assert(vec4 < vec5);
    
    // test operator =
    MyVector vec6 = vec2; // this is not assignment! this initializes a MyVector
    vec6  = vec3;
    assert(vec6 == vec3);
    
    std::cout << "All tests passed for MyVector<double>!\n";
}

void testChar() {
    // test makeVector
    MyVector<char> vec1(5);
    assert(vec1.getCapacity() == 5);
    assert(vec1.getSize() == 0);
    
    // test pushBack
    char data1[5] = {'a', 'b', 'c', 'd', 'e'};
    for (char n : data1) {
        vec1.pushBack(n);
    }
    assert(vec1.getSize() == 5);
    
    // test growVector
    vec1.pushBack('f');
    assert(vec1.getCapacity() == 10);
    assert(vec1.getSize() == 6);
    
    // test set & get
    vec1.set(3, 'z');
    assert(vec1.get(3) == 'z');
    
    // test popBack
    char data2[6] = {'f', 'e', 'z'};
    for (int i = 0; i < 3; i++) {
        assert(vec1.popBack() == data2[i]);
        assert(vec1.getSize() == 5 - i);
    }
    
    // test copy constructor and []
    // now vec1 is {'a', 'b', 'c', }
    MyVector vec2(vec1);
    assert(vec2 == vec1); // test ==
    vec2[0] = 's';
    assert(vec2 != vec1); // test !=
    assert(vec1 < vec2); // test <
    assert(vec2 > vec1); // test >
    assert(vec1 <= vec2); // test <=
    assert(vec2 >= vec1); // test >=
    
    // test < with some edge cases
    char data3[4] = {'q', 'q', 'w', 't'};
    char data4[4] = {'q', 'q', 'w', 'h'};
    char data5[5] = {'q', 'q', 'w', 'h', 'a'};
    
    MyVector<char> vec3(data3, 4);
    MyVector<char> vec4(data4, 4);
    MyVector<char> vec5(data5, 5);
    assert(vec4 < vec3);
    assert(vec4 < vec5);
    
    // test operator =
    MyVector vec6 = vec2; // still call copy constructor
    vec6  = vec3; // call operator =
    assert(vec6 == vec3);
    
    std::cout << "All tests passed for MyVector<char>!\n";
}

void testString() {
    // test makeVector
    MyVector<std::string> vec1(5);
    assert(vec1.getCapacity() == 5);
    assert(vec1.getSize() == 0);
    
    // test pushBack
    std::string data1[5] = {"hello", "world", "this", "is", "me"};
    for (std::string s : data1) {
        vec1.pushBack(s);
    }
    assert(vec1.getSize() == 5);
    
    // test growVector
    vec1.pushBack("wonderful!");
    assert(vec1.getCapacity() == 10);
    assert(vec1.getSize() == 6);
    
    // test set & get
    vec1.set(3, "is not");
    assert(vec1.get(3) == "is not");
    
    // test popBack
    std::string data2[6] = {"wonderful!", "me", "is not"};
    for (int i = 0; i < 3; i++) {
        assert(vec1.popBack() == data2[i]);
        assert(vec1.getSize() == 5 - i);
    }
    
    // test copy constructor and []
    // now vec1 is {'a', 'b', 'c', }
    MyVector vec2(vec1);
    assert(vec2 == vec1); // test ==
    vec2[0] = 's';
    assert(vec2 != vec1); // test !=
    assert(vec1 < vec2); // test <
    assert(vec2 > vec1); // test >
    assert(vec1 <= vec2); // test <=
    assert(vec2 >= vec1); // test >=
    
    // test < with some edge cases
    std::string data3[4] = {"q", "q", "w", "t"};
    std::string data4[4] = {"q", "q", "w", "h"};
    std::string data5[5] = {"q", "q", "w", "t", "a"};
    
    MyVector<std::string> vec3(data3, 4);
    MyVector<std::string> vec4(data4, 4);
    MyVector<std::string> vec5(data5, 5);
    assert(vec4 < vec3);
    assert(vec4 < vec5);
    
    // test operator =
    MyVector vec6 = vec2; // still call copy constructor
    vec6  = vec3; // call operator =
    assert(vec6 == vec3);
    
    std::cout << "All tests passed for MyVector<string>!\n";
}

int main() {
    testInt();
    testChar();
    return 0;
}
