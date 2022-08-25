//
//  main.cpp
//  ForLoopPractice
//
//  Created by Jinyi Zhou on 8/24/22. uID: u1424752
//

#include <iostream>

int main(int argc, const char * argv[]) {
    // print numbers from 1 to 10
    std::cout << "print numbers from 1 to 10, using for loop:\n";
    for (int i = 1; i <= 10; i++) {
        std::cout << i << "\n";
    }
    
    std::cout << "print numbers from 1 to 10, using while loop:\n";
    int i = 1;
    while (i <= 10) {
        std::cout << i << "\n";
        i++;
    }
    
    // comment: using a for loop is more approriate since there is only one line of code inside the block, while there are two lines in the while loop block.
    
    // print all numbers between the two numbers entered:
    int num1, num2;
    std::cout << "Enter two integer numbers\n";
    std::cin >> num1 >> num2;
    if (num1 <= num2) {
        std::cout << "print all the numbers from the smaller one to the larger one:\n";
        for (num1; num1 <= num2; num1++) {
            std::cout << num1 << "\n";
        }
    } else {
        std::cout << "print all the numbers from the larger one to the smaller one:\n";
        for (num1; num1 >= num2; num1--) {
            std::cout << num1 << "\n";
        }
    }
    
    // Print all the odd numbers between 1 and 20 using two methods:
    // 1, uses if statement and a loop:
    std::cout << "Print all the odd numbers between 1 and 20, method 1:\n";
    for (int i = 1; i <= 20; i++) {
        if (!(i % 2 == 0)) {
            std::cout << i << "\n";
        }
    }
    
    // 2, do not use if statement:
    std::cout << "Print all the odd numbers between 1 and 20, method 2:\n";
    for (int i = 1; i <= 20; i += 2) {
        std::cout << i << "\n";
    }
    
    // comment: I think the one that does not use if statement is better, because it does not need to check every interger from 1 to 20 if it is odd or not. Instead it just add the previous number by 2.
    
    // Adding all positive numbers inputed (until the user inputs a negative number):
    double sum = 0;
    double temporaryNumber;
    std::cout << "keep inputing positive numbers: \n";
    do {
        std::cin >> temporaryNumber;
        sum += temporaryNumber;
    } while (temporaryNumber >= 0);
    std::cout << "The sum of all the positive numbers inputed is: " << sum - temporaryNumber << "\n";
    
    // Print a multiplication table:
    std::cout << "multiplication table: \n";
    for (int i = 1; i <= 5; i++) {
        std::cout << i << "x*: ";
        for (int j = 1; j <= 5; j++) {
            std::cout << i * j << " ";
            if (j == 5) {
                std::cout << "\n";
            }
        }
    } // i stands for the # of rows, while j stands for the # of columns
    
    return 0;
}
