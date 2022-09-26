//
//  main.cpp
//  VectorPractice
//
//  Created by Jinyi Zhou on 8/29/22.
//

#include <iostream>
#include <string>
#include <vector>
#include <cassert>
using namespace std;

int sum(vector<int> ints) {
    int sum = 0;
    for (int i = 0; i < ints.size(); i++) {
        sum += ints[i];
    }
    return sum;
}

vector<char> stringToVec(string s) {
    vector<char> chars;
    for (int i = 0; i < s.size(); i++) {
        chars.push_back(s.at(i));
    }
    return chars;
}

vector<int> reverse(vector<int> ints) {
    int temp;
    int i = 0;
    int j = ints.size() - 1;
    for (; i < j; i++, j--) {
        temp = ints[i];
        ints[i] = ints[j];
        ints[j] = temp;
    }
    return ints;
}

int main(int argc, const char * argv[]) {

//  Testcase:
    vector<int> nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int result = sum(nums);
    std::cout << result << endl;
    assert(result == 55);
    
//  Testcase:
    string s = "Practice";
    vector<char> chars = stringToVec(s);
    for (int i = 0; i < chars.size(); i++) {
        cout << s.at(i) << " ";
    }
    cout << endl;
    assert(chars[0] == 'P');
    assert(chars[1] == 'r');
    assert(chars[2] == 'a');
    assert(chars[3] == 'c');
    assert(chars[4] == 't');
    assert(chars[5] == 'i');
    assert(chars[6] == 'c');
    assert(chars[7] == 'e');
    
//  Testcase:
    vector<int> ints = {1, 2, 3, 4, 5, 6};
    vector<int> reversedInts = reverse(ints);
    for (int i = 0; i < reversedInts.size(); i++) {
        cout << reversedInts[i] << " ";
    }
    cout << endl;
    assert(reversedInts[0] == 6);
    assert(reversedInts[1] == 5);
    assert(reversedInts[2] == 4);
    assert(reversedInts[3] == 3);
    assert(reversedInts[4] == 2);
    assert(reversedInts[5] == 1);
    
    return 0;
}
