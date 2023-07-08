import sys
input = sys.stdin.readline

N = int(input())
graph = [list(map(int,input().split())) for _ in range(N)]

for k in range(N): # 거쳐가는 노드
    for i in range(N): # 시작 노드
        for j in range(N): # 도착 노드
            if graph[i][k] == 1 and graph[k][j] == 1: # i가 k를 거쳐서 j로 갈 수 있는 경우
                graph[i][j] = 1
                
for row in graph:
    print(*row)
    