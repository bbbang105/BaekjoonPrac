import sys
sys.setrecursionlimit(10**6)
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
    
N = int(input())
M = int(input())

parent = [i for i in range(N+1)]
for i in range(1,N+1):
    # 간선 정보
    info = [0] + list(map(int,input().split()))
    # 연결된 경우
    for j in range(1,N+1):
        if info[j] == 1:
            # 양방향이기에
            union(i,j)
            union(j,i)
            
plan = list(map(int,input().split())) # 여행 계획

res = set([find(n) for n in plan])
# 부모 노드가 모두 동일한 경우
if len(res) > 1:
    print("NO")
# 다른 경우
else:
    print("YES")