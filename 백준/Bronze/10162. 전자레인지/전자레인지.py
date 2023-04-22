import sys
input = sys.stdin.readline

a, b, c = 300, 60, 10   # 5분, 1분, 10초
num = [0,0,0]           # 횟수를 담음
T = int(input())

while(True):
    if T >= 300:
        num[0] += 1
        T -= 300
    elif T >= 60:
        num[1] += 1
        T -= 60
    else:
        num [2] += 1
        T -= 10
    if T < 1:
        break
    
if T == 0:
    print(*num)     # 모든 원소 출력
else:
    print(-1)