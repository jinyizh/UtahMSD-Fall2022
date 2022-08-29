//
//  main.cpp
//  StringAnalyzer
//
//  Created by Jinyi Zhou on 8/29/22.
//

#include <iostream>
#include <string>
#include "LetterHelpers.hpp"
#include "WordHelpers.hpp"
using namespace std;

int main(int argc, const char * argv[]) {
    string s;
    do {
        cout << "Enter a string containing one or more sentences, or enter 'done' to leave:\n";
        getline(cin, s);
        if (s == "done") {
            cout << "Goodbye!" << endl;
            return 0;
        }
        cout << "Analysis:\n";
        cout << "  Number of words: " << NumWords(s) << endl;
        cout << "  Number of sentences: " << NumSentences(s) << endl;
        cout << "  Number of vowels: " << NumVowels(s) << endl;
        cout << "  Number of consonants: " << NumConsonants(s) << endl;
        cout << "  Reading level (average word length): " << AverageWordLength(s) << endl;
        cout << "  Average vowels per word: " << AverageVowelsPerWord(s) << endl;
    } while (s != "done");
    
    return 0;
}
