import sys
from collections import deque
sys = sys.stdin.readline

N,K = map(int,input().split())
nums = list(map(int,input().strip()))
stk = []

for i in range(N):
    # K개 까지만 지워야하므로
    while stk and K > 0:
        # 맨 앞에 가장 큰 수를 오게 하기 위함
        if stk[-1] < nums[i]:
            stk.pop()
            K -= 1
        else:
            break
    stk.append(nums[i])
    
# 스택에 들어있는 숫자 출력
if K > 0: # 스택에서 K개만큼을 제거하지 않은 경우
    print(*stk[:len(stk)-K],sep = '')
else:     # 스택에서 K개만큼을 제거한 경우
    print(*stk,sep = '')