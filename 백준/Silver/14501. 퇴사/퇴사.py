import sys
input = sys.stdin.readline

N = int(input().rstrip())

day = []
pay = []
dp = []  # 해당 일수에서 시작하여 나올 수 있는 가장 큰 값을 담음 
         # => 1일부터 상담을 시작하는 것이 항상 크지 않기 때문

for i in range(N):
    a,b = map(int,input().split())
    day.append(a)
    pay.append(b)
    dp.append(b)
dp.append(0) # index를 넘기지 않기 위해 0을 추가해 줌

for i in range(N-1,-1,-1): # Top-Down탐색
    if i + day[i] > N:     # 퇴사 날짜를 넘어버리는 경우
        dp[i] = dp[i+1]  
    else:                  # i일에 상담을 할 때와 안할 때를 비교해 dp에 값 추가                 
        dp[i] = max(dp[i+1], pay[i] + dp[i+day[i]])
        
print(dp[0])