import sys
inpiut = sys.stdin.readline

# Input
N= int(input())
DP = [list(map(int,input().split())) for _ in range(N)]

for i in range(1,N):
    # 각 색별 최소 비용을 저장
    DP[i][0] += min(DP[i-1][1],DP[i-1][2]) # R
    DP[i][1] += min(DP[i-1][0],DP[i-1][2]) # G
    DP[i][2] += min(DP[i-1][0],DP[i-1][1]) # B
    
print(min(DP[-1]))