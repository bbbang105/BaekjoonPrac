import sys
input = sys.stdin.readline

N,K = map(int,input().split()) # 물건의 개수, 배낭의 최대 무게
W,V = [],[] # 각 물건의 무게, 가치
for _ in range(N):
    w,v = map(int,input().split())
    W.append(w); V.append(v)
    
DP = [0 for _ in range(K+1)] # 배낭 무게별, 최대로 들어갈 수 있는 가치합

for i in range(N):
    for j in range(K,0,-1):
        if W[i] <= j:
            DP[j] = max(DP[j], DP[j-W[i]]+V[i])

print(DP[-1])