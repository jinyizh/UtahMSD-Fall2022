//
//  main.cpp
//  NumberConverter
//
//  Created by Jinyi Zhou on 9/6/22.
//

#include <iostream>
#include <string>
#include <cmath>
using namespace std;

int stringToInt(string s, int base) {
    int deciValue = 0;
    for (int i = s.size() - 1; i >= 0; i--) {
        if (s.at(i) == 'a' || s.at(i) == 'b' || s.at(i) == 'c' ||
            s.at(i) == 'd' || s.at(i) == 'e' || s.at(i) == 'f' ||
            s.at(i) == 'A' || s.at(i) == 'B' || s.at(i) == 'C' ||
            s.at(i) == 'D' || s.at(i) == 'E' || s.at(i) == 'F') {
            deciValue += (s.at(i) - 'a' + 10) * pow(16, s.size() - 1 - i);
        } else if (s.at(i) == '-') {
            deciValue *= -1;
        } else {
            deciValue += (s.at(i) - '0') * pow(base, s.size() - 1 - i);
        }
    }
    return deciValue;
}

string intToDecimalString(int num) {
    char character;
    string s;
    int digit;
    if (num < 0) {
        num = -num;
        while (num != 0) {
            digit = num % 10;
            num -= digit;
            num /= 10;
            character = digit + '0';
            s.push_back(character);
        }
        s.push_back('-');
        reverse(s.begin(), s.end());
        return s;
    }
    while (num != 0) {
        digit = num % 10;
        num -= digit;
        num /= 10;
        character = digit + '0';
        s.push_back(character);
    }
    reverse(s.begin(), s.end());
    return s;
}

string intToBinaryString(int num) {
    char character;
    string s;
    int digit;
    if (num < 0) {
        num = -num;
        while (num != 0) {
            digit = num % 2;
            num -= digit;
            num /= 2;
            character = digit + '0';
            s.push_back(character);
        }
        s.push_back('-');
        reverse(s.begin(), s.end());
        return s;
    }
    while (num != 0) {
        digit = num % 2;
        num -= digit;
        num /= 2;
        character = digit + '0';
        s.push_back(character);
    }
    reverse(s.begin(), s.end());
    return s;
}

string intToHexadecimalString(int num) {
    char character;
    string s;
    int digit;
    if (num < 0) {
        num = -num;
        while (num != 0) {
            digit = num % 16;
            num -= digit;
            num /= 16;
            if (digit < 10) {
                character = digit + '0';
            } else {
                character = digit + 'a' - 10;
            }
            s.push_back(character);
        }
        s.push_back('-');
        reverse(s.begin(), s.end());
        return s;
    }
    while (num != 0) {
        digit = num % 16;
        num -= digit;
        num /= 16;
        if (digit < 10) {
            character = digit + '0';
        } else {
            character = digit + 'a' - 10;
        }
        s.push_back(character);
    }
    reverse(s.begin(), s.end());
    return s;
}

int main(int argc, const char * argv[]) {
    int result = stringToInt("-9229", 10);
    if (result != -9229) {
        cout << "test 1 failed!" << endl;
        return 0;
    }
    string result10 = intToDecimalString(-321);
    if (result10 != "-321") {
        cout << "test 2 failed!" << endl;
        return 0;
    }
    string result2 = intToBinaryString(-1200);
    if (result2 != "-10010110000") {
        cout << "test 3 failed!" << endl;
        return 0;
    }
    string result16 = intToHexadecimalString(-23232);
    if (result16 != "-5ac0") {
        cout << "test 4 failed!" << endl;
        return 0;
    }
    cout << "All test past!" << endl;
    return 0;
}
