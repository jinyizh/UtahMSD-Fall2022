//
//  main.cpp
//  Cards
//
//  Created by Jinyi Zhou on 8/30/22.
//
//  Based on Jinyi Zhou's code for "Deck of Cards".
//  Group member: Junming Jin, Jaehyung Park, Jinyi Zhou.
//
//  Percentage of each type of hands:
//  Flush: 0.1965%
//  Straight: 0.3925%
//  Straight Flush: 0.0014%
//  Rotal Flush: 0.00154%
//  Full House: 0.1441%

#include <iostream>
#include <string>
#include <vector>
#include <cstdlib>
#include <time.h>
using namespace std;

struct Card {
    char suit; // H: heart, S: spade, C: club, D: diamond, J: joker.
    int rank; // A, 2 - 10, J, Q, K. Jokers have two ranks, 0 and 1 because there are only two of them.
};

vector<Card> createDeck() {
    vector<Card> deck;
    vector<char> suit = {'H', 'S', 'C', 'D'};
// Push 52 cards:
    for (int i = 0; i <= 3; i++) {
        for (int j = 1; j <= 13; j++) {
            struct Card card {suit[i], j};
            deck.push_back(card);
        }
    }
// Push jokers:
//    Card joker1 = {'J', 0};
//    Card joker2 = {'J', 1};
//    deck.push_back(joker1);
//    deck.push_back(joker2);
    return deck;
}

void printCard(Card card) {
    vector<char> jqk = {'J', 'Q', 'K'};
    if (card.rank > 10) {
        cout << card.suit << jqk[card.rank - 11] << endl;
    } else if (card.rank == 1 /* && card.suit != 'J' */) {
        cout << card.suit << 'A' << endl;
    } else {
        cout << card.suit << card.rank << endl;
    }
}

void shuffle(vector<Card>& deck) {
    srand(time(0));
    struct Card tempCard;
    for (int i = 0; i < 52; i++) {
        int num = rand() % 52;
        tempCard = deck[i];
        deck[i] = deck[num];
        deck[num] = tempCard;
    }
}

bool isFlush(vector<Card> deck) {
    struct Card card1 = deck[0];
    struct Card card2 = deck[1];
    struct Card card3 = deck[2];
    struct Card card4 = deck[3];
    struct Card card5 = deck[4];
    return (card1.suit == card2.suit && card2.suit == card3.suit &&
            card3.suit == card4.suit && card5.suit);
}

void sort(int array[], int size)
 {
   int temp;
   for (int i = 0; i < size - 1; i++)
   {
     for (int j = 0; j < size - i - 1; j++)
     {
       if (array[j] > array[j + 1])
       {
         temp = array[j];
         array[j] = array[j + 1];
         array[j + 1] = temp;
       }
     }
   }
 }
bool isStraight(vector<Card> deck){
    struct Card card1 = deck[0];
    struct Card card2 = deck[1];
    struct Card card3 = deck[2];
    struct Card card4 = deck[3];
    struct Card card5 = deck[4];
    int array[] = {card1.rank, card2.rank, card3.rank, card4.rank, card5.rank};
    int size = sizeof(array) / sizeof(array[0]);
    sort(array, size);
    
    if (card5.rank == 13 && card4.rank == 12 && card3.rank == 11 &&
       card2.rank == 10 && card1.rank == 1) {
        return true;
    }

    return(card5.rank-card4.rank==1 && card4.rank-card3.rank==1 && card3.rank-card2.rank==1 && card2.rank-card1.rank==1);
}

bool isStraightFlush(vector<Card> deck) {
    return (isFlush(deck) && (isStraight(deck)));
}

bool isRoyalFlush(vector<Card> deck){
    struct Card card1 = deck[0];
    return (isFlush(deck)==true && card1.rank==10);
}

bool isFullHouse(vector<Card> deck) {
    struct Card card1 = deck[0];
    struct Card card2 = deck[1];
    struct Card card3 = deck[2];
    struct Card card4 = deck[3];
    struct Card card5 = deck[4];
    int array[] = {card1.rank, card2.rank, card3.rank, card4.rank, card5.rank};
    int size = sizeof(array) / sizeof(array[0]);
    sort(array, size);
    
    if (deck[0].rank != deck[1].rank || deck[3].rank != deck[4].rank) {
        return false;
    }
    
    return (deck[2].rank == deck[1].rank || deck[2].rank == deck[3].rank);
    
}

int main(int argc, const char * argv[]) {
    srand(time(0));
    vector<Card> deck = createDeck();
    shuffle(deck);
    for (int i = 0; i < deck.size(); i++) {
        printCard(deck[i]);
    }
    
    if (isFlush(deck)) {
        cout << "is Flush!" << endl;
    } else {
        cout << "is not Flush!" << endl;
    }
    
    if (isStraight(deck)) {
        cout << "is Straight!" << endl;
    } else {
        cout << "is not Straight!" << endl;
    }
    
    if (isStraightFlush(deck)) {
        cout << "is Straight Flush!" << endl;
    } else {
        cout << "is not Straight Flush!" << endl;
    }
    
    if (isRoyalFlush(deck)) {
        cout << "is Royal Flush!" << endl;
    } else {
        cout << "is not Royal Flush!" << endl;
    }
    
    if (isFullHouse(deck)) {
        cout << "is Full House!" << endl;
    } else {
        cout << "it not Full House!" << endl;
    }
    
    return 0;
}
