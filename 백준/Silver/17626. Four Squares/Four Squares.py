import sys
input = sys.stdin.readline

dp = [0,1]              # 0의 제곱, 1의 제곱 저장
N = int(input())        # 값 입력

for i in range(2,N+1):
    mn = 1e9            # 1의 9승
    j = 1               # 본인보다 작은 제곱수
    while(j**2 <= i):   # 그 중 최대값까지 반복
        mn = min(mn, (dp[i-j**2]))  # 가장 최소 개수를 넣어줌
        j += 1                      # Ex) 19-(4**2) = 1+1+1이므로 4개이지만, 19-(3**2) = 9+1 이므로 3개임 
    dp.append(mn+1)

print(dp[N])