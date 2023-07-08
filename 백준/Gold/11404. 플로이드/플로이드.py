import sys
input = sys.stdin.readline
INF = sys.maxsize
# Input
V = int(input())
E = int(input())
dp = [[INF]*V for _ in range(V)]
for i in range(V):
    dp[i][i] = 0
# 초기 노선 정보를 저장
for _ in range(E):
    u,v,w = map(int,input().split())
    if dp[u-1][v-1] == INF:
        dp[u-1][v-1] = w
    # 중복된 정보일 경우, 더 작은 값을 넣어줌
    else:
        dp[u-1][v-1] = min(dp[u-1][v-1],w)
# 플로이드-워셜 알고리즘   
for k in range(V):
    for i in range(V):
        for j in range(V):
            if dp[i][j] > dp[i][k] + dp[k][j]:
                dp[i][j] = dp[i][k] + dp[k][j]
# Output                
for row in dp:
    for i in range(V):
        # 갈 수 없는 경우
        if row[i] == INF:
            row[i] = 0
    print(*row)