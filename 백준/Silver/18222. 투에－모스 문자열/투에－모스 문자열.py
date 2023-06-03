import sys
input = sys.stdin.readline 

# 입력
k = int(input())

def Thue_Morse(x):
    if x == 0:
        return 0
    elif x == 1:
        return 1
    elif x % 2 == 0:
        return Thue_Morse(x//2)
    else:
        return 1 - Thue_Morse(x//2)
    
print(Thue_Morse(k-1))