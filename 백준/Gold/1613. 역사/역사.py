import sys
input = sys.stdin.readline
INF = sys.maxsize

V,E = map(int,input().split())
dp = [[0]*(V+1) for _ in range(V+1)]
for i in range(1,V+1):
    dp[i][i] = 0
for _ in range(E):
    u,v = map(int,input().split())
    dp[u][v] = 1

# 플로이드-워셜 알고리즘
for k in range(1,V+1):
    for i in range(1,V+1):
        for j in range(1,V+1):
            if not dp[i][j] and dp[i][k] and dp[k][j]:
                dp[i][j] = 1
# 선후관계 찾아서 출력
N = int(input())
for i in range(N):
    ac1,ac2 = map(int,input().split())
    if dp[ac1][ac2] == 1:
        print(-1)
    elif dp[ac2][ac1] == 1:
        print(1)
    else:
        print(0)