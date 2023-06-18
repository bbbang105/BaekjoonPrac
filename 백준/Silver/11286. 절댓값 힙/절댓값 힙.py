import heapq
import sys
input = sys.stdin.readline

N = int(input())
hq = []
for _ in range(N):
    command = int(input())
    # 값을 넣는 연산
    if command != 0:
        # 절댓값을 우선순위로 넣어줌
        heapq.heappush(hq, (abs(command),command))
    # 값을 출력 & 제거하는 연산
    else:
        if len(hq) == 0:
            print(0)
        else:
            # 원래의 값을 출력
            print(heapq.heappop(hq)[1])