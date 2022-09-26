//
//  LetterHelpers.cpp
//  
//
//  Created by Jinyi Zhou on 8/29/22.
//

#include "LetterHelpers.hpp"
#include "WordHelpers.hpp"

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
