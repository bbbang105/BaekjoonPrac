import sys
input = sys.stdin.readline

N = int(input())
lst = sorted(list(map(int,input().split())))
minNum = 3000000001

for i in range(N-2):
    fixNum = lst[i]
    left = i+1
    right = N-1
    while left < right:
        temp = fixNum + lst[left] + lst[right]
        
        if abs(temp) < minNum:
            minNum = abs(temp)
            threeSol = [fixNum, lst[left], lst[right]]
            
        if temp < 0:
            left += 1
        elif temp > 0:
            right -= 1
        else:
            print(*threeSol)
            exit(0)
            
print(*threeSol)