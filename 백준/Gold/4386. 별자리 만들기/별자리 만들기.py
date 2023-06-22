import sys, math
input = sys.stdin.readline

# Union Find
def find(x):
    if parent[x] == x:
        return x
    else:
        return find(parent[x])
    
def union(a,b):
    a = find(a)
    b = find(b)
    
    if a < b:
        parent[b] = a
    else:
        parent[a] = b
        
# Input
N = int(input())
stars = [list(map(float,input().split())) for _ in range(N)]
parent = list(range(N+1))
paths = []

# 모든 별들 간의 거리 계산
for i in range(N-1):
    for j in range(i+1,N):
        distance = math.sqrt((stars[j][0] - stars[i][0])**2 + (stars[j][1] - stars[i][1])**2)
        paths.append((distance,i,j)) # 거리, 시작 노드, 끝 노드

# 오름차순 정렬        
paths.sort()

# Kruskal Algorithm
result = 0
for d,a,b in paths:
    if find(a) != find(b):
        union(a,b)
        result += d

# Output
print(round(result,2))