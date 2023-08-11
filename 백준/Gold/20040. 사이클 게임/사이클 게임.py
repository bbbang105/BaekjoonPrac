import sys
sys.setrecursionlimit(10**5)
input = sys.stdin.readline

def find(n):
    if parent[n] == n:
        return n
    parent[n] = find(parent[n])
    return parent[n]

def union(a,b):
    a,b = find(a),find(b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

N,M = map(int,input().split())
parent = [i for i in range(N)]

cycle = False
res = 0
for i in range(1,M+1):
    a,b = map(int,input().split())
    if not cycle:
        if find(a) == find(b):
            res = i
            cycle = True
        else:
            union(a,b)
            
print(res)  