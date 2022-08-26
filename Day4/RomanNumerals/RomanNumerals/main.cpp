//
//  main.cpp
//  RomanNumerals
//
//  Created by Jinyi Zhou on 8/25/22.
//  Boundary condition: number inputed is larger than 0.
//

#include <iostream>
#include <string>
#include <array>
using namespace std;

int main(int argc, const char * argv[]) {
    int num;
    string roman;
    int nums[13] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    string romans[13] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    cout << "Enter decimal number:\n";
    cin >> num;
    if (num <= 0) {
        cout << "Invalid input.\n";
        return 0;
    }
    for (int i = 0; i < 13; i++) {
        while (num - nums[i] >= 0) {
            roman += romans[i];
            num -= nums[i];
        }
    }
    cout << "Roman numeral version:" << "\n" << roman << endl;
    return 0;
}
