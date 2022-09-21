//
//  main.cpp
//  TemplatizedVector
//
//  Created by Jinyi Zhou & Junming Jin on 9/15/22.
//

#ifndef vector_hpp
#define vector_hpp

#include <cstddef>
#include <iostream>
#include <cmath>

template<typename T>

class MyVector {
private:
    T* data_;
    size_t capacity_;
    size_t size_;
    
public:
    MyVector() {
        capacity_ = 0;
        size_ = 0;
        data_ = new T[capacity_];
    }
    
    MyVector(const size_t initialCapacity) {
        data_ = new T[initialCapacity];
        capacity_ = initialCapacity;
        size_ = 0;
    }
    
    MyVector(T data[], size_t size) {
        size_ = size;
        capacity_ = size;
        data_ = new T[size]; // allocate spaces
        for (size_t i = 0; i < size; i++) { // copy the data
            data_[i] = data[i];
        }
    }
    
    MyVector(const MyVector<T>& rhs) { // copy constructor
        if (this != &rhs) {
            capacity_ = rhs.getCapacity();
            size_ = rhs.getSize();
            data_ = new T[capacity_];
            for (int i = 0; i < rhs.getSize(); i++) {
                data_[i] = rhs.get(i);
            }
        }
    }
    
    ~MyVector() {
        delete[] data_;
        data_ = nullptr;
        size_ = 0;
        capacity_ = 0;
        std::cout << "Destructor excuted!\n";
    }
    
    // getters and setters:
    
    size_t getSize() const {
        return size_;
    }
    
    size_t getCapacity() const {
        return capacity_;
    };
    
    T get(size_t index) const {
        if (index >= size_) {
            throw std::invalid_argument("Index out of range!");
        }
        return data_[index];
    };
    
    void set(size_t index, T newValue) {
        if (index >= size_) {
            throw std::invalid_argument("Index out of range!");
        }
        data_[index] = newValue;
    }
    
    void pushBack(T i) {
        growVector();
        data_[size_++] = i;
    };
    
    T popBack() {
        size_ -= 1;
        return data_[size_];
    };
    
    void growVector() {
        if (capacity_ == size_) {
            T* temp = new T[capacity_ * 2];
            for (int i = 0; i < size_; i++) {
                temp[i] = data_[i];
            }
            delete[] data_;
            data_ = temp;
            capacity_ *= 2;
            temp = nullptr;
        }
    };
        
    // operator overload:
    MyVector<T>& operator=(const MyVector<T>& rhs) {
        std::cout << "using operator =\n";
        if (this == &rhs) return *this;
        // if object not empty, delete old data to avoid memory leak
        if (capacity_ != 0) {
            delete [] data_;
            data_ = nullptr;
        }
        capacity_ = rhs.getCapacity();
        size_ = rhs.getSize();
        data_ = new T[capacity_];
        for (int i = 0; i < rhs.getSize(); i++) {
            data_[i] = rhs.get(i);
        }
        return *this;
    };
    
    T& operator[](size_t i) {
        return data_[i];
    };
    
    bool operator==(const MyVector<T>& rhs) {
        if (size_ != rhs.getSize()) return false;
        for (int i = 0; i < size_; i++) {
            if (data_[i] != rhs.get(i)) {
                return false;
            }
        }
        return true;
    };
    
    bool operator!=(const MyVector<T>& rhs) {
        return ! (*this == rhs);
    };
    
    bool operator<(const MyVector<T>& rhs) {
        size_t minSize = std::min(size_, rhs.getSize());
        for (int i = 0; i < minSize; i++) {
            if (data_[i] > rhs.get(i)) {
                return false;
            } else if (data_[i] < rhs.get(i)) {
                return true;
            }
        }
        return size_ < rhs.getSize();
    };
    
    bool operator<=(const MyVector<T>& rhs) {
        return *this < rhs || *this == rhs;
    };
    
    bool operator>=(const MyVector<T>& rhs) {
        return !(*this < rhs);
    };
    
    bool operator>(const MyVector<T>& rhs) {
        return !(*this <= rhs);
    };
    
    MyVector& operator+=(const MyVector<T>& rhs) {
        for (int i = 0; i < std::min(size_, rhs.getSize()); i++) {
            data_[i] += rhs.get(i);
        }
        return *this;
    }
    
};

template<typename T>
std::ostream& operator<<(std::ostream& out, const MyVector<T>& vec) {
    for (int i = 0; i < vec.getSize(); i++) {
        std::cout << vec.get(i) << " ";
    }
    std::cout << std::endl;
    return out;
}

#endif /* vector_hpp */
