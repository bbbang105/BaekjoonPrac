import sys
input = sys.stdin.readline

# Union Find
def find(x):
    if parent[x] == x:
        return x
    return find(parent[x])

def union(a,b):
    a = find(a)
    b = find(b)
    
    if a < b:
        parent[b] = a
    else:
        parent[a] = b
        
# Input
while True:
    N,M = map(int,input().split())
    if N == 0 and M == 0: exit(0)
    
    paths = []
    all_spend = 0 # 모든 비용의 합
    for _ in range(M):
        a,b,w = map(int,input().split())
        paths.append((a,b,w))
        all_spend += w
    paths.sort(key = lambda x : x[2])
    parent = list(range(N+1))

    # Kruskal Algorithm
    spend = 0 # 최소 비용
    cnt = 0   # 간선의 개수
    for a,b,w in paths:
        if find(a) != find(b):
            union(a,b)
            spend += w
            cnt += 1
            
        if cnt == N-1:
            break

    # Output
    print(all_spend - spend)