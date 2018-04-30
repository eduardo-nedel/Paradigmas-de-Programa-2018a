/*
OBI2003 
https://olimpiada.ic.unicamp.br/static/extras/obi2003/provas/ProvaOBI2003_f0i0.pdf
                    ROLAND GARROS
No torneio de Roland Garros, um dos mais tradicionais torneios de tênis do mundo, realizado em
Paris, participam 128 tenistas. Em cada partida, participam dois jogadores, sendo que o vencedor
passa para a próxima fase, e o perdedor é eliminado do torneio. A cada rodada, os tenistas que
ainda continuam no torneio participam de exatamente uma partida.
*/

num_partidas(1, 0) :- !.
num_partidas(Paricipantes, Partidas) :-
    X is Paricipantes/2,
    num_partidas(X, Y),
    Partidas is Y + X.

/*
Questão 3. Qual o número total de partidas deste torneio?
    (A) 64
    num_partidas(128,64).
    
    (B) 65
    num_partidas(128,65).

    (C) 127
    num_partidas(128,127).
    
    (D) 128
    num_partidas(128,128).
    
    (E) nenhuma das acima
    num_partidas(128,X).  ---> Note que X é um valor qualquer, com excessao dos anteriores, inserido pelo usuário

Alternativa correta = C.
*/