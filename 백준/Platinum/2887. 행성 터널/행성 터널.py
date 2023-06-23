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
xlst,ylst,zlst = [],[],[]
# 좌표별 리스트 구성
for i in range(N):
    x,y,z = map(int,input().split())
    xlst.append((x,i))
    ylst.append((y,i))
    zlst.append((z,i))
xlst.sort(); ylst.sort(); zlst.sort()

# 연결 비용 계산
paths = []
for curlist in xlst,ylst,zlst:
    for i in range(1,N):
        w1,a = curlist[i-1]
        w2,b = curlist[i]
        paths.append((abs(w1-w2),a,b))
paths.sort()

# Kruskal Algorithm
parent = list(range(N+1))
result = 0
line_cnt = 0
for w,a,b in paths:
    if find(a) != find(b):
        union(a,b)
        result += w
        line_cnt += 1
        
    # 필요한 간선을 모두 구한 경우
    if line_cnt == N-1:
        break

# Output
print(result)