# LLPL
(= LispLikeProgrammingLanguage)

Interpreted array-oriented programming language

## Examples
```
a = (reduce [1 2 3 4] {[acc b] (+ a b)})
(print a)
```
```
(print (index 2 [0 9 4 2 9 3]))
# prints 3
```
