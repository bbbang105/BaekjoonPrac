from collections import deque
import sys
input = sys.stdin.readline

N,K = map(int,input().split())
    
def BFS(start,target):              # BFS
    Q = deque([N])
    visited = [0]*100001            # 방문여부와 횟수 저장
    
    while Q:
        n = Q.popleft()
        for node in [n+1,n-1,n*2]:  # 3가지 경우
            if 0 <= node <= 100000: # 범위 설정
                if visited[node]:   # 방문했었으면 continue
                    continue
                elif node == K:     # 도착하면 return
                    return visited[n]+1
                else:               # 처음이면
                    Q.append(node)
                    visited[node] = (visited[n]+1)
                    
if N == K:
    print(0)
else:
    print(BFS(N,K))