import sys
input = sys.stdin.readline

N,S = map(int, input().split())
lst = (list(map(int, input().split())))
left, right = 0, 1
length = N+1
Sum = lst[0]

if S == 0:
    print(1)
    exit(0)

while True:
    if Sum < S:
        if right < N:
            Sum += lst[right]
            right += 1
        else:
            break
    
    else:
        length = min(length, (right-left))
        Sum -= lst[left]
        left += 1

if length == N+1:
    print(0)
else:       
    print(length)