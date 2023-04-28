import sys
input = sys.stdin.readline

N,K = map(int,input().split())

coins = []
for _ in range(N):
    coins.append(int(input().rstrip()))
    
coins = coins[::-1]
coincnt = 0
for n in coins:
    if K >= n:
        coincnt += K//n
        K -= (K//n*n)
    if K == 0:
        break
print(coincnt)