//
//  main.cpp
//  VectorUpgrades
//
//  Created by Jinyi Zhou on 9/16/22.
//

#include <iostream>

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
    void pop_back() {
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
    ~MyVector() {
        delete [] this->data;
        this->data = nullptr;
        this->size = 0;
        this->capacity = 0;
    }
};

int main(int argc, const char * argv[]) {
    
    // initialize MyVector class
    int data[3] = {1, 2, 3};
    MyVector vector1 = MyVector(data, 3);
    
    // test results:
    int sizeOfVector = vector1.getSize();
    std::cout << "The elements in the class are: " << std::endl;
    for (int i = 0; i < sizeOfVector; i++) {
        int element = vector1.get(i);
        std::cout << element << std::endl;
    }
    std::cout << "The vector (class) has a size of " << sizeOfVector << std::endl;
    
    vector1.push_back(4);
    int newSizeOfVector = vector1.getSize();
    std::cout << "After pushing back 4 to the class, the elements in the class vector1 are:" << std::endl;
    for (int i = 0; i < newSizeOfVector; i++) {
        int element = vector1.get(i);
        std::cout << element << std::endl;
    }
    
    vector1.pop_back();
    int newSizeOfVector1 = vector1.getSize();
    std::cout << "After popping back, the elements in the class vector1 are:" << std::endl;
    for (int i = 0; i < newSizeOfVector1; i++) {
        int element = vector1.get(i);
        std::cout << element << std::endl;
    }
    
    std::cout << "Get the 1st element in the class: " << std::endl;
    std::cout << vector1.get(1) << std::endl;
    
    std::cout << "Set the 1st element to 6 in the class, the elements are now: " << std::endl;
    vector1.set(1, 6);
    int sizeOfVector2 = vector1.getSize();
    for (int i = 0; i < sizeOfVector2; i++) {
        int element = vector1.get(i);
        std::cout << element << std::endl;
    }
    std::cout << "Set the capacity to 2." << std::endl;
    vector1.setCapacity(2);
    vector1.growVector();
    std::cout << "After growing, the capacity is now: " << vector1.getCapacity() << std::endl;
    return 0;
}
