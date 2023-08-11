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
    # 친구 비용이 낮은 쪽으로 합쳐줌
    if price[a] < price[b]:
        parent[b] = a
    else:
        parent[a] = b
        
N,M,charge = map(int,input().split())
price = [0] + list(map(int,input().split()))
parent = [i for i in range(N+1)]
# 친구 관계를 통해 집합 형성
for _ in range(M):
    n1,n2 = map(int,input().split())
    union(n1,n2)
# 친구 비용을 지불해야 하는 집합만 더해줌
res = 0 
set_lst = [find(p) for p in parent[1:]]
set_lst = list(set(set_lst))
for p in set_lst:
    res += price[p]
# 모든 학생을 친구로 만들 수 있는 경우
if charge >= res:
    print(res)
# 다 사귈 수 없는 경우
else:
    print("Oh no")