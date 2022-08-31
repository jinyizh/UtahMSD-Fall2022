//
//  main.cpp
//  Cards
//
//  Created by Jinyi Zhou on 8/30/22.
//

#include <iostream>
#include <string>
#include <vector>
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
    Card joker1 = {'J', 0};
    Card joker2 = {'J', 1};
    deck.push_back(joker1);
    deck.push_back(joker2);
    return deck;
}

void printCard(Card card) {
    vector<char> jqk = {'J', 'Q', 'K'};
    if (card.rank > 10) {
        cout << card.suit << jqk[card.rank - 11] << endl;
    } else if (card.rank == 1 && card.suit != 'J') {
        cout << card.suit << 'A' << endl;
    } else {
        cout << card.suit << card.rank << endl;
    }
}

int main(int argc, const char * argv[]) {
    vector<Card> deck = createDeck();
    for (int i = 0; i < deck.size(); i++) {
        printCard(deck[i]);
    }
    return 0;
}
