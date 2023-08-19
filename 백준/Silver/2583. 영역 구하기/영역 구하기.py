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
            # 영역 넓히기
            if 0 <= nx < M and 0 <= ny < N and not visited[nx][ny] and graph[nx][ny] == 0:
                cnt += 1 # 개수 + 1
                visited[nx][ny] = True
                Q.append((nx,ny))
                
    return cnt # 개수 반환

M,N,K = map(int,input().split())
graph = [[0]*N for _ in range(M)]
# 직사각형 표시하기
for _ in range(K):
    a,b,c,d = map(int,input().split())
    for i in range(M-b-1,M-d-1,-1):
        for j in range(a,c):
            if graph[i][j] == 0:
                graph[i][j] = 1
 
dx,dy = [-1,1,0,0],[0,0,-1,1]               
visited = [[False]*N for _ in range(M)]
res = []
for i in range(M):
    for j in range(N):
        # 새로운 영역 발견
        if graph[i][j] == 0 and not visited[i][j]:
            cnt = bfs(i,j)
            res.append((cnt)) # 리스트에 추가
            
res.sort()            
print(len(res))
print(*res)