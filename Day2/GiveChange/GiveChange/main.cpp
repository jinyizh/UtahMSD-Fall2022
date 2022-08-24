//
//  main.cpp
//  GiveChange
//
//  Created by Jinyi Zhou on 8/23/22.
//
// Test case:
// Enter item price in cents:
// 65
// Enter amount paid in cents:
// 100
// Change = 35 cents
// Quarters: 1
// Dimes: 1
// Nickels: 0
// Pennies: 0

#include <iostream>

int main(int argc, const char * argv[]) {
    
    int price, paid;
    int quarters = 0;
    int dimes = 0;
    int nickles = 0;
    int pennies = 0;
    
    std::cout << "Enter item price in cents:\n";
    std::cin >> price;
    std::cout << "Enter amount paid in cents:\n";
    std::cin >> paid;
    
    int change = paid - price;
    std::cout << "Change = " << change << " cents\n";
    
    int remainedChange = change;
    if (remainedChange / 25 > 0) {
        quarters = remainedChange / 25;
        remainedChange %= 25;
    }
    if (change / 10 > 0) {
        dimes = remainedChange / 10;
        remainedChange %= 10;
    }
    if (change / 5 > 0) {
        nickles = remainedChange / 5;
        remainedChange %= 5;
    }
    pennies = remainedChange;
    
    std::cout << "Quarters = " << quarters << std::endl;
    std::cout << "Dimes = " << dimes << std::endl;
    std::cout << "Nickles = " << nickles << std::endl;
    std::cout << "Pennies = " << pennies << std::endl;
    return 0;
}
