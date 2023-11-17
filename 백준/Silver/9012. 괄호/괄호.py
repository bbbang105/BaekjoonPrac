for _ in range(int(input())):
    s = input()
    stk = []
    temp = True
    for i in s:
        if i == '(':
            stk.append(i)
        elif i == ')':
            if not stk or stk[-1] == ')':
                temp = False
                break
            elif stk[-1] == '(':
                stk.pop()
                
    if not stk and temp == True: # 짝이 모두 맞을 시
        print('YES')
    else:
        print('NO')