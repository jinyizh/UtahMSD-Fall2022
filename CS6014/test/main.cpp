#include <iostream>
#include "Key.h"
#include "rc4.h"

std::string rc4Encrypt(std::string message, std::string password){

    uint8_t m[message.size()];
    for (int i = 0; i < message.size(); i++)
        m[i] = message[i];

    std::string out;
    rc4 r = rc4(password);
    for(int i = 0; i < message.size(); i++){
        out += r.genNextByte() ^ m[i];
    }
    return out;
}


int main() {

    std::string rc4Enc = rc4Encrypt("My salary is $1000", "password");
    std::cout << rc4Enc << "\n";

    std::string bitFlipped;
    for(int i = rc4Enc.size() - 4; i < rc4Enc.size(); i++){
        if(i == rc4Enc.size() - 4){
            bitFlipped += rc4Enc[i] xor 0b1000;
        }
        else {
            bitFlipped += rc4Enc[i] xor 0b1001;
        }
    }
    std::cout << "bitFlipped: "+ bitFlipped << "\n";
    bitFlipped = rc4Enc.substr(0, rc4Enc.size()-4) + bitFlipped;


    std::string rc4DecBitFlipped = rc4Encrypt(bitFlipped, "password");
    std::cout << rc4DecBitFlipped << "\n";

    std::string rc4Dec = rc4Encrypt(rc4Enc, "password");
    std::cout << rc4Dec << "\n";

    //Verify that decrypting a message with a different key than the encryption key does not reveal the plaintext.
    std::string rc4DecDiff = rc4Encrypt(rc4Enc, "passworl");
    std::cout << rc4DecDiff << "\n";

    //Verify that encrypting 2 messages using the same keystream is insecure (what do you expect to see if you xor the two encrypted messages?)
    std::string rc4_1 = rc4Encrypt("test1", "password");
    std::string rc4_2 = rc4Encrypt("test2", "password");

    std::string out;
    for(int i = 0; i < rc4_1.size(); i++){
        out += rc4_1[i] xor rc4_2[i];
    }
    std::cout <<  out + "\n";

    return 0;
}