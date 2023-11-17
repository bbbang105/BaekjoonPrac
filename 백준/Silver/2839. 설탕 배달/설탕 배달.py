import sys
input = sys.stdin.readline

N = int(input())

if (N % 5 == 0):
    print(N // 5)
    exit()
    
max_five = N // 5
for i in range(max_five,-1,-1):
    if ((N - 5 * i) % 3 == 0):
        print(i + (N - 5 * i) // 3)
        exit()
        
print(-1)