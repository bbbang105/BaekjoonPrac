import sys
input = sys.stdin.readline

N = int(input())
cards = list(map(int, input().split()))
cards.sort()

M = int(input())
Q = list(map(int, input().split()))

dct = dict()            # 카드의 개수를 담을 dict

for i in cards:
    if i in dct:        # 중복되는 카드인 경우
        dct[i] += 1
    else:               # 처음 나오는 카드인 경우
        dct[i] = 1
        
for i in range(M):
    if Q[i] in dct:     # 가지고 있는 카드일 경우
        print(dct[Q[i]], end = ' ')
    else:               # 가지고 있지 않은 카드일 경우
        print(0, end = ' ')