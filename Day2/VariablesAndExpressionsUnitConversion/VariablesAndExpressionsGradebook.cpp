// Part 2 of the Variables and Expressions Group Lab, done by JAEHYUNG PARK

#include <iostream>

int main() {

int first_assignment;
int second_assignment;
int third_assignment;
int fourth_assignment;
int fifth_assignment;

std::cout << "Your first_assignment score is: ";
std::cin >> first_assignment;
std::cout << "Your second_assignment score is: ";
std::cin >> second_assignment;
std::cout << "Your third_assignment score is: ";
std::cin >> third_assignment;
std::cout << "Your fourth_assignment score is: ";
std::cin >> fourth_assignment;
std::cout << "Your fifth_assignment score is: ";
std::cin >> fifth_assignment;


float average = (float)(first_assignment + second_assignment + 
third_assignment + fourth_assignment + fifth_assignment) / 5;
std::cout << "Your all 5 assignments score average is " << average << 
"\n";

return 0;
}
