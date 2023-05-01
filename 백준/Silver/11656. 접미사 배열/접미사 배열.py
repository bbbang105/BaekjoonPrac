import sys
input = sys.stdin.readline

W = input().rstrip()
lst = []

for i in range(len(W)):
    lst.append(W[i:])
print(*sorted(lst),sep='\n')