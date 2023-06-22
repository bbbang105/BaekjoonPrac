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
N,M = map(int,input().split())
parent = list(range(N+1))
roads = []
for _ in range(M):
    roads.append(list(map(int,input().split())))
roads.sort(key = lambda x : x[2])

# Kruskal Algorithm
expend = []
for a,b,w in roads:
    if find(a) != find(b):
        union(a,b)
        expend.append(w)

# Output
expend = sorted(expend)[:-1]
print(sum(expend))