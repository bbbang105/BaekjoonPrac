import sys
input = sys.stdin.readline

def Primenum(x):
    temp = True
    for i in range(2,x//2+1):
        if x % i == 0:
            temp = False
            break
    return temp 

lst = [2,3,5,7,11]
for _ in range(int(input())):
    num = int(input().rstrip())
    if num > lst[-1]:
        for i in range(lst[-1],num+1):
            if Primenum(i):
                lst.append(i)
    answer= num//2            
    while True:
        if answer in lst and num-answer in lst:
            print(answer, num-answer)
            break
        else:
            answer -= 1