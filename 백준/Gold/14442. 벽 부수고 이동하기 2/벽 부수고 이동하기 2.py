# nk를 만들어도 괜찮다!
import sys
from collections import deque
input = sys.stdin.readline

def bfs(k,x,y):
    Q = deque()
    Q.append((k,x,y))
    # visited[k][x][y] = k만큼 기회가 있는 상태
    visited = [[[0] * M for _ in range(N)] for _ in range(K+1)]
    visited[k][x][y] = 1
    
    while Q:
        k,x,y = Q.popleft()
        
        # 도착한 경우
        if x == N-1 and y == M-1:
            return visited[k][x][y]
        # 상하좌우 이동
        for i in range(4):
            nk = k-1
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < N and 0 <= ny < M:                   
                # 이동할 곳이 벽이지만 기회가 있는 경우
                if graph[nx][ny] == 1 and not visited[nk][nx][ny] and k:
                    # 기회 사용 후 이동
                    visited[nk][nx][ny] = visited[k][x][y] + 1
                    Q.append((nk,nx,ny))
           
                # 이동할 곳이 빈 공간인 경우
                elif graph[nx][ny] == 0 and not visited[k][nx][ny]:
                    visited[k][nx][ny] = visited[k][x][y] + 1
                    Q.append((k,nx,ny))
    
    return -1               

N,M,K = map(int,input().split())
graph = [list(map(int,input().strip())) for _ in range(N)]
dx = [-1,1,0,0]
dy = [0,0,-1,1]
res = bfs(K,0,0) 
print(res)