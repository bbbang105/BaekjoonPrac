import sys
input = sys.stdin.readline

N = int(input())
lst = list(map(int,input().split()))
M = int(input())
dp = [[0]*(N+1) for _ in range(N+1)]

for i in range(N): # i는 글자 수를 의미
    for start in range(N-i):
        end = start+i # 확인할 시작점과 끝점
        # 한 글자인 경우
        if start == end:
            dp[start][end] = 1
        # 두 글자 이상이고, 양 끝이 같은 경우
        elif lst[start] == lst[end]:
            # 두 글자인 경우
            if start+1 == end:
                dp[start][end] = 1
            # 가운데가 팰린드롬인 경우
            elif dp[start+1][end-1]:
                dp[start][end] = 1             
                
for _ in range(M):
    a,b = map(int,input().split())
    print(dp[a-1][b-1])