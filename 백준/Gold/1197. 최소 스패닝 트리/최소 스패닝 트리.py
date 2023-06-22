import sys
input = sys.stdin.readline

# Union-Find
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

# Main      
V,E = map(int,input().split()) # 노드, 간선의 개수
paths = []  # 간선 리스트
parent = list(range(V+1)) # 부모 노드 테이블
for _ in range(E):
    paths.append(list(map(int,input().split())))
paths.sort(key = lambda x : x[2]) # 가중치를 오름차순으로 정렬

MinWeight = 0
for path in paths:
    a,b,w = path[0],path[1],path[2] # 출발 노드, 도착 노드, 가중치
    # 부모 노드가 달라, Union해도 '사이클'이 생기지 않는 경우
    if find(a) != find(b):
        union(a,b)
        MinWeight += w

print(MinWeight)