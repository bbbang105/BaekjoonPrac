import sys
from collections import deque
from itertools import combinations
input = sys.stdin.readline

def bfs(lst):
    Q = deque()
    # 바이러스 위치 넣어주기
    for x,y in lst:
        Q.append((x,y))
        visited[x][y] = 0

    while Q:
        x,y = Q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < N and 0 <= ny < N:
                # 바이러스 퍼뜨리기
                if graph[nx][ny] != 1 and visited[nx][ny] == -1 and (nx,ny) not in viruses:
                    visited[nx][ny] = visited[x][y] + 1
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
    visited = [[0]*N for _ in range(N)]
    # 바이러스가 퍼질 수 있는 곳을 -1로 변경
    for i in range(N):
        for j in range(N):
            if graph[i][j] == 0 or graph[i][j] == 2:
                visited[i][j] = -1
    # 바이러스 위치만으로 bfs 실행
    bfs(viruses)
    # 모든 칸에 바이러스가 퍼진 시간 파악
    distance = 0
    possible = True
    for row in visited:
        # 바이러스가 다 퍼지지 않은 경우
        if -1 in row:
            possible = False
            break
        distance = max(distance,max(row))
    # 조합 중 최소 시간 파악
    if possible:
        res = min(res,distance)
# 불가능한 경우
if res == 10000:
    print(-1)
else:
    print(res)