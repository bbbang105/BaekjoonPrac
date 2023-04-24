from collections import deque
import sys
input = sys.stdin.readline

# 카드 덱 생성
dq = deque(i for i in range(1,int(input())+1))

while len(dq) > 1:
    dq.popleft()             # 맨 위에 있는 카드를 한 장 버림
    dq.append(dq.popleft())  # 그 다음으로 맨 위에 있는 카드를 맨 아래에 넣어줌
    
print(dq[0])                 # 제일 마지막에 남는 카드의 숫자를 출력