/* 
                    CD INDEPENDENTE
Uma banda formada por alunos e alunas da escola est´a gravando um CD com exatamente sete m´usicas
distintas – S, T, V, W, X, Y e Z. Cada m´usica ocupa exatamente uma das sete faixas contidas no
CD. Algumas das m´usicas s˜ao sucessos antigos de rock; outras s˜ao composi¸c˜oes da pr´opria banda. As
seguintes restri¸c˜oes devem ser obedecidas:
•1 S ocupa a quarta faixa do CD.
•2 Tanto W como Y precedem S no CD (ou seja, W e Y est˜ao numa faixa que ´e tocada antes de S
no CD).
•3 T precede W no CD (ou seja, T est´a numa faixa que ´e tocada antes de W).
•4 Um sucesso de rock ocupa a sexta faixa do CD.
•5 Cada sucesso de rock ´e imediatamente precedido no CD por uma composi¸c˜ao da banda (ou seja,
no CD cada sucesso de rock toca imediatamente ap´os uma composi¸c˜ao da banda).
•6 Z ´e um sucesso de rock.
*/

sucesso_rock(z).
composicao_banda(_).

rule1(CD) :-
    CD = [_,_,_,s,_,_,_].

rule2(CD) :-
    nth0(Pos1, CD, w),
    nth0(Pos2, CD, y),
    nth0(Pos3, CD, s),
    Pos1 < Pos3,
    Pos2 < Pos3.

rule3(CD) :-
    nth0(Pos1, CD, t),    
    nth0(Pos2, CD, w),
    Pos1 < Pos2.

rule4(CD) :-
    CD = [_,_,_,_,_,X,_], sucesso_rock(X).

rule5(CD) :-
    sucesso_rock(X), composicao_banda(Y), nextto(Y, X, CD).

rule6(X) :-
    sucesso_rock(X).

ordem(CD) :-
    rule1(CD),
    rule2(CD),
    rule3(CD),
    rule4(CD),
    rule5(CD),
    nth0(5, CD, X),
    rule6(X).

/*
Questão 11. Qual das seguintes alternativas poderia ser a ordem das músicas no CD, da primeira
para a sétima faixa?
    (A) T, W, V, S, Y, X, Z 
    ordem([t,w,v,s,y,x,z]).

    (B) V, Y, T, S, W, Z, X 
    ordem([v,y,t,s,w,z,x]).

    (C) X, Y, W, S, T, Z, S 
    ordem([x,y,w,s,t,z,s]).

    (D) Y, T, W, S, X, Z, V 
    ordem([y,t,w,s,x,z,v]).

    (E) Z, T, X, W, V, Y, S 
    ordem([z,t,x,w,v,y,s]).

Alternativa correta = D
*/

