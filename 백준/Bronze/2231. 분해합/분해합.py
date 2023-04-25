import sys
input = sys.stdin.readline

N = int(input())               # 숫자를 입력 받음
M = len(str(N)) * 9            # 자릿수에 따라 나올 수 있는 가장 큰 자릿수의 합
tmp = False                    # 생성자 유무 확인

if N <= 10:                    # 한 자릿수인 경우 (2,4,6,8,10)
    print(N//2)                # 생성자는 N의 절반
    exit(0)                    # 프로그램을 종료해 줌
    
for i in range(M,0,-1):        # 가장 작은 생성자를 찾는 것이므로, 큰 수부터 빼면서 계산
    if (N-i) < 0:              # 음수일 경우 continue
        continue
    
    new_N = str(N-i)
    S = 0
    for j in new_N:            # 자릿수의 합을 구함
        S += int(j) 
    result = S + int(new_N)
    
    if N == result:
        tmp = True
        break
    
if tmp:
    print(new_N)
else:
    print(0)