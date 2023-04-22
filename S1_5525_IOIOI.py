import sys
input = sys.stdin.readline

N = int(input())     # P문자열의 길이
M = int(input())     # 문자열의 길이
S = input().rstrip() # 문자열 입력

answer, i, count = 0, 0, 0

while i < (M-1):
    if S[i:i+3] == 'IOI': # 3칸씩 탐색하여 맞을 경우
        i += 2
        count += 1        
        if count == N:    # P문자열과 동일할 경우
            answer += 1   # 결과값 +1
            count -= 1
    
    else:                 # IOI문자열이 아닐 경우
        i += 1
        count = 0         # count 초기화
        
print(answer)