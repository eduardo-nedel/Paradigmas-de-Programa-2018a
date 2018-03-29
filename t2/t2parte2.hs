-- 1 Escreva uma função recursiva isBin :: String -> Bool para verificar se uma dada String representa um número binário, ou seja, 
-- contém apenas caracteres '0' ou '1'. As únicas funções pré-definidas autorizadas aqui são head e tail. 

aux :: String -> Bool
aux "" = True
aux str
    | (head str) == '0' = aux (tail str)
    | (head str) == '1' = aux (tail str)
    | otherwise = False

isBin :: String -> Bool
isBin str = if (aux str && str/="") then True else False

-- 2 Reescreva a função acima de forma não-recursiva. Dê outro nome para ela, por exemplo isBin'. 
-- Aqui você pode usar quaisquer funções auxiliares pré-definidas em Haskell.

isBin' :: String -> Bool
isBin' str = if (length str==0) then False else (notElem False (map (\x -> elem x "10") str))

-- 3 Encontra-se abaixo a definição parcial da função bin2dec :: [Int] -> Int, 
-- que converte uma lista de 0's e 1's (representando um número binário), em seu equivalente em decimal.

bin2dec :: [Int] -> Int
bin2dec [] = undefined
bin2dec bits = auxBin2Dec bits ((length bits)-1)

-- Implemente a função auxBin2Dec de forma recursiva, para que bin2dec funcione corretamente, conforme os exemplos abaixo:

auxBin2Dec :: [Int] -> Int -> Int
auxBin2Dec [] tam = 0
auxBin2Dec bits tam = (head bits)*(2^tam) + auxBin2Dec (tail bits) (tam-1)

-- 4 Reescreva a função do exercício anterior de forma não-recursiva, usando funções pré-definidas em Haskell. Dê outro nome 
-- para a função (por exemplo, bin2dec').

-- bin2dec' :: [Int] -> Int