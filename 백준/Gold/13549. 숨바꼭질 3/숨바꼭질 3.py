from collections import deque
import sys
input = sys.stdin.readline

N,K = map(int,input().split())

if N == K:
    print(0)
    exit(0)

graph = [0 for _ in range(100001)]  # 시간을 저장
visited = [False for _ in range(100001)] # 방문 여부
 
# BFS
def BFS(x):
    Q = deque()
    Q.append(x)
    
    while Q:
        q = Q.popleft()
        
        # 좌,우,순간이동할 좌표
        left = q-1
        right = q+1
        teleportion = q*2
        
        for move in teleportion,left,right:
            if 0 <= move <= 100000 and not visited[move]:
                visited[move] = True # 방문 처리
                
                # 순간이동하는 경우
                if move == teleportion:
                    graph[move] = graph[q]
                    # 가중치가 0이기에, 큐의 맨 앞에 삽입
                    Q.appendleft(move)
                    
                # 좌,우로 걷는 경우
                else:
                    graph[move] = graph[q] + 1
                    Q.append(move)
                
                # 동생을 찾은 경우
                if move == K:
                    return graph[move]
                
print(BFS(N))
    