import sys
from collections import deque
input = sys.stdin.readline

def bfs(x,y):
    Q = deque()
    Q.append((x,y))
    visited[x][y] = 0
    
    while Q:
        x,y = Q.popleft()
        
        if x == end_x and y == end_y:
            return visited[x][y]
        
        for i in range(8):
            nx,ny = x+dx[i],y+dy[i]
            if 0 <= nx < N and 0 <= ny < N and visited[nx][ny] == -1:
                visited[nx][ny] = visited[x][y] + 1
                Q.append((nx,ny))     
                
dx,dy = [-1,-2,-2,-1,1,2,2,1],[-2,-1,1,2,2,1,-1,-2]

T = int(input())
for _ in range(T):
    N = int(input())
    start_x,start_y = map(int,input().split())
    end_x,end_y = map(int,input().split())
    visited = [[-1]*N for _ in range(N)]
    print(bfs(start_x,start_y))