import heapq
import sys
input = sys.stdin.readline

N = int(input())
deck = []
for _ in range(N):
    deck.append(int(input()))
heapq.heapify(deck) # heap으로 변환

if len(deck) == 1:  # 덱이 1개인 경우
    print(0)
    exit(0)
    
count = 0  # 총 비교 횟수
while len(deck) > 1:
    d1 = heapq.heappop(deck) # 가장 작은 개수의 덱
    d2 = heapq.heappop(deck) # 그 다음으로 작은 개수의 덱
    compare = (d1 + d2)      # 두 덱 비교
    count += compare
    heapq.heappush(deck,compare) # 다시 heap에 넣어줌

print(count)