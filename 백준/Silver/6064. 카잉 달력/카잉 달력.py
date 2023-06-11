import sys
input = sys.stdin.readline

def cal(m,n,x,y):
    k = x # k를 x로 초기화, 즉 x값을 고정으로 두고 계산을 시작함
    while k <= m*n:
        if (k-x) % m == 0 and (k-y) % n == 0: # 해를 찾은 경우
            return k
        k += m
    # 해가 나오지 않는 경우    
    return -1

T = int(input())
for _ in range(T):
    M,N,X,Y = map(int,input().split())
    print(cal(M,N,X,Y))