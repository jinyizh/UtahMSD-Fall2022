//
//  main.cpp
//  StatusCheck Week1
//
//  Content:
//  Variable creation and value assignment
//  Loops
//  If statements
//  Input / Output
//
//  Created by Jinyi Zhou on 8/29/22.
//

#include <iostream>
using namespace std;

int main(int argc, const char * argv[]) {
    int result = 0, cache;
    cout << "Add 4 numbers. The numbers should be from 1 to 10. \n";
    for (int i = 0; i <= 3; i++) {
        cout << "Enter the number, or enter -99 to quit.: " << endl;
        cin >> cache;
        if (cache >= 1 && cache <= 10) {
            result += cache;
        } else if (cache == -99) {
            cout << "Goodbye!" << endl;
            return 0;
        } else {
            cout << "This one is an invalid input!" << endl;
        }
    }
    cout << "The total sum of the valid numbers is " << result << endl;
    return 0;
}
