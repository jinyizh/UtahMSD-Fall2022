//
//  main.cpp
//  Practice
//
//  Created by Jinyi Zhou on 9/1/22.
//

#include <iostream>
#include <string>
#include <vector>

struct House {
    int numberOfBathrooms;
    int numberOfBedrooms;
    std::string color;
    int zipCode;
};

int main(int argc, const char * argv[]) {
    House house1 = {2, 2, "red", 84101};
    House house2;
    house2.numberOfBathrooms = 3;
    house2.numberOfBedrooms = 3;
    house2.color = "green";
    house2.zipCode = 94101;
    int totalRooms = house1.numberOfBedrooms + house1.numberOfBathrooms;
    
    std::cout << totalRooms << std::endl;
    
    return 0;
}
