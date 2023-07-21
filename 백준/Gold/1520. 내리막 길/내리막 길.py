import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**8)

def DFS(x,y):
    # 도착지에 도달한 경우
    if x == R-1 and y == C-1:
        return 1 # 경로의 수 + 1
    
    # 이미 최적의 경로가 있는 경우
    if dp[x][y] != -1: 
        return dp[x][y]
    
    ways = 0
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < R and 0 <= ny < C:
            if graph[nx][ny] < graph[x][y]: # 내리막길인 경우
                ways += DFS(nx,ny)
     
    dp[x][y] = ways
    return dp[x][y]
                
R,C = map(int,input().split())
graph = [list(map(int,input().split())) for _ in range(R)]
dp = [[-1]*C for _ in range(R)]
dx = [1,-1,0,0]
dy = [0,0,-1,1]

res = DFS(0,0)
print(res)