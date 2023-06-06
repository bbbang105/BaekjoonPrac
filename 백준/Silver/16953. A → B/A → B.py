import sys
input = sys.stdin.readline

A,B = input().split()

cnt = 1
temp = False
while int(A) <= int(B):
    cnt += 1
    
    if B[-1] == '1':
        B = B[:-1]
    elif int(B) % 2 == 0:
        B = str(int(B)//2)
    else:
        break
    
    if B == A:
        temp = True
        break
    
if temp:
    print(cnt)
else:
    print(-1)