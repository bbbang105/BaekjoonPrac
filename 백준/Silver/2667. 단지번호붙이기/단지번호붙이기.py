from collections import deque
import sys
input = sys.stdin.readline

def BFS(x,y):
    global house_lst
    Q = deque()
    Q.append((x,y))
    visited[x][y] = True # 본인 제외
    
    dx = [1,-1,0,0]
    dy = [0,0,-1,1]
    
    cnt = 1 # 단지 내 집의 수
    while Q:
        x,y = Q.popleft()
        
        for i in range(4):
            if 0 <= x+dx[i] < N and 0 <= y+dy[i] < N:
                if graph[x+dx[i]][y+dy[i]] and not visited[x+dx[i]][y+dy[i]]:
                    Q.append((x+dx[i],y+dy[i]))
                    visited[x+dx[i]][y+dy[i]] = True
                    cnt += 1
                    
    house_lst.append(cnt)
    
N = int(input())
graph = [list(map(int,input().rstrip())) for _ in range(N)]
visited = [[False for _ in range(N)] for _ in range(N)]
house_cnt, house_lst = 0, []
for i in range(N):
    for j in range(N):
        if not visited[i][j] and graph[i][j]:
            BFS(i,j)
            house_cnt += 1 # 총 단지수
            
print(house_cnt, *sorted(house_lst), sep = '\n')