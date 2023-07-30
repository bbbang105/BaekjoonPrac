import sys
from collections import deque
from itertools import combinations
input = sys.stdin.readline

def bfs(lst):
    Q = deque()
    viruses = list(lst)
    # 바이러스 위치 넣어주기
    for x,y in viruses:
        Q.append((x,y))
        visited[x][y] = 0

    while Q:
        x,y = Q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < N and 0 <= ny < N:
                # 빈칸인 경우 바이러스 확산
                if graph[nx][ny] == 0 and visited[nx][ny] == -1:
                    visited[nx][ny] = visited[x][y] + 1
                    Q.append((nx,ny))
                # 비활성 바이러스를 만나면 활성으로 변경
                elif graph[nx][ny] == 2 and  visited[nx][ny] == -1 and (nx,ny) not in viruses:
                    visited[nx][ny] = visited[x][y] + 1
                    viruses.append((nx,ny))
                    Q.append((nx,ny))
    
N,M = map(int,input().split())
graph = [list(map(int,input().split())) for _ in range(N)]

# 바이러스 위치 파악
virus_lst = []
for i in range(N):
    for j in range(N):
        if graph[i][j] == 2:
            virus_lst.append((i,j))
            
dx = [-1,1,0,0]
dy = [0,0,-1,1]
# 조합을 이용해 브루트포스           
res = 10000
for viruses in combinations(virus_lst,M):
    visited = [[-1]*N for _ in range(N)]
    
    # 바이러스 위치만으로 bfs 실행
    bfs(viruses)
    
    distance = 0
    possible = True
    
    # 각 조합별 이동횟수 파악
    for i in range(N):
        for j in range(N):
            if graph[i][j] == 0:
                # 바이러스가 다 퍼지지 않은 경우
                if visited[i][j] == -1:
                    possible = False
                    break
                distance = max(distance,visited[i][j])
            
    # 다 퍼진 경우, 최소 시간 파악
    if possible:
        res = min(res,distance)
        
# 불가능한 경우
if res == 10000:
    print(-1)
else:
    print(res)