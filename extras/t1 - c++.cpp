#include <iostream>
#include <string>
#include <vector>

using namespace std;

string getString(string enunciado) {
    cout << enunciado << endl;
    
    string str;
    cin.ignore();
    getline (cin,str);
    return str;
}

vector<string> separa_em_vector(string str, char c) {
    vector<string> list;
    string aux2;
    bool aux = true;

    for (int i = 0; i <= str.length(); ++i) {

        if ((str[i] == c || i == str.length()) && aux){
            list.push_back(aux2);
            aux2.clear();
            aux = false;
        }

        if (str[i] == c && !aux) {
            aux = true;
        }
        else if(str[i] != c && aux) {
            aux2.push_back(str[i]);
        } 
    }

    return list;
}

void isVowel() {
    char c, aux[6] = {'a','e','i','o','u'};
    
    cout << "Digite uma caractere" << endl;
    cin >> c;

    for (int i = 0; i < 6; ++i) {
        if (c == aux[i]) {
            cout << "True" << endl;
            return;
        }
    }
    cout << "False" << endl;
}

void addComma() {
    string str = getString("Digite strings em sequencia, separando-as com espaco");

    if (str.length() != 0)
    {
        str.insert(str.end(), ',');
    }

    int i = 1;

    for (auto e : str)
    {
        if (str[i] == ' ' && str[i-1] != ' ') {
            str.insert(str.begin()+i, ',');
            i++;
        }
        i++;
    }
    cout << str << endl;
    return;
}

void htmlListItems() {
    string str = getString("Digite strings em sequencia, separando-as com ,");

    vector<string> list = separa_em_vector(str, ',');
    
    for (int i = 0; i < list.capacity(); ++i) {
        list[i].insert(0, "<LI>");
        list[i].append("</LI>");
    }

    for (auto e : list)
    {
        cout << e << ' ';
    }

    return;
}

void semVogais() {
    string str = getString("Digite uma string");

    int i = 0;

    while(i < str.length()) {
        if (str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u') {
            str.erase(str.begin()+i);
        }
        i++;
    }

    cout << str;

    return;
}

void codifica() {
    string str = getString("Digite una string");

    for (int i = 0; i <= str.length(); ++i)
    {
        if (str[i] != ' ')
            str[i] = '-';
    }

    cout << str;

    return;
}

string firstName(string nome) {
    vector<string> list = separa_em_vector(nome, ' ');

    return *list.begin();
}

void isInt() {
    string str;
    cout << "Digite uma string" << endl;

    cin.ignore();
    getline (cin,str);

    char* p;
    strtod(str.c_str(), &p);
    if (*p == 0) {
        cout << "true" << endl;
        return;
    }
    cout << "false" << endl;
    return;
}

string lastName(string nome) {
    vector<string> list = separa_em_vector(nome, ' ');

    return *(list.end()-1); 
}

void userName(string nome) {
    string firt = firstName(nome);
    string last = lastName(nome);

    cout << *firt.begin() << last << endl;

    return;
}

void encodeName(string str) {
    for (int i = 0; i <= str.length(); ++i)
    {
        switch(str[i]) {
            case 'a':
                str[i] = '4';
                break;
            case 'e':
                str[i] = '3';
                break;
            case 'i':
                str[i] = '2';
                break;
            case 'o':
                str[i] = '1';
                break;
            case 'u':
                str[i] = '0';
                break;
        }
    }
    cout << str << endl;
    return;
}

void betterEncodeName(string str) {
    for (int i = 0; i <= str.length(); ++i)
    {
        switch(str[i]) {
            case 'a':
                str[i] = '4';
                break;
            case 'e':
                str[i] = '3';
                break;
            case 'i':
                str[i] = '2';
                break;
            case 'o':
                str[i] = '1';
                break;
            case 'u':
                string::iterator it = str.begin();
                advance (it,i);
                int index = distance( str.begin(), it );
                str.insert(index, "00");
                i+=2;
                str.erase(str.begin()+i);
                break;
        }
    }
    cout << str << endl;
    return;
}

void tenCharacters(string str) {
    while (str.length() < 10) {
        str.push_back('.');
    }
    while (str.length() > 10) {
        str.pop_back();
    }

    cout << str << endl;

    return;

}

int main() 
{
    string nome[12] = {"IsVowel", "addComma", "htmlListItems", "semVogais", "codifica", 
        "firstName", "isInt", "lastName", "userName", "encodeName", "betterEncodeName", 
        "tenCharacters"};

    int numero = 0;

    cout << "Questoes do trabalho 1 de Haskell" << endl << endl;

    for (int i = 0; i < 12; ++i) {
        cout << i+1 << " -- " << nome[i] << endl;
    }

    cout << "DIGITE 15 PARA SAIR" << endl;

    while(numero != 15) {

        cout << endl;
        
        while((numero > 12 || numero < 1) && numero != 15) {
            cout << "Selecione a questao" << endl;
            cin >> numero;
        }

        switch (numero) {
            case 1:
                isVowel();
                numero = 0;
                break;
            case 2:
                addComma();
                numero = 0;
                break;
            case 3:
                htmlListItems();
                numero = 0;
                break;
            case 4:
                semVogais();
                numero = 0;
                break;
            case 5:
                codifica();
                numero = 0;
                break;
            case 6:
                cout << firstName(getString("Digite um nome")) << endl;
                numero = 0;
                break;
            case 7:
                isInt();
                numero = 0;
                break;
            case 8:
                cout << lastName(getString("Digite um nome")) << endl;
                numero = 0;
                break;
            case 9:
                userName(getString("Digite um nome"));
                numero = 0;
                break;
            case 10:
                encodeName(getString("Digite uma string"));
                numero = 0;
                break;
            case 11:
                betterEncodeName(getString("Digite uma string"));
                numero = 0;
                break;
            case 12:
                tenCharacters(getString("Digite uma string"));
                numero = 0;
                break;
        }
    }
}