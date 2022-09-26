//
//  main.cpp
//  StringAnalyzer
//
//  Created by Jinyi Zhou on 8/26/22.
//

#include <iostream>
#include <string>
using namespace std;

bool IsTerminator(char c) {
    return (c == '.' || c == '?' || c == '!');
}

bool IsPunctuation(char c) {
    return (c == '.' || c == '?' || c == '!' || c == ',' ||
            c == ';' || c == '-');
}

bool IsVowel(char c) {
    return (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' ||
            c == 'Y' || c == 'a' || c == 'e' || c == 'i' || c == 'o' ||
            c == 'u' || c == 'y');
}

bool IsConsonant(char c) {
    return (!IsTerminator(c) && !IsPunctuation(c) && !IsVowel(c));
}

int NumWords(string s) {
    int wordCount = 1; // assume at least one none-space char was inputed
    for (int i = 0; i < s.length() - 1; i++) {
        if (s.at(i) == ' ' && s.at(i + 1) != ' ') {
            wordCount++;
        } // in case there are more than 1 space between words
    }
    return wordCount;
}

int NumSentences(string s) {
    int sentenceCount = 0;
    for (int i = 1; i < s.length(); i++) {
        if (IsTerminator(s.at(i))) {
            sentenceCount++;
        }
    }
    return sentenceCount;
}

int NumVowels(string s){
    int vowelCount = 0;
    for (int i = 0; i < s.length(); i++) {
        if (IsVowel(s.at(i))) {
            vowelCount++;
        }
    }
    return vowelCount;
}

int NumConsonants(string s) {
    int consonantCount = 0;
    for (int i = 0; i < s.length(); i++) {
        if (IsConsonant(s.at(i))) {
            consonantCount++;
        }
    }
    return consonantCount;
}

double AverageWordLength(string s) {
    return static_cast<double> (NumVowels(s) + NumConsonants(s)) / NumWords(s);
}

double AverageVowelsPerWord(string s) {
    return static_cast<double> (NumVowels(s)) / NumWords(s);
}

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
