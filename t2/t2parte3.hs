import Data.Char

-- Você deverá implementar uma função isEanOk :: String -> Bool, que verifique se uma dada string representa um 
-- número EAN-13 com dígito verificador válido.

divideString :: String -> [Int]
divideString "" = []
divideString str = (digitToInt (head str)) : divideString (tail str)

multiply_Array_Int :: [Int] -> [Int]
multiply_Array_Int array = zipWith (*) array [1,3,1,3,1,3,1,3,1,3,1,3,1]

aux_sum_12_first :: [Int] -> Int
aux_sum_12_first [] = 0
aux_sum_12_first array = head array + aux_sum_12_first (tail array)

sum_12_first :: [Int] -> Int
sum_12_first array = aux_sum_12_first (fst (splitAt 12 array))

get_near_decimal :: Int -> Int
get_near_decimal x = head (filter (>=x) [0,10..])

isEanOk :: String -> Bool
isEanOk str = if (((get_near_decimal(sum_12_first(multiply_Array_Int(divideString str)))) - (sum_12_first(multiply_Array_Int(divideString str)))) == last(divideString str)) then True else False 