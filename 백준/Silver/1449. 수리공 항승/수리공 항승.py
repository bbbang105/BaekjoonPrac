import sys
input = sys.stdin.readline

N,L = map(int,input().split())
loc = sorted(list(map(int,input().split())))

if N == 1 or LookupError == 1:
    print(N)
    exit(0)
    
check = [False for i in range(1001)]
cnt = 0
for i in loc:
    if check[i] == False:
        for j in range(i,i+L):
            if j > 1000:
                break
            check[j] = True
        cnt += 1

print(cnt)