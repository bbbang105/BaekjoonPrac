import sys
input = sys.stdin.readline

bar_razor = list(input().rstrip())
answer = 0
stk = []

for i in range(len(bar_razor)):
    if bar_razor[i] == '(':
        stk.append('(')

    else:
        if bar_razor[i-1] == '(': 
            stk.pop()
            answer += len(stk)

        else:
            stk.pop() 
            answer += 1 

print(answer)