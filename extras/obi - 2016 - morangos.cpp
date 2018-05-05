#include <iostream>
using namespace std;


int main(){ 
    int comprimento1 = 0, comprimento2 = 0;
    int largura1 = 0, largura2 = 0;

    while(comprimento1 < 1 || comprimento1 > 100 || largura1 < 1 || largura1 > 100 || 
            comprimento2 < 1 || comprimento2 > 100 || largura2 < 1 || largura2 > 100) {
        cin >> comprimento1 >> largura1 >> comprimento2 >> largura2;
    }

    int um = comprimento1*largura1, dois = comprimento2*largura2;

    if (um > dois)
        cout << um << endl;
    else
        cout << dois << endl;

    return 0;
}