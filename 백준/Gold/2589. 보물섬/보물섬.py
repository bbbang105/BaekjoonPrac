import sys
from collections import deque
from itertools import combinations
input = sys.stdin.readline

def bfs(x,y):
    Q = deque()
    Q.append((x,y,0)) # 0 == 이동 횟수
    maxMove = 0 # 최단 거리 중 가장 긴 거리
    visited[x][y] = index
    while Q:
        x,y,m = Q.popleft()
        for i in range(4):
            nx, ny, nm = x+dx[i], y+dy[i], m+1
            if 0 <= nx < N and 0 <= ny < M and visited[nx][ny] != index and graph[nx][ny] == 'L':
                visited[nx][ny] = index
                Q.append((nx,ny,nm))             
                # 가장 긴 거리로 계속해서 갱신  
                if nm > maxMove:
                    maxMove = nm

    return maxMove
                
N,M = map(int,input().split())
graph = [input().strip() for _ in range(N)]
islands = []
for i in range(N):
    for j in range(M):
        if graph[i][j] == 'L':
            islands.append((i,j))
            
res = 0     
index = 1 # 각 시도별로 방문여부를 분류하기 위함
visited = [[0]*M for _ in range(N)]
dx,dy = [-1,1,0,0],[0,0,-1,1]
for i in range(N):
    for j in range(M):
        if graph[i][j] == 'L':
            move = bfs(i,j)
            res = max(res,move)
            index += 1
print(res)