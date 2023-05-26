import sys
input = sys.stdin.readline

N = int(input())
lst = sorted(list(map(int,input().split())))
left,right = 0,N-1
minNum = abs(lst[left]+lst[right])
n1,n2 = lst[left],lst[right]

while left < right:
    temp = lst[left] + lst[right]
    
    if abs(temp) < minNum:
        minNum = abs(temp)
        n1 = lst[left]
        n2 = lst[right]
    
    if temp < 0:
        left += 1
    else:
        right -= 1

print(n1, n2)