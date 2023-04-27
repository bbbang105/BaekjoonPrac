import sys
input = sys.stdin.readline

N,M = map(int,input().split())          # 나무의 개수와 필요한 길이
tree = list(map(int,input().split()))   # 나무들의 길이를 입력받음
start,end = 1, max(tree)

while (start <= end):                   # 이분 탐색
    mid = (start+end)//2
    sum = 0
    for x in tree:
        if x > mid:
            sum += (x-mid)
    if sum >= M:
        start = (mid+1)
    else:
        end = (mid-1)
        
print(end)