from collections import deque
import sys
input = sys.stdin.readline
                    
n, k = map(int, input().split())
visited = [[-1, 0] for _ in range(100001)]  # 소요 시간, 경우의 수

def bfs(n):
    Q = deque([n])
    visited[n][0] = 0
    visited[n][1] = 1 
    
    while Q:
        x = Q.popleft()
        
        for i in [x - 1, x + 1, x * 2]:
            if 0 <= i <= 100000:
                if visited[i][0] == -1:               # 첫 방문일 시
                    visited[i][0] = visited[x][0] + 1 # 소요 시간 저장
                    visited[i][1] = visited[x][1]     # 경우의 수 저장
                    Q.append(i)
                    
                elif visited[i][0] == visited[x][0] + 1: # 첫 방문이 아니지만, 최소 시간일 시
                    visited[i][1] += visited[x][1]       # 경우의 수 +1
                    
bfs(n)
print(visited[k][0])
print(visited[k][1])