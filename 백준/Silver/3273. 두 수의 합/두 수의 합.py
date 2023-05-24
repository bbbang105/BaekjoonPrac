import sys
input = sys.stdin.readline

N = int(input())
lst = sorted(list(map(int,input().rstrip().split())))
goal = int(input())
left,right = 0, len(lst)-1
cnt = 0

while left != right:
    temp = lst[left] + lst[right]
    if temp == goal:
        cnt += 1
    if temp < goal:
        left += 1
    else:
        right -= 1
        
print(cnt)