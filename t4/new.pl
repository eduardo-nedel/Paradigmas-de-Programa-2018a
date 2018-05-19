/*               Pipas
E um sábado de muito vento, e seis pipas est˜ao sendo empinadas no parque. Cada pipa ´e de uma ´
das seguintes cores: vermelha, verde, azul ou amarela. H´a pelo menos uma pipa de cada cor, e n˜ao
h´a mais do que duas pipas de uma mesma cor. Cada pipa est´a voando a uma altitude diferente das
outras.
• A mesma pipa vermelha, que est´a mais alta do que uma pipa azul, est´a mais baixa do que uma
pipa verde.
• Uma pipa azul est´a mais alta do que uma pipa verde.
• A pipa mais alta náo é azul e a pipa mais baixa não é azul.
• Não há uma pipa azul imediatamente mais alta ou imediatamente mais baixa do que uma pipa
verde.
*/

ultimo(X,[X]).
ultimo(X,[_|Z]) :- ultimo(X,Z).

/* Mais alto = fim da lista */
regra1(PIPAS) :-
    PIPAS = [_,_,_,_,_,_].

regra2(PIPAS) :-
    nth0(Pos1, PIPAS, r),
    nth0(Pos2, PIPAS, b),
    nth0(Pos3, PIPAS, g),
    Pos1 > Pos2,
    Pos3 > Pos1.

regra3(PIPAS) :-
    nth0(Pos1, PIPAS, b),
    nth0(Pos2, PIPAS, g),
    Pos1 > Pos2.

regra4(PIPAS) :-
    PIPAS = [H|_],
    [H] =\= [b],
    ultimo(X, PIPAS),
    [b] =\= [X].

regra5(PIPAS) :-
    not(nextto(b, g, PIPAS)),
    not(nextto(g, b, PIPAS)).

pipas(PIPAS) :-
    regra1(PIPAS),
    regra2(PIPAS),
    regra3(PIPAS),
    regra4(PIPAS),
    regra5(PIPAS).



/*
 Quest˜ao 14. Qual das seguintes ´e uma lista correta
e completa das pipas no parque, da mais alta
para a mais baixa?

(A) amarela, verde, vermelha, azul, vermelha,
verde
--> pipas([g,r,b,r,g,y]). CORRETA

(B) vermelha, verde, amarela, azul, amarela,
verde
pipas([g,y,b,y,g,r]).

(C) amarela, verde, vermelha, azul, vermelha,
amarela
pipas([y,r,b,r,g,y]).

(D) azul, amarela, verde, vermelha, azul, amarela
pipas([y,b,r,g,y,b]).

(E) vermelha, verde, vermelha, azul, verde,
amarela
pipas([y,g,b,r,g,r]).
*/

/*
Quest˜ao 17. Se pipas amarelas est˜ao imediatamente
abaixo da pipa mais alta e acima da pipa
mais baixa, ent˜ao qual das alternativas seguintes
´e necessariamente falsa?

(A) A pipa mais alta ´e verde.
pipas([_,y,_,_,y,g]).

(B) H´a exatamente uma pipa azul.
pipas([_,y,b,_,y,_]).


(C) H´a exatamente duas pipas vermelhas.
--> CORRETA
?- pipas([r,r,_,y,y,_]).
false.

?- pipas([r,_,r,y,y,_]).
false.

?- pipas([r,_,_,y,y,r]).
false.

?- pipas([r,_,y,r,y,_]).
false.

?- pipas([r,y,_,r,y,_]).
false.

?- pipas([y,r,_,r,y,_]).
false.

?- pipas([y,_,r,r,y,_]).
false.

?- pipas([y,_,_,r,y,r]).
false.

?- pipas([_,y,_,r,y,r]).
false.

(D) H´a uma pipa amarela imediatamente acima
de uma pipa vermelha.
pipas([_,y,_,r,y,_]).


(E) H´a uma pipa azul imediatamente acima de
uma pipa amarela.
pipas([_,y,b,_,y,_]).


*/