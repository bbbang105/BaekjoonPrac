import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def DFS(p):
    for i in graph[p]:
        if not visited[i]:
            visited[i] = p # 부모 노드를 저장해줌
            DFS(i)         # 더 깊이 탐색

N = int(input())
graph = [[] for _ in range(N+1)]
visited = [0]*(N+1)

for _ in range(N-1):
    n1,n2 = map(int,input().split())
    graph[n1].append(n2)
    graph[n2].append(n1)
    
DFS(1) # 루트 노드가 1이므로 

for parent in visited[2:]: # 2번 노드부터 출력
    print(parent)