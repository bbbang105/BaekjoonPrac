import sys
input = sys.stdin.readline
dp = ['']*1000
w1 = input().strip()
w2 = input().strip()
for i in range(len(w1)):
    temp = ''
    for j in range(len(w2)):
        if len(temp) < len(dp[j]):
            temp = dp[j]
        elif w1[i] == w2[j]:
            dp[j] = temp+w2[j]
            
dp = list(set(dp))
dp = sorted(dp,key = lambda x : -len(x))
LCS = dp[0]
if LCS:
    print(len(LCS))
    print(LCS)
else:
    print(0)