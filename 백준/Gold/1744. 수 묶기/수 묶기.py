import sys
input = sys.stdin.readline

N = int(input())

plus,minus = [],[]
for _ in range(N):
    n = int(input())
    # 0보다 큰 경우
    if n > 0:
        plus.append(n)
    # 0이거나 0보다 작은 경우
    else:
        minus.append(n)

# 절댓값이 큰 수끼리 짝지어 지도록 정렬
plus.sort(reverse=True) 
minus.sort(reverse=False)

res = 0
# 양수끼리만 진행
i = 0
while i < len(plus)-1:
    # 하나라도 1인 경우에는 합해주는 것이 더 큼
    if plus[i] == 1 or plus[i+1] == 1:
        res += plus[i] + plus[i+1]
        i += 2
    # 그 외에는 2개씩 곱해줌
    else:
        res += plus[i] * plus[i+1]
        i += 2
        
# 남은 숫자가 있는 경우 더하기   
if i == len(plus)-1:
        res += plus[i]
    
# 0과 음수끼리만 진행
i = 0
while i < len(minus)-1:
    # 2개씩 곱해줌
    res += minus[i] * minus[i+1]
    i += 2

# 남은 숫자가 있는 경우 더하기 
if i == len(minus)-1:
    res += minus[i]

print(res)