import sys
input = sys.stdin.readline

result = 0
cnt = 0
for _ in range(20):
    a = input().rstrip().split()
    num = int(a[1][0])  # 학점
    grade = a[2]     # 등급
    
    if grade == 'A+':
        result += num*(4.5)
        cnt += num
    elif grade == 'A0':
        result += num*(4.0)
        cnt += num
    elif grade == 'B+':
        result += num*(3.5)
        cnt += num
    elif grade == 'B0':
        result += num*(3.0)
        cnt += num
    elif grade == 'C+':
        result += num*(2.5)
        cnt += num
    elif grade == 'C0':
        result += num*(2.0)
        cnt += num
    elif grade == 'D+':
        result += num*(1.5)
        cnt += num
    elif grade == 'D0':
        result += num*(1.0)
        cnt += num
    elif grade == 'F':
        result += num*(0)
        cnt += num
    else:               # P일 경우
        continue

final = result/cnt
print(f"{final:.6f}")