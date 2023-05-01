import sys
input = sys.stdin.readline

cnt0 = [1,0,1]
cnt1 = [0,1,1]

def fibonacci(x):
    length = len(cnt0)
    if x >= length:
        for i in range(length,x+1):
            cnt0.append(cnt0[i-1] + cnt0[i-2])
            cnt1.append(cnt1[i-1] + cnt1[i-2])
    print(cnt0[x],cnt1[x])

for _ in range(int(input())):
    fibonacci(int(input()))