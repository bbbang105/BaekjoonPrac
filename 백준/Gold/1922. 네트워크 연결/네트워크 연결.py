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
N = int(input())
M = int(input())
lines = [list(map(int,input().split())) for _ in range(M)]
lines.sort(key = lambda x : x[2])
parent = list(range(N+1))

# Kruskal Algorithm
spend = 0
for a,b,w in lines:
    if find(a) != find(b):
        union(a,b)
        spend += w
 
# Output       
print(spend)