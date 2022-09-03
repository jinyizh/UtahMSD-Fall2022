//
//  main.cpp
//  BookAnalyzer
//
//  Created by Jinyi Zhou on 9/1/22.
//

#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

string findTitle(vector<string> words) {
    string title;
    int start, end;
    for (int i = 0; i < words.size(); i++) {
        if (words[i] == "Title:") {
            start = i;
            break;
        }
    }
    for (int i = 0; i < words.size(); i++) {
        if (words[i] == "Author:") {
            end = i;
            break;
        }
    }
    for (int i = start + 1; i < end; i++) {
        title += words[i];
        title += " ";
    }
    return title;
}

string findAuthor(vector<string> words) {
    string title;
    int start, end;
    for (int i = 0; i < words.size(); i++) {
        if (words[i] == "Author:") {
            start = i;
            break;
        }
    }
    for (int i = 0; i < words.size(); i++) {
        if (words[i] == "Illustrator:") {
            end = i;
            break;
        }
    }
    for (int i = start + 1; i < end; i++) {
        title += words[i];
        title += " ";
    }
    return title;
}

string findReleaseDate(vector<string> words) {
    string title;
    int start, end;
    for (int i = 0; i < words.size(); i++) {
        if (words[i] == "Date:") {
            start = i;
            break;
        }
    }
    for (int i = 0; i < words.size(); i++) {
        if (words[i] == "[eBook") {
            end = i;
            break;
        }
    }
    for (int i = start + 1; i < end; i++) {
        title += words[i];
        title += " ";
    }
    return title;
}

int totalChar(vector<string> words) {
    int sum = 0;
    for (auto word : words) {
        sum += word.size();
    }
    return sum;
}

string shortestWord(vector<string> words) {
    string shortest = "a";
    for (auto word : words) {
        if (word.size() <= shortest.size()) {
            return word;
        }
    }
    return shortest;
}

string longestWord(vector<string> words) {
    string result;
    for (auto word : words) {
        if (word.size() >= result.size()) {
            result = word;
        }
    }
    return result;
}

int main(int argc, const char * argv[]) {
    string input;
    cout << "Enter a book you want to analyze: " << std::endl;
    cin >> input;
    ifstream fin(input + ".txt");
    if (fin.fail()) {
        cout << "Failed to find the book!" << endl;
        return 0;
    }
    
    string word;
    vector<string> allWords;
    while (fin >> word) {
        allWords.push_back(word);
    }
    
    string title = findTitle(allWords);
    string author = findAuthor(allWords);
    string releaseDate = findReleaseDate(allWords);
    
    int totalWords = allWords.size();
    int totalChars = totalChar(allWords);
    
    string shortest = shortestWord(allWords);
    
    cout << "title: " << title << endl;
    cout << "author: " << author << endl;
    cout << "release date: " << releaseDate << endl;
    cout << "total words: " << totalWords << endl;
    cout << "total characters: " << totalChars << endl;
    cout << "shortest word: " << shortest << endl;
    cout << "longest word: " << longestWord(allWords) << endl;
    
    return 0;
}
