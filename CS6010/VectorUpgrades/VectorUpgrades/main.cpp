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
    
    return 0;
}
