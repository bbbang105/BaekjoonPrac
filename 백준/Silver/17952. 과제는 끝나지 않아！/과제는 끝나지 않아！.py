import sys
input = sys.stdin.readline

stk = []
result = 0
for _ in range(int(input().rstrip())):
    num = list(map(int,input().rstrip().split()))

    if num[0] == 1:
        if num[2] == 1:
            result += num[1]
        else:
            stk.append([num[1],num[2]-1])
    elif num[0] == 0 and stk:
        if (stk[-1][1]-1) == 0:
            result += stk[-1][0]
            stk.pop()
        else:
            stk[-1][1] -= 1
            
print(result)