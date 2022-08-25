//
//  main.cpp
//  Palindromes
//
//  8/25/22.
//  Group member: Junming Jin, Jo Song, Jinyi Zhou, Juisheng Hung.
//  This part is done by Junming Jin.

#include <iostream>
using namespace std;

int main() {
    string Palindromes;
    cout << " Enter a word ";
    cin >> Palindromes;
    string revPalindromes;
    int i;
    for(i = Palindromes.length() - 1; i >= 0; i--)
    {
        revPalindromes +=Palindromes[i];
    }
    if (Palindromes == revPalindromes){
        cout<<"\n"<< Palindromes<<" IS a Palindromes "<<"\n";}
    else{
        cout <<"\n"<<Palindromes<<" Is not Palindromes "<<"\n";}
    return 0;
}
