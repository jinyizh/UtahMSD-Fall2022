#include <iostream>
#include <vector>
#include <fstream>

struct MyVector {
    double * data;
    int size;
    int capacity;
};

double arrayModSum(MyVector& vec) {
    double * temp = new double[vec.size];
    int sum = 0;
    for (int i = 0; i < vec.size; i++) {
        temp[i] = vec.data[i] + 1;
        sum += temp[i];
    }
    delete [] vec.data;
    vec.data = temp;
    temp = nullptr;
    return sum;
}

void growMyVector(MyVector& vec) {
    if (vec.size == vec.capacity) {
        double * temp = new double[2 * vec.size];
        for (int i = 0; i < vec.size; i++) {
            temp[i] = vec.data[i];
        }
        delete [] vec.data;
        vec.data = temp;
        temp = nullptr;
    }
}

int main(int argc, const char * argv[]) {
    MyVector vec1;
    std::cout << "Input the array size: " << std::endl;
    std::cin >> vec1.size;
    vec1.data = new double[vec1.size];
    
    for (int i = 0; i < vec1.size; i++) {
        vec1.data[i] = 1.0;
    }
    
    double newSum = arrayModSum(vec1);
    std::cout << "The modified sum is: " << newSum << std::endl;
    
    growMyVector(vec1);
    std::cout << "After growing, the capacity becomes: " << vec1.capacity 
<< std::endl;
    
    return 0;
}

