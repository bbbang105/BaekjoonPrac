import sys
import heapq
input = sys.stdin.readline

MaxHQ = []                                  # 최대힙
N = int(input())
for _ in range(N):
    cmd = int(input().rstrip())
    if cmd == 0:    
        if MaxHQ:
            print(heapq.heappop(MaxHQ)[1])  # 각 튜플의 두번째에 최대값이 들어있음
        else:
            print(0)
    else:
        heapq.heappush(MaxHQ,(-cmd,cmd))    # 기본은 최소힙이므로 -를 붙여서 최대값을 함께 넣어줌