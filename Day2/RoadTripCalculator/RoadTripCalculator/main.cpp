//
//  main.cpp
//  RoadTripCalculator
//
//  Created by Jinyi Zhou on 8/23/22.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    int distance;
    float mpg;
    float costOfGas;
    
    std::cout << "Enter the distance in whole miles:\n";
    std::cin >> distance;
    std::cout << "Enter the mpg efficiency:\n";
    std::cin >> mpg;
    std::cout << "Enter the cost of gas in dollars per gallon:\n";
    std::cin >> costOfGas;
    float costOfTrip = distance * costOfGas / mpg;
    std::cout << "The cost of trip is " << costOfTrip << " dollars" << std::endl;
    
    return 0;
}
