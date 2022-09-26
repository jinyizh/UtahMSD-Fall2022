//
//  main.cpp
//  IfStatementPractice
//
//  Created by Jinyi Zhou on 8/24/22. uID: u1424752
//  I did this lab assignment on my own, so I did both parts.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    
    // Start part 1
    
    int age;
    do {
        std::cout << "Part one, enter your age:" << "\n";
        std::cin >> age;
    } while (age <= 0);
    if (age >= 18) {
        std::cout << "You are old enough to vote.\n";
    }
    if (age >= 30) {
        std::cout << "You are also old enough to run for senate.\n";
    }
    if (age < 18) {
        std::cout << "You are not old enough to vote, nor to run for senate.\n";
    }
    
    if (age > 80) {
        std::cout << "You are part of the greatest generation.\n";
    } else if (age > 60 && age <= 80) {
        std::cout << "You are part of the baby boomers.\n";
    } else if (age > 40 && age <= 60) {
        std::cout << "You are in generation X.\n";
    } else if (age > 20 && age <= 40) {
        std::cout << "You are a millennial.\n";
    } else {
        std::cout << "You are an iKid.\n";
    }
    
    // Start part 2
    
    bool isWeekday, isHoliday, haveChildren;
    char weekdayResponse;
    char holidayResponse = 'N';
    char childrenResponse;
    std::cout << "Part two, gets to sleep in today or not?\n";
    std::cout << "Is it a weekday today? Enter Y for yes or N for no.\n";
    do {
        std::cin >> weekdayResponse;
    } while (! (weekdayResponse == 'Y' || weekdayResponse == 'N'));
    
    if (weekdayResponse == 'N') {
        std::cout << "Is it a holiday (not including weekend) today? Enter Y for yes or N for no.\n";
        do {
            std::cin >> holidayResponse;
        } while (! (holidayResponse == 'Y' || holidayResponse == 'N'));    }
    
    std::cout << "Do you have young children? Enter Y for yes or N for no.\n";
    do {
        std::cin >> childrenResponse;
    } while (! (childrenResponse == 'Y' || childrenResponse == 'N'));
    
    if (weekdayResponse == 'Y') {
        isWeekday = true;
    } else {
        isWeekday = false;
    }
    
    if (holidayResponse == 'Y') {
        isHoliday = true;
    } else {
        isHoliday = false;
    }
    
    if (childrenResponse == 'Y') {
        haveChildren = true;
    } else {
        haveChildren = false;
    }
    
    if (!haveChildren && (isHoliday || !isWeekday)) {
        std::cout << "You can get to sleep in today!\n";
    } else {
        std::cout << "You CAN'T get to sleep in today!\n";
    }
    
    return 0;
}
