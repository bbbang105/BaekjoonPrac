from collections import deque
import sys
input = sys.stdin.readline

for _ in range(int(input())):   
     
    command = input().rstrip()  # 명령어
    n = int(input())            # 정수의 개수
    Q = deque()
    nums = input().rstrip()
    if n > 0:
        for i in nums[1:-1].split(','):
            Q.append(i)
    
    error = False
    R_cnt = 0
    
    for j in command:
        if j == 'R': 
            R_cnt += 1
        elif j == 'D' and len(Q) == 0:  # 에러가 나오는 경우
            error = True
            break
        else:
            if R_cnt % 2 == 0: # 정방향일 경우
                Q.popleft() 
            else:              # 역방향일 경우
                Q.pop()
                
    if R_cnt % 2 == 1: 
        Q.reverse()
    
    if error:
        print('error')
    else:
        print('[' + ','.join(list(Q)) + ']')
          
    