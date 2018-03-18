import Data.Char

--               _________________________________
--              |---------------------------------|
--              |------Eduardo Mueller Nedel------|
--              |----------ELC117-2018a-----------|
--              |---------------------------------|


-- 1. Crie uma função isVowel :: Char -> Bool que verifique se um caracter é uma vogal ou não.
isVowel :: Char -> Bool
isVowel c = elem (toLower c) "aeiou" 

-- 2. Escreva uma função addComma, que adicione uma vírgula no final de cada string contida numa lista.
addComma :: [String] -> [String]
addComma list = map (++",") list

-- 3. Crie uma função htmlListItems :: [String] -> [String], que receba uma lista de strings e retorne outra lista contendo as strings formatadas como itens de lista em HTML.
-- Com Anônima
htmlListItems :: [String] -> [String]
htmlListItems list = map (\x -> "<LI>" ++ x ++ "</LI>") list

-- Sem Anônima
aux :: String -> String
aux str = "<LI>" ++ str ++ "</LI>"

htmlListItems2 :: [String] -> [String]
htmlListItems2 list = map aux list

-- 4. Defina uma função que receba uma string e produza outra retirando as vogais, conforme os exemplos abaixo.
-- Com Anônima
semVogais :: String -> String
semVogais str = filter (\x -> not (isVowel x)) str

-- Sem Anônima
aux2 :: Char -> Bool
aux2 x = not (isVowel x)

semVogais2 :: String -> String
semVogais2 str = filter aux2 str

-- 5. Defina uma função que receba uma string, possivelmente contendo espaços, e que retorne outra string substituindo os demais caracteres por '-', mas mantendo os espaços.
-- Com Anônima 
codifica :: String -> String
codifica str = map (\x -> if x /=' ' then '-' else x) str

-- Sem Anônima
aux3 :: Char -> Char
aux3 c = if c /=' ' then '-' else c

codifica2 :: String -> String
codifica2 str = map aux3 str

-- 6. Escreva uma função firstName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu primeiro nome. Suponha que cada parte do nome seja separada por um espaço e que não existam espaços no início ou fim do nome.
firstName :: String -> String
firstName str = takeWhile (/=' ') str

-- 7. Escreva uma função isInt :: String -> Bool que verifique se uma dada string só contém dígitos de 0 a 9.
isInt :: String -> Bool
isInt str = notElem False (map (\n -> elem n "0123456789") str)

-- 8. Escreva uma função lastName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu último sobrenome.
lastName :: String -> String
lastName str = reverse (firstName(reverse str))

-- 9. Escreva uma função userName :: String -> String que, dado o nome completo de uma pessoa, crie um nome de usuário (login) da pessoa, formado por: primeira letra do nome seguida do sobrenome, tudo em minúsculas.
userName :: String -> String
userName str = map toLower ([head (firstName str)] ++ (lastName str))

-- 10. Escreva uma função encodeName :: String -> String que substitua vogais em uma string, conforme o esquema a seguir: a = 4, e = 3, i = 2, o = 1, u = 0.
valorVogal :: Char -> Char
valorVogal c
  | (toLower c) == 'a' = '4'
  | (toLower c) == 'e' = '3'
  | (toLower c) == 'i' = '2'
  | (toLower c) == 'o' = '1'
  | (toLower c) == 'u' = '0'
  | otherwise = c

encodeName :: String -> String
encodeName str = map valorVogal str

-- 11. Escreva uma função betterEncodeName :: String -> String que substitua vogais em uma string, conforme este esquema: a = 4, e = 3, i = 1, o = 0, u = 00.


-- 12. Dada uma lista de strings, produzir outra lista com strings de 10 caracteres, usando o seguinte esquema: strings de entrada com mais de 10 caracteres são truncadas, strings com até 10 caracteres são completadas com '.' até ficarem com 10 caracteres.
tenCharacters :: [String] -> [String]
tenCharacters list = map (\str -> if (length str) > 10 then (take 10 str) else (take 10 (str ++ ".........."))) list

