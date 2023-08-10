import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def find(n):
    if parent[n] == n:
        return n
    parent[n] = find(parent[n])
    return parent[n]

def union(a,b):
    a = find(a)
    b = find(b)
    
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

N,M = map(int,input().split())
parent = [i for i in range(N+1)]

for _ in range(M):
    command,n1,n2 = map(int,input().split())
    # 합집합을 만드는 연산
    if command == 0:
        union(n1,n2)
    # 같은 집합인지 확인하는 연산    
    else:
        a = find(n1)
        b = find(n2)
        # 같은 집합일 경우
        if a == b:
            print("YES")
        # 다른 집합일 경우
        else:
            print("NO")