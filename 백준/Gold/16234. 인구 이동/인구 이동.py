import sys
from collections import deque
input = sys.stdin.readline

def bfs(x,y):
    global change,all_same
    Q = deque()
    Q.append((x,y))
    visited[x][y] = True
    # 연합 리스트, 인구수 합계
    unity_lst = [(x,y)]
    Sum = graph[x][y]   
    
    while Q:
        x,y = Q.popleft()
        for i in range(4):
            nx,ny = x + dx[i], y + dy[i]
            if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny]:
                # 국경선이 열리는 경우
                if under <= abs(graph[x][y]-graph[nx][ny]) <= high:
                    visited[nx][ny] = True
                    Q.append((nx,ny))
                    unity_lst.append((nx,ny))
                    Sum += graph[nx][ny]
    # 연합이 생성되었다면 그래프에서도 바꿔줌                
    if len(unity_lst) > 1:
        avg = int(Sum/len(unity_lst)) # 소수점 버림
        for x,y in unity_lst:
            # 하한선이 0인 경우 무한 루프되는 것을 방지하기 위함
            if graph[x][y] != avg:
                all_same = False
            graph[x][y] = avg
    # 최종적으로 인구 이동이 발생한 경우
    if not all_same:
        change = True
 
N,under,high = map(int,input().split())
graph = [list(map(int,input().split())) for _ in range(N)]

dx,dy = [-1,1,0,0],[0,0,-1,1]

day = 0
while True:
    visited = [[False]*N for _ in range(N)]
    change = False
    all_same = True
    for i in range(N):
        for j in range(N):
            if not visited[i][j]:
                bfs(i,j)
    # 인구 이동이 발생하지 않았다면            
    if not change:
        print(day)
        break
    
    day += 1