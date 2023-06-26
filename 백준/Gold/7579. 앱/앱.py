import sys
input = sys.stdin.readline

N,M = map(int,input().split())
B = [0] + list(map(int,input().split())) # Byte
C = [0] + list(map(int,input().split())) # Cost
dp = [[0 for _ in range(sum(C)+1)] for _ in range(N+1)] # dp에는 확보한 byte량이 저장됨
answer = sum(C) # Cost의 최댓값

for i in range(1,N+1): # i는 앱 번호를 의미
    byte = B[i]
    cost = C[i]
    for j in range(1,sum(C)+1): # j는 cost를 의미
        # 현재 cost로 앱을 비활성화할 수 없는 경우
        if j < cost:
            dp[i][j] = dp[i-1][j]
        # 현재 cost로 비활성화할 수 있는 경우
        else: 
            # 같은 cost 내에서 현재 앱을 끈 뒤의 byte와 끄지 않은 뒤의 byte를 비교
            dp[i][j] = max(dp[i-1][j], byte + dp[i-1][j-cost])
        # 확보한 byte량이 조건에 충족하는 경우  
        if dp[i][j] >= M:
            # 더 작은 cost값으로 갱신
            answer = min(answer,j)
            
if M != 0:
    print(answer)
else:
    print(0)