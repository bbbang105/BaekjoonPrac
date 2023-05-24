import sys
input = sys.stdin.readline

N, goal = map(int, input().split())
lst = (list(map(int, input().split())))
left, right = 0, 1
cnt = 0
temp = lst[0]

while True:
    if temp < goal:
        if right < N:
            temp += lst[right]
            right += 1
        else:
            break
    
    elif temp == goal:
        cnt += 1
        temp -= lst[left]
        left += 1
        
    else:
        temp -= lst[left]
        left += 1
        
print(cnt)