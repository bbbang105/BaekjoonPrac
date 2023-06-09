from collections import deque
import sys
input = sys.stdin.readline

def BFS(x,y,rain):
    global visited
    Q = deque()
    Q.append((x,y))
    
    dx = [1,-1,0,0]
    dy = [0,0,-1,1]
        
    while Q:
        
        x,y = Q.popleft()
        
        for i in range(4):
            if 0 <= x+dx[i] < N and 0 <= y+dy[i] < N:
                if graph[x+dx[i]][y+dy[i]] > rain and not visited[x+dx[i]][y+dy[i]]:
                    Q.append((x+dx[i],y+dy[i]))
                    visited[x+dx[i]][y+dy[i]] = True
        
N = int(input())
graph = [list(map(int,input().split())) for _ in range(N)]
Q = deque()
maxNum = 0
for rain in range(0,101):
    visited = [list(False for _ in range(N)) for _ in range(N)]
    cnt = 0
    
    for x in range(N):
        for y in range(N):
            if not visited[x][y] and graph[x][y] > rain:
                BFS(x,y,rain)
                cnt += 1
    
    if cnt == 0:
        break
                 
    maxNum = max(maxNum, cnt)
            
print(maxNum)