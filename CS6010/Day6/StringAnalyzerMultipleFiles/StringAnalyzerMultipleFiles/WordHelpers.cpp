//
//  WordHelpers.cpp
//  
//
//  Created by Jinyi Zhou on 8/29/22.
//

#include "WordHelpers.hpp"
#include "LetterHelpers.hpp"

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
