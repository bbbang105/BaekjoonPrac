import sys
input = sys.stdin.readline

while(True):
    N = list(map(int,input().split()))
    if sum(N) == 0:         # 0 0 0이 입력될 경우 종료
        break
    
    L = max(N)              # 세변 중 가장 긴 변 저장
    N.remove(max(N))        # 리스트에서 삭제
    
    if L**2 == N[0]**2 + N[1]**2:  # 가장 긴 변과 나머지 두 변의 각 제곱의 합이 동일하면
        print('right')             # 직각삼각형
    else:
        print('wrong')