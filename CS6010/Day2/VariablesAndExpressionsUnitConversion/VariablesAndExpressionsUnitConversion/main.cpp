//
//  main.cpp
//  VariablesAndExpressionsUnitConversion
//  This is the first part of the group lab "Variables and Expressions"
//  Created by Jinyi Zhou on 8/23/22. uID: u1424752
//
//  Unit    '1 Ounce = x of these'
//  Ounces    1
//  Cups    1/8
//  Pints    1/16
//  Gallons    1/128
//  Liters    0.0296
//  Cubic Inches    1.8

#include <iostream>

int main(int argc, const char * argv[]) {
    float ounce;
    
    std::cout << "Enter volume in ounces: \n";
    std::cin >> ounce;
    std::cout << "Ounces: " << ounce << std::endl;
    
    float cups = ounce / 8;
    float pints = ounce /16;
    float gallons = ounce / 128;
    float liters = ounce * 0.0296;
    float cubicInches = ounce * 1.8;
    
    std::cout << "Cups: " << cups << "\n";
    std::cout << "Pints: " << pints << "\n";
    std::cout << "Gallons: " << gallons << "\n";
    std::cout << "Liters: " << liters << "\n";
    std::cout << "Cubic Inches: " << cubicInches << "\n";
    
    return 0;
}
