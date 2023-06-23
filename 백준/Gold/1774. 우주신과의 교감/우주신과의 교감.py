import sys,math
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
stars = [list(map(int,input().split())) for _ in range(N)]
parent = list(range(N+1))

# 이미 연결된 통로
already_link = [list(map(int,input().split())) for _ in range(M)]
for a,b in already_link:
    already_cnt = 0
    if find(a-1) != find(b-1): # 중복 입력일 가능성이 있기에
        union(a-1,b-1)
        already_cnt += 1


# 모든 통로 길이 계산
paths = []
for i in range(N-1):
    for j in range(i+1,N):
        distance = math.sqrt((stars[j][0]-stars[i][0])**2 + (stars[j][1]-stars[i][1])**2)
        paths.append((distance,i,j)) # 거리, 출발, 도착
paths.sort()
    
# Kruskal Algorithm  
length = 0 
cnt = already_cnt  # 이미 연결된 통로의 개수
for w,a,b in paths:
    if find(a) != find(b):
        union(a,b)
        length += w
        cnt += 1
    
    if cnt == N-1:
        break
        
# Output
print(f'{length:.2f}')