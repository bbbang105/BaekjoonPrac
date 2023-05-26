import sys
input = sys.stdin.readline

N = int(input())
require = int(input())
lst = sorted(list(map(int,input().split())))
left,right = 0,N-1
cnt = 0

while left < right:
    Sum = lst[left] + lst[right]
    
    if Sum < require:
        left += 1
    elif Sum == require:
        left += 1
        cnt += 1
    else:
        right -= 1
        
print(cnt)