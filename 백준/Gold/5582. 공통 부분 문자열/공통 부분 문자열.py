import sys
input = sys.stdin.readline

W1 = input().rstrip()
W2 = input().rstrip()

i = 0      # 인덱스
w = ''     # 부분 문자열
length = 0 # 최대 길이를 저장
while i < len(W1):
    w += W1[i]      # 뒤에 문자를 하나씩 추가
    if w in W2:     # 공통 부분 문자열인 경우
        length = max(length,len(w))
    else:           # 공통 부분 문자열이 아닌 경우
        if len(w) > 1: # 한 글자 이상인 경우
            w = w[1:]
        else:          # 한 글자인 경우
            w = ''
        length = max(length,len(w))
        
    i += 1
    
print(length)   