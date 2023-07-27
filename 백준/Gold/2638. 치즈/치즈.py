import sys
from collections import deque
input = sys.stdin.readline

def bfs(x,y):
    Q = deque()
    Q.append((x,y))
    visited[x][y] = True
    
    while Q:
        x,y = Q.popleft()
        
        contact = 0 
        for i in range(4):
            nx = x + dx[i] 
            ny = y + dy[i]
            if 0 <= nx < N and 0 <= ny < M:
                # 접촉한 외부 공기의 개수 세기
                if graph[nx][ny] == 0 and expose[nx][ny]:
                    contact += 1
                # 인접한 치즈를 만난 경우
                elif graph[nx][ny] == 1 and not visited[nx][ny]:
                    visited[nx][ny] = True
                    Q.append((nx,ny))
        # 사라질 치즈 리스트에 추가
        if contact >= 2:
            del_lst.append((x,y))
                
N,M = map(int,input().split())
graph = [list(map(int,input().split())) for _ in range(N)]
cheese_lst = []
dx,dy = [-1,1,0,0],[0,0,-1,1]
time = 0
# 치즈 위치 파악
for i in range(N):
    for j in range(M):
        if graph[i][j] == 1:
            cheese_lst.append((i,j))
            
del_lst = []         
# 치즈가 몇 시간 안에 녹는지 알아내기
while True:
    # 리스트에서도 사라진 치즈를 제거
    cheese_lst = list(set(cheese_lst) - set(del_lst))
    del_lst = []
    # 모두 사라진 경우
    if not cheese_lst:
        print(time)
        exit(0)
    
    # 치즈 내부,외부 파악    
    expose = [[False]*M for _ in range(N)]
    Q = deque()
    Q.append((0,0))
    while Q:
        x,y = Q.popleft()
        for i in range(4):
            nx = x + dx[i] 
            ny = y + dy[i]
            if 0 <= nx < N and 0 <= ny < M:
                if graph[nx][ny] == 0 and not expose[nx][ny]:
                    expose[nx][ny] = True
                    Q.append((nx,ny))

    # bfs 실행                
    visited = [[False]*M for _ in range(N)]
    for x,y in cheese_lst:
        if not visited[x][y]:
            bfs(x,y)
            
    # 탐색이 끝난 후에 치즈를 제거해줌
    for x,y in del_lst:
        graph[x][y] = 0   

    time += 1