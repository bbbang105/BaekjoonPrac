import sys
input = sys.stdin.readline

N,L = map(int,input().split())               # 새는 곳의 개수, 테이프의 길이
loc = sorted(list(map(int,input().split()))) # 물이 새는 곳의 위치

if N == 1:   # 만약 개수가 1개라면, 무조건 1 출력                           
    print(1)
    exit(0)
    
check = [False for i in range(1001)] # False는 테이프로 막지 않음을 의미
cnt = 0  # 필요한 테이프의 개수
for i in loc:
    if check[i] == False: # 테이프로 아직 막지 않은 곳일 경우
        for j in range(i,i+L):
            if j > 1000:
                break
            check[j] = True
        cnt += 1

print(cnt)