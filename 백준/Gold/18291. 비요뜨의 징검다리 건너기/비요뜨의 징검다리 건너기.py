import sys,math
input = sys.stdin.readline
mod = 1000000007
def fpow(C,n): # 고속 거듭제곱
    res = 1
    
    while n:
        if n % 2 != 0: # 홀수인 경우
            res *= C
            res %= mod # 값이 커짐을 방지하여, 연산시마다 나눠줌
        C *= C
        C %= mod
        n //= 2
        
    return res
            
T = int(input())
for _ in range(T):
    N = int(input())
    if N == 1:
        print(1)
    else:
        print(fpow(2,N-2))