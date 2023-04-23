from itertools import combinations
import sys
input = sys.stdin.readline

N,M = map(int,input().split())
cards = list(map(int,input().split()))
answer = []

for i in combinations(cards,3): # 중복 없이 카드 3장을 뽑는 조합
    if sum(i) <= M:             # 카드 3장의 합이 M값을 넘지 않는 경우
        answer.append(sum(i))   # 값에 추가
        
print(max(answer))              # 가장 큰 값 출력