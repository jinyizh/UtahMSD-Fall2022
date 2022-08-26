//
//  main.cpp
//  FunctionPractice
//
//  Created by Jinyi Zhou on 8/26/22.
//

#include <iostream>
#include <string>
#include <cmath>
#include <ctime>

float LengthOfHypotenuse(float lengthA, float lengthB) {
    return sqrt(lengthA * lengthA + lengthB * lengthB);
}

void XYVelocity(double speed, double angle) {
    std::cout << "X velocity is " << speed * cos(angle) << "\n";
    std::cout << "Y velocity is " << speed * sin(angle) << "\n";
}

bool isCapitalized(std::string string) {
    char a = string.front();
    if (a == 'A' || a == 'B' || a == 'C' || a == 'D' ||
        a == 'E' || a == 'F' || a == 'G' || a == 'H' ||
        a == 'I' || a == 'J' || a == 'K' || a == 'L' ||
        a == 'M' || a == 'N' || a == 'O' || a == 'P' ||
        a == 'Q' || a == 'R' || a == 'S' || a == 'T' ||
        a == 'U' || a == 'V' || a == 'W' || a == 'X' ||
        a == 'Y' || a == 'Z') {
        return true;
    }
    return false;
}

std::string boolToString(bool boolValue) {
    if(boolValue) {
        return "true";
    } else {
        return "false";
    }
}

bool isPrime(int input) {
    if (input == 2) return true;
    for (int i = 2; i < input; i++) {
        while (input % i == 0) {
            return false;
        }
    }
    return true;
}

int main(int argc, const char * argv[]) {

//   a) print the length of the hypotenuse
    float lengthA;
    float lengthB;
    std::cout << "Compute the length of the hypotenuse of a triangle.\n";
    std::cout << "Enter the first right-angle side length:\n";
    std::cin >> lengthA;
    std::cout << "Enter the second right-angle side length:\n";
    std::cin >> lengthB;
    float hypotenuse = sqrt(lengthA * lengthA + lengthB * lengthB);
    std::cout << "The length of the hypotenuse of this triangle is " << hypotenuse << std::endl;
    
//  b) print out their x and y velocity
    double speed;
    double angle;
    std::cout << "Print out the velocity in x or y direction\n";
    std::cout << "Enter the speed: \n";
    std::cin >> speed;
    std::cout << "Enter the angle between the X axis in radians\n";
    std::cin >> angle;
    std::cout << "X velocity is " << speed * cos(angle) << "\n";
    std::cout << "Y velocity is " << speed * sin(angle) << "\n";
    
//   c)
    std::time_t result = std::time(nullptr);
    std::cout << std::asctime(std::localtime(&result))
              << result << " seconds since the Epoch\n";
    
//   The functions being called here are:
//   1. time()
//   2. asctime()
//   3. localtime()
    
//   Part 2: Write Your Own Functions:

//   The function that computes performs the hypotenuse task can read fixed values (instead of reading from user input). Refer to the LengthOfHypotenuse function written above. Call the function below:
    
    float length1 = 3;
    float length2 = 4;
    float length3 = LengthOfHypotenuse(length1, length2);
    std::cout << "the length of hypertenuse of a triangle with right angle lengths of 3 and 4 is " << length3 << "\n";
    
//   This function takes two parameters, that are the two right angle lengths. The reason for not getting the input form std::cin is that the funtion manipulates the variables. We can assign variables outside the function.
    
//   It is difficult to turn the speed/velocity task above into a function because it requires the funtion to return two values, the X velocity and the Y velocity, instead of just one value. However, since the X velocity and Y velocity depends on each other, there is a way to wrap them into one function. Refer to the XYVelocity function defined above.
    
//   Test the isCapitalized function:
    std::string string;
    std::cout << "Enter a string\n";
    std::cin >> string;
    if (isCapitalized(string)) {
        std::cout << "This string is capitalized!\n";
    } else {
        std::cout << "This string is not capitalized!\n";
    }
    
//   The boolToString function is defined above. Test:
    bool boolValue = isCapitalized(string);
    std::cout << "The result is " << boolToString(boolValue) << "\n";
    
//   Test the isPrime function defined above
    int input;
    std::cout << "Type an interger that is larger than 1:\n";
    std::cin >> input;
    if (isPrime(input)) {
        std::cout << "This is a prime number!\n";
    } else {
        std::cout << "This is not a prime number!\n";
    }
    
    return 0;
}
