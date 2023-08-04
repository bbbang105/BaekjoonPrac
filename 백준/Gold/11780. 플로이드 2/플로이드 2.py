import sys
input = sys.stdin.readline

n,m = int(input()),int(input())
graph = [[float('inf')]*n for _ in range(n)]
path = [[-1]*n for _ in range(n)]
for _ in range(m):
    a,b,c = map(int,input().split())
    a-=1; b-=1
    graph[a][b] = min(graph[a][b], c)
    path[a][b] = a

# 플로이드 실행
for k in range(n):
    graph[k][k] = 0
    path[k][k] = -1
    for i in range(n):
        for j in range(n):
            d = graph[i][k]+graph[k][j]
            if graph[i][j] > d:
                graph[i][j] = d
                path[i][j] = path[k][j]
            
for i in graph:
    for j in i:
        print(j if j!=float('inf') else 0, end=" ")
    print()

# 최단거리 역추적
for i in range(n):
    for j in range(n):
        if path[i][j] == -1:
            print(0)
            continue
        
        v = j
        ans = []
        while True:
            if v==i:
                break
            ans.append(v+1)
            v = path[i][v]
        print(len(ans)+1,i+1,*ans[::-1])