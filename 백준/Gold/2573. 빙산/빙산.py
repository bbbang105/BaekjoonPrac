import sys
from collections import deque
input = sys.stdin.readline

# BFS
def BFS(x,y):
    visited[x][y] = True
    sea_lst = []
    Q = deque()
    Q.append((x,y))
    
    while Q:
        x,y = Q.popleft()
        
        sea = 0 # 인접한 바다물의 개수
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < N and 0 <= ny < M:
                # 바다인 경우
                if not graph[nx][ny]:
                    sea += 1
                # 첫 방문하는 빙산인 경우
                if graph[nx][ny] and not visited[nx][ny]: 
                    visited[nx][ny] = True
                    Q.append((nx,ny))
        # 좌표와 인접한 바다물의 개수를 저장     
        if sea > 0:
            sea_lst.append((x,y,sea))
    # 빙산을 녹이는 과정        
    for x,y,n in sea_lst:
        graph[x][y] = max(0, graph[x][y] - n)
         
    return 1  # 탐색 종료는 그룹 하나가 끝났음을 의미 

# 입력
N,M = map(int,input().split())
graph = [list(map(int,input().split())) for _ in range(N)]

dx = [1,-1,0,0]
dy = [0,0,-1,1]

# 초기 빙산의 위치 찾기
ice = []
for i in range(N):
    for j in range(M):
        if graph[i][j] > 0:
            ice.append((i,j))
            
# 년도별 변화   
year = 0        
while ice:
    visited = [[False]*M for _ in range(N)]
    del_lst = [] # 다 녹아버린 빙산의 정보를 저장
    group = 0
    
    for x,y in ice:
        # 빙산 그룹 탐색
        if graph[x][y] and not visited[x][y]:
            group += BFS(x,y)
        # 탐색이 끝난 후에, 다 녹아버린 빙산을 찾아 저장
        if graph[x][y] == 0:
            del_lst.append((x,y))
    
    # 두 그룹 이상으로 나눠진 경우                            
    if group >= 2:
        print(year)
        exit(0)
        
    ice = list(set(ice) - set(del_lst)) # 다 녹은 빙산을 제외
    year += 1  # 년도 추가
    
print(0)