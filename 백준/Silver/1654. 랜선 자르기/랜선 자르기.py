import sys
input = sys.stdin.readline

K,N = map(int,input().split())      # 가지고 있는 랜선의 개수, 필요한 랜선의 개수
ran = []
for _ in range(K):
    ran.append(int(input()))
    
start,end = 1,max(ran)
while (start <= end):               # 이분 탐색
    mid = (start+end)//2
    c = 0
    for r in ran:
        c += (r//mid)
    if c >= N:
        start = (mid+1)
    else:
        end = (mid-1)
print(end)