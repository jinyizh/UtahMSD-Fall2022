#include <iostream>

extern "C" {
    void sayHello();
    void myPuts(const char* s, int len);
}

int main() {
    std::cout << "test sayHello:" << std::endl;
    sayHello();
    const char* s = "hello again";
    std::cout << "test myPuts:" << std::endl;
    myPuts(s, 11);
    std::cout << "\n";
    return 0;
}
