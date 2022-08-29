//
//  WordHelpers.hpp
//  
//
//  Created by Jinyi Zhou on 8/29/22.
//

#ifndef WordHelpers_hpp
#define WordHelpers_hpp

#include <stdio.h>
#include <string>
using namespace std;

int NumWords(string s);

int NumSentences(string s);

int NumVowels(string s);

int NumConsonants(string s);

double AverageWordLength(string s);

double AverageVowelsPerWord(string s);

#endif /* WordHelpers_hpp */
