//
//  main.cpp
//  MagicDates
//
//  Created by Jinyi Zhou on 8/25/22.
//  Group member: Junming Jin, Jo Song, Jinyi Zhou, Juisheng Hung.
//  This part is done by Jinyi Zhou, which is partly based on the code of Part 2.
//  Therefore, part of this is copied from Part 2's code, and made some changes too.
//

#include <iostream>

int main(int argc, const char * argv[]) {
    std::string date;
    std::string day_year;
    std::string month; // position: 0-1
    std::string day; // position: 3-4
    std::string year; // position: 6-9
    std::string s_month;
    // ask the user to enter a date
    std::cout << "Enter a date (mm/dd/yyyy):\n";
    std::cin >> date;
    // find month
    for (int i = 0; i < date.find("/"); i++){
        month += date.substr(i,1);
    }
    // find day
    day_year = date.substr((date.find("/")+1),7); // or (date.rfind(‘/’)+1)
    for (int x = 0; x < day_year.find("/"); x++) {
        day += day_year.substr(x,1);
    }
    // find year
    year = day_year.substr((day_year.find("/")+1),4);
    // convert date string to integers
    int i_month = std::stoi(month);
    int i_day = std::stoi(day);
    int i_year = std::stoi(year);
    int last_two_digits = i_year % 100;
    // determine invalid conditions
    if (i_month < 1 or i_month >12 or i_day < 1 or i_day > 31 or i_year < 1000 or i_year >9999) {
        std::cout << "Invalid date.\n";
    }
    // print out valid results
    else if (i_month * i_day == last_two_digits){
        std::cout << date << " IS a magic date" << std::endl;
    } else {
        std::cout << date << " is NOT a magic date" << std::endl;
    }
    return 0;
}
