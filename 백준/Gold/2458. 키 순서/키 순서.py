import sys
input = sys.stdin.readline
INF = sys.maxsize
# 입력
V,E = map(int,input().split())
dp = [[0]*V for _ in range(V)]
for _ in range(E):
    small,big = map(int,input().split())
    dp[small-1][big-1] = 1
# 플로이드-워셜 알고리즘
for k in range(V):
    for i in range(V):
        for j in range(V):
            if dp[i][k] and dp[k][j]:
                dp[i][j] = 1
# 키가 몇 번째인지 알 수 있는 노드 찾기
result = 0
for i in range(V): # 1번 노드부터 탐색
    Sum = 0        
    for j in range(V):
        Sum += dp[j][i] # 자신보다 작은 노드의 개수
    # (자신보다 작은 노드의 개수 + 큰 노드의 개수 == 전체 개수 - 1)인 경우 가능
    if Sum + sum(dp[i]) == V-1: 
        result += 1
# 출력        
print(result)           