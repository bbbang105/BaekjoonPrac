import sys
from collections import deque
input = sys.stdin.readline

def bfs(x,y):
    visited[x][y] = True
    Q = deque()
    Q.append((x,y))
    while Q:
        x,y = Q.popleft()
        for i in range(8):
            nx,ny = x+dx[i],y+dy[i]
            if 0 <= nx < M and 0 <= ny < N:
                if graph[nx][ny] == 1 and not visited[nx][ny]:
                    visited[nx][ny] = True
                    Q.append((nx,ny))
                
dx,dy = [-1,1,0,0,-1,-1,1,1],[0,0,-1,1,-1,1,-1,1]

while True:
    N,M = map(int,input().split()) # 열,행
    if N == 0 and M == 0:
        break
    
    graph = [list(map(int,input().split())) for _ in range(M)]
    
    visited = [[False]*N for _ in range(M)]
    res = 0
    for i in range(M):
        for j in range(N):
            if graph[i][j] == 1 and not visited[i][j]:
                bfs(i,j)
                res += 1
                
    print(res)