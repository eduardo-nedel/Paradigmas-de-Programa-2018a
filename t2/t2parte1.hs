-- 1 Usando recursão, escreva uma função geraTabela :: Int -> [(Int,Int)] que produza uma lista com n tuplas, cada tupla com números de n a 1 e seus respectivos quadrados. 
geraTabela :: Int -> [(Int,Int)]
geraTabela 0 = []
geraTabela n = (n, n*n) : geraTabela (n-1)

-- 2 Defina uma função recursiva que verifique se um dado caracter está contido numa string, 
contido :: Char -> String -> Bool
contido c "" = False
contido c nome = if (c == (head nome)) then True else contido c (tail nome)

-- 3 Defina uma função recursiva que receba uma lista de coordenadas de pontos 2D e desloque esses pontos em 2 unidades.
translate :: [(Float, Float)] -> [(Float, Float)]
translate [] = []
translate tupla = (fst (head tupla)+2, snd (head tupla)+2) : translate (tail tupla)

-- 4 Defina uma função que receba um número n e retorne uma lista de n tuplas, cada tupla com números de 1 a n e seus respectivos quadrados. 
aux :: Int -> Int -> [(Int, Int)]
aux x n = if x == n+1
    then []
    else (x, x*x) : aux (x+1) n

geraTabela' :: Int -> [(Int,Int)]  
geraTabela' n = aux 1 n