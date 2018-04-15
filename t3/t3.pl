% 1. Defina um predicado zeroInit(L) que é verdadeiro se L for uma lista que inicia com o número 0 (zero).
zeroInit(L) :- L = [H|_], H =:= 0.

/* 2. Defina um predicado has5(L) que é verdadeiro se L for uma lista de 5 elementos.
Resolva este exercício sem usar um predicado auxiliar. */

has5(L) :- L = [_,_,_,_,_].

% 3. Defina um predicado hasN(L,N) que é verdadeiro se L for uma lista de N elementos.

hasN(L, N) :- length(L, A), A =:= N.

% 4. Defina um predicado potN0(N,L), de forma que L seja uma lista de potências de 2, com expoentes de N a 0.

potN0(0, [1]).
potN0(N, L) :-
    L = [H|T],
    X is N-1,
    H is 2**N,
    potN0(X, T).

/* 5. Defina um predicado zipmult(L1,L2,L3), de forma que cada elemento da lista L3 seja o produto dos elementos 
de L1 e L2 na mesma posição do elemento de L3. */

zipmult([], _, []).
zipmult(L1, L2, L3) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    L3 = [H3|T3],
    H3 is H1*H2,
    zipmult(T1, T2, T3).

/* 6. Defina um predicado potencias(N,L), de forma que L seja uma lista com as N primeiras potências de 2, 
sendo a primeira 2^0 e assim por diante.. */

potencias(0, []).
potencias(N,L) :- 
    potN0(N-1, AUX),
    reverse(AUX, L).



    

