import sys,math
input = sys.stdin.readline

K = int(input())

cnt = 0
while True:
    isPrime = True
    for i in range(2,K+1):
        if K % i == 0:
            K //= i
            cnt += 1
            isPrime = False
            break
        
    if isPrime:
        break

print(math.ceil(math.log2(cnt)))