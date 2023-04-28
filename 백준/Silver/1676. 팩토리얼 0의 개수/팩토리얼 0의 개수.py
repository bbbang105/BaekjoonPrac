import sys
input = sys.stdin.readline

def factorial(x):
    if x == 0 or x == 1:
        return 1
    else:
        result = 1
        for i in range(2,x+1):
            result *= i
        return result
    
N = factorial(int(input()))
N = str(N)[::-1]

c = 0
for i in N:
    if i != '0':
        print(c)
        break
    c += 1