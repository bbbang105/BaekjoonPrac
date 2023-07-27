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
            mx = x + dx[i]
            my = y + dy[i]
            if 0 <= mx < N and 0 <= my < M:                   
                # 이동할 곳이 벽이지만 기회가 있는 경우
                if graph[mx][my] == 1 and not visited[k-1][mx][my] and k:
                    # 기회 사용 후 이동
                    visited[k-1][mx][my] = visited[k][x][y] + 1
                    Q.append((k-1,mx,my))
           
                # 이동할 곳이 빈 공간인 경우
                elif graph[mx][my] == 0 and not visited[k][mx][my]:
                    visited[k][mx][my] = visited[k][x][y] + 1
                    Q.append((k,mx,my))
    
    return -1               

N,M,K = map(int,input().split())
graph = [list(map(int,input().strip())) for _ in range(N)]
dx = [-1,1,0,0]
dy = [0,0,-1,1]
res = bfs(K,0,0) 
print(res)