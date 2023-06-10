from collections import deque
import sys
input = sys.stdin.readline

# BFS
def BFS(x,y):
    Q = deque()
    Q.append((x,y))
    
    dx = [1,-1,0,0]
    dy = [0,0,-1,1]
    
    visited = [[False for _ in range(M)] for _ in range(N)]
    
    cnt = 0
    while Q:
        x,y = Q.popleft()
        
        for i in range(4):
            mx = x+dx[i]
            my = y+dy[i]
            
            if 0 <= mx < N and 0 <= my < M:
                if graph[mx][my] == 'O' and not visited[mx][my]:
                    visited[mx][my] = True
                    Q.append((mx,my))
                elif graph[mx][my] == 'P' and not visited[mx][my]:
                    visited[mx][my] = True
                    Q.append((mx,my))
                    cnt += 1
                    
    return cnt
   
# Main             
N,M = map(int,input().split())
graph = [list(input().rstrip()) for _ in range(N)]

for i in range(N):
    for j in range(M):
        if graph[i][j] == 'I':
            P_num = BFS(i,j) # BFS 탐색
            
            if not P_num: # 아무도 만나지 못한 경우
                print('TT')
            else:
                print(P_num)
                
            exit(0)