import sys
input = sys.stdin.readline

N = int(input())                # 입력

s = []                          # 각 숫자들을 저장
for i in str(N):                # 문자열로 변환하여
    s.append(int(i))            # 리스트에 추가
    
if sum(s)%3!= 0 or 0 not in s:  # 각 숫자의 합이 3이 아니거나 0이 없는 경우
    print(-1)                   # -1 출력
else:                           # 30의 배수는 3의 배수이면서 10의 배수여야 함
    s = sorted(s,reverse=True)  # 내림차순으로 출력하면 가장 큰 수
    print(*s,sep='')