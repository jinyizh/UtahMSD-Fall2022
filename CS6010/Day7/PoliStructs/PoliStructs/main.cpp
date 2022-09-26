//
//  main.cpp
//  PoliStructs
//
//  Created by Jinyi Zhou on 8/30/22.
//

#include <iostream>
#include <string>
#include <vector>
using namespace::std;

struct Politician {
    string name;
    string party;
    string stateOrFederal;
};

vector<Politician> Javacans(vector<Politician> politicians) {
    vector<Politician> javacanPoliticians;
    for (auto politician : politicians) {
        if (politician.party == "Javacans") {
            javacanPoliticians.push_back(politician);
        }
    }
    return javacanPoliticians;
}

vector<Politician> federalCplusers(vector<Politician> politicians) {
    vector<Politician> federalCpluserPoliticians;
    for (auto politician : politicians) {
        if (politician.party == "Cplusers" && politician.stateOrFederal == "federal") {
            federalCpluserPoliticians.push_back(politician);
        }
    }
    return federalCpluserPoliticians;
}

int main(int argc, const char * argv[]) {
    
    Politician poli1 {"Adam", "Cplusers", "state"};
    Politician poli2 {"Ben", "Javacans", "federal"};
    Politician poli3 {"Claire", "Javacans", "federal"};
    Politician poli4 {"David", "Cplusers", "federal"};
    Politician poli5 {"Eugene", "Javacans", "state"};
    Politician poli6 {"Frank", "Cplusers", "state"};
    Politician poli7 {"Gene", "Cplusers", "federal"};
    Politician poli8 {"Henry", "Javacans", "state"};
    Politician poli9 {"Irene", "Cplusers", "state"};
    Politician poli10 {"Jack", "Javacans", "federal"};
    
    vector<Politician> politicians = {poli1, poli2, poli3, poli4, poli5, poli6, poli7, poli8, poli9, poli10};
    
    vector<Politician> javacanPoliticians = Javacans(politicians);
    vector<Politician> federalCpluserPoliticians = federalCplusers(politicians);
    
    cout << "Javacans are: " << endl;
    for (auto politician : javacanPoliticians) {
        cout << politician.name << endl;
    }
    
    cout << "federal Cplusers are: " << endl;
    for (auto politician : federalCpluserPoliticians) {
        cout << politician.name << endl;
    }
    return 0;
}
