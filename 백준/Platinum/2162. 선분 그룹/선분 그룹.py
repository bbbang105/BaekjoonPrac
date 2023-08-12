import sys
input = sys.stdin.readline

def find(n):
    if parent[n] == n:
        return n
    parent[n] = find(parent[n])
    return parent[n]

def union(a,b):
    a,b = find(a),find(b)
    # 부모 노드 숫자가 작은 쪽으로 합침
    parent[b] = a
    cnt[a] += cnt[b]
    cnt[b] = 0

def sinbal(a,b,c):
    A = a[0]*b[1] + b[0]*c[1] + c[0]*a[1]
    B = a[1]*b[0] + b[1]*c[0] + c[1]*a[0]
    return A-B

def ccw(a,b):
    fx,fy,sx,sy = a[:2],a[2:],b[:2],b[2:]
    first = sinbal(fx,fy,sx)*sinbal(fx,fy,sy)
    second = sinbal(sx,sy,fx)*sinbal(sx,sy,fy)
    
    if first == 0 and second == 0:
        if fx > fy:
            fx,fy = fy,fx
        if sx > sy:
            sx,sy = sy,sx
        if fx <= sy and sx <= fy:
            return True
    else:
        if first <= 0 and second <= 0:
            return True
        else:
            return False

N = int(input())
lines = [list(map(int,input().split())) for _ in range(N)]
parent = [i for i in range(N)]
cnt = [1]*N

for i in range(N):
    for j in range(N):
        if find(i) != find(j):
            if ccw(lines[i],lines[j]) == True:
                union(i,j)

# 그룹 개수 찾기
res = 0
for i in cnt:
    if i != 0:
        res += 1
# 그룹 개수, 선분 개수 출력
print(res)
print(max(cnt))   