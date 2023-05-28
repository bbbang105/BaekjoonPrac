import sys, math
input = sys.stdin.readline

def isPrimeNum(n):
    check = [True for i in range(n*2+1)]
    cnt = n
    for i in range(2,int(math.sqrt(2*n)+1)):
        if check[i] == True:
            j = 2
            while i*j <= 2*n:
                if check[i*j] == True:
                    check[i*j] = False
                    if n < i*j <= 2*n:
                        cnt -= 1
                j += 1
                    
    return cnt
    
while True:
    N = int(input())
    if N == 0:
        break
    
    print(isPrimeNum(N))