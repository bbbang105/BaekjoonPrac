import sys
from collections import deque
input = sys.stdin.readline

def bfs(x,y,c):
    global flag
    for i in range(4):
        nx,ny = x+dx[i],y+dy[i]
        if 0 <= nx < N and 0 <= ny < M:
            # 물을 퍼뜨리는 경우
            if c == 0:
                visited[x][y] = -1
                if graph[nx][ny] == '.' and visited[nx][ny] != -1:
                    visited[nx][ny] = -1
                    new_water_lst.append((nx,ny))
            # 비버가 이동하는 경우   
            else:
                if graph[nx][ny] == 'D':
                    flag = True
                    return
                elif graph[nx][ny] == '.' and not visited[nx][ny]:
                    visited[nx][ny] = time
                    new_b_lst.append((nx,ny))
        
N,M = map(int,input().split())
graph = [list(input().strip()) for _ in range(N)]
water_lst = []
for i in range(N):
    for j in range(M):
        if graph[i][j] == 'S':
            bx,by = i,j
        elif graph[i][j] == '*':
            water_lst.append((i,j))
dx,dy = [-1,1,0,0],[0,0,-1,1]   
time = 1
flag = False
visited = [[0]*M for _ in range(N)]
b_lst = []
b_lst.append((bx,by))
while True:
    new_water_lst = []
    for x,y in water_lst:
        bfs(x,y,0)
        
    new_b_lst = []
    for x,y in b_lst:
        bfs(x,y,1)
        
    if flag:
        print(time)
        break
    
    if not new_b_lst:
        print('KAKTUS')
        break
    
    water_lst = new_water_lst
    b_lst = new_b_lst
    time += 1