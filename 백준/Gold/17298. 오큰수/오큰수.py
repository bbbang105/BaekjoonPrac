import sys
input = sys.stdin.readline

n = int(input())
L = list(map(int, input().split()))
answer = [-1] * n # n개수만큼 -1로 초기화
I = []            # index를 넣어줄 list
I.append(0)       # 초기 index는 0

for i in range(1,n):
    while I and L[I[-1]] < L[i]: # 이전의 값들과 현재 i에 해당하는 값을 비교
        answer[I.pop()] = L[i]   # 오큰수를 구한 index는 pop()
    I.append(i)                  # 다음 반복에서 구할 index를 넣어줌
                                 # 만약 오큰수를 구하지 못했더라도, i+1, i+2..에서 구할 가능성도 존재함
print(*answer)                   # 모든 원소 출력