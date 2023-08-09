import sys
from collections import deque
input = sys.stdin.readline

def bfs(x,y):
    Q = deque()
    Q.append((x,y))
    
    while Q:
        x,y = Q.popleft()
        
        diff_lst = []
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < R and 0 <= ny < C and graph[nx][ny] != -1:
                diff_lst.append((nx,ny))
        # 확산되는 경우
        if diff_lst:
            num = int(graph[x][y] / 5) # 확산되는 미세먼지의 양
            dp[x][y] -= (num*len(diff_lst)) # 확산되는 만큼 빼줌
            # 인접한 칸에는 더해줌
            for x,y in diff_lst:
                dp[x][y] += num

R,C,T = map(int,input().split())
graph = [list(map(int,input().split())) for _ in range(R)]

dx,dy = [-1,1,0,0],[0,0,-1,1]

# 공기청정기 위치 찾기
temp = False
for i in range(R):
        for j in range(C):
            if graph[i][j] == -1:
                air_upper = (i,j)   # 공기청정기 위쪽 좌표
                air_lower = (i+1,j) # 공기청정기 아래쪽 좌표
                temp = True
                break
        if temp:
            break
        
# 주어진 시간만큼 반복
while T:
    dp = [[0]*C for _ in range(R)] # +- 값을 넣어줌
    # 모든 칸에 대해 탐색
    for i in range(R):
        for j in range(C):
            # 미세먼지가 있는 경우 확산
            if graph[i][j] > 0:
                bfs(i,j)
                
    # dp에 저장된 값으로 그래프 실제로 변경
    for i in range(R):
        for j in range(C):
            graph[i][j] += dp[i][j]
    
    # 공기 청정기 바람 이동
    # 순차적으로 하나씩 공기 청정기 쪽으로 당겨옴
    
    # 1. 공기 청정기 위쪽 이동
    start_x,start_y = (air_upper[0],air_upper[1])
    
    # 1.(1) 왼쪽 이동
    for x in range(start_x-1,0,-1):
        # 공기 청정기로 빨려들어가면 제거
        if graph[x][0] == -1:
            graph[x-1][0] = 0
        # 한 칸 이동할 시
        else:
            graph[x][0] = graph[x-1][0]
            graph[x-1][0] = 0
            
    # 1.(2) 맨 윗줄 이동  
    for y in range(1,C):
        graph[0][y-1] = graph[0][y]
        graph[0][y] = 0  
        
    # 1.(3) 오른쪽 이동
    for x in range(1,start_x+1):
        graph[x-1][C-1] = graph[x][C-1]
        graph[x][C-1] = 0
        
    # 1.(4) 아랫줄 이동
    for y in range(C-2,0,-1):
        graph[start_x][y+1] = graph[start_x][y]
        graph[start_x][y] = 0
    
    # 2. 공기 청정기 아래쪽 이동
    start_x,start_y = (air_lower[0],air_lower[1])
    
    # 2.(1) 왼쪽 이동
    for x in range(start_x+1,R):
        # 공기 청정기로 빨려들어가면 제거
        if graph[x-1][0] == -1:
            graph[x][0] = 0
        # 한 칸 이동할 시
        else:
            graph[x-1][0] = graph[x][0]
            graph[x][0] = 0
            
    # 2.(2) 맨 아랫줄 이동  
    for y in range(1,C):
        graph[R-1][y-1] = graph[R-1][y]
        graph[R-1][y] = 0   
        
    # 2.(3) 오른쪽 이동
    for x in range(R-2,start_x-1,-1):
        graph[x+1][C-1] = graph[x][C-1]
        graph[x][C-1] = 0
        
    # 2.(4) 윗줄 이동
    for y in range(C-2,0,-1):
        graph[start_x][y+1] = graph[start_x][y]
        graph[start_x][y] = 0
    
    # 시간 - 1
    T -= 1

# 미세먼지 양 합산
res = 0
for row in graph:
    res += sum(row)
# 공기 청정기만큼 +2하여 출력
print(res+2)