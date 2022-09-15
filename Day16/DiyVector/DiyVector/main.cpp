//
//  main.cpp
//  DiyVector
//
//  Created by Jinyi Zhou on 9/13/22.
//

#include <iostream>

struct MyVec {
    int* data;
    int capacity;
    int size;
};

MyVec makeVector(int initialCapacity) {
    MyVec vec;
    vec.capacity = initialCapacity;
    vec.size = 0;
    vec.data = new int[vec.size];
    return vec;
}

void freeVector(MyVec vec) {
    delete [] vec.data;
    vec.data = nullptr;
}

void pushBack(MyVec& vec, int i) {
    vec.data[vec.size] = i;
    vec.size++;
}

void popBack(MyVec& vec) {
    vec.data[vec.size - 1] = NULL;
    vec.size--;
}

int get(MyVec vec, int i) {
    return vec.data[i];
}

void set(MyVec& vec, int i, int newValue) {
    vec.data[i] = newValue;
}

void growVector(MyVec vec) {
    int* temp = new int[2 * vec.capacity];
    for (int i = 0; i < vec.size; i++) {
        temp[i] = vec.data[i];
    }
    delete [] vec.data;
    vec.data = temp;
    vec.capacity = 2 * vec.capacity;
    temp = nullptr;
}

class MyVector {
private:
    int* data;
    int capacity;
    int size;
public:
    MyVector(int capacity) {
        this->capacity = capacity;
        this->size = 0;
    }
    MyVector(int d[], int size) {
        this->setSize(size);
        this->data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = d[i];
        }
    }
    void setSize(int size) {
        this->size = size;
    }
    void setCapacity(int capacity) {
        this->capacity = capacity;
    }
    void setData(int* data) {
        this->data = data;
    }
    int getSize() {
        return size;
    }
    int getCapacity() {
        return capacity;
    }
    int get(int i) {
        return this->data[i];
    }
    void set(int i, int newValue) {
        this->data[i] = newValue;
    }
    void push_back(int value) {
        this->data[this->size] = value;
        this->size++;
    }
    void pop_back(int value) {
        this->data[this->size - 1] = NULL;
        this->size--;
    }
    void growVector() {
        int* temp = new int[2 * this->capacity];
        for (int i = 0; i < this->size; i++) {
            temp[i] = this->data[i];
        }
        delete [] this->data;
        this->data = temp;
        this->capacity = 2 * this->capacity;
        temp = nullptr;
    }
    void freeVector() {
        delete [] this->data;
        this->data = nullptr;
        this->size = 0;
        this->capacity = 0;
    }
};

int main(int argc, const char * argv[]) {
    // create a MyVec with capacity of 4 and size of 2
    MyVec vec1 = makeVector(4);
    int size = 2;
    for (int i = 0; i < size; i++) {
        vec1.data[i] = i; // value of elements
        vec1.size++;
    }
    
    // test result:
    std::cout << "The elements in the struct vec1 are: " << std::endl;
    for (int i = 0; i < vec1.size; i++) {
        std::cout << vec1.data[i] << std::endl;
    }
    
    pushBack(vec1, 4);
    std::cout << "After pushing back 4 to the struct, the elements in the struct vec1 are: " << std::endl;
    for (int i = 0; i < vec1.size; i++) {
        std::cout << vec1.data[i] << std::endl;
    }
    
    popBack(vec1);
    std::cout << "After popping back, the elements in the struct vec1 are now: " << std::endl;
    for (int i = 0; i < vec1.size; i++) {
        std::cout << vec1.data[i] << std::endl;
    }
    
    std::cout << "Get the 1st element in the struct using the get function: " << std::endl;
    int result = get(vec1, 1);
    std::cout << result << std::endl;
    
    std::cout << "Set the 1st element to 2 in the struct using the set function: " << std::endl;
    set(vec1, 1, 2);
    std::cout << vec1.data[1] << std::endl;
    
    // initialize MyVector class
    int data[3] = {1, 2, 3};
    MyVector vector1 = MyVector(data, 3);
    return 0;
}
