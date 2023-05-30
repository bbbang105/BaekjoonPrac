import sys
input = sys.stdin.readline
a,b,c = map(int,input().split())

def dac(a,b):
    if b == 1:
        return a % c
    
    if b % 2 == 0:
        return (dac(a,b//2) ** 2) % c
    else:
        return ((dac(a,b//2) ** 2) * a) % c
    
print(dac(a,b))