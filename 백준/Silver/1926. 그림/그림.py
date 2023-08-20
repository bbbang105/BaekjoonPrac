import sys
from collections import deque
input = sys.stdin.readline

def bfs(x,y):
    Q = deque()
    Q.append((x,y))
    visited[x][y] = True
    cnt = 1
    while Q:
        x,y = Q.popleft()
        for i in range(4):
            nx,ny = x+dx[i],y+dy[i]
            if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny] and graph[nx][ny] == 1:
                visited[nx][ny] = True
                Q.append((nx,ny))
                cnt += 1

    return cnt
                   
N,M = map(int,input().split())
graph = [list(map(int,input().split())) for _ in range(N)]
dx,dy = [-1,1,0,0],[0,0,-1,1]
res,maxNum = 0,0 # 그림의 개수, 최대 넓이
visited = [[False]*M for _ in range(N)]
for i in range(N):
    for j in range(M):
        if graph[i][j] == 1 and not visited[i][j]:
            temp = bfs(i,j)
            maxNum = max(maxNum,temp)
            res += 1
            
print(res,maxNum,sep = '\n')        