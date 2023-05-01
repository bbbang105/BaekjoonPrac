import sys
input = sys.stdin.readline

S = input().rstrip()

pre = S[0]
cnt = 0
for i in S:
    if i != pre:
        cnt += 1
    pre = i

if cnt %2 != 0:
    print(cnt//2 + 1)
else:
    print(cnt//2)