from itertools import permutations
import sys
input = sys.stdin.readline

N = int(input())
nums = list(map(int,input().split()))
cals = list(map(int,input().split()))

lst = []
for i in range(4):
    if i == 0:
        for j in range(cals[i]):
            lst.append('+')
    elif i == 1:
        for j in range(cals[i]):
            lst.append('-')
    elif i == 2:
        for j in range(cals[i]):
            lst.append('*')
    else:
        for j in range(cals[i]):
            lst.append('/')
  
permu = set(list(permutations(lst)))   

maxNum, minNum = -1000000001,1000000001
for p in permu:
    num = nums[0]
    i = 1
    for j in p:
        if j == '+':
            num += nums[i]
        elif j == '-':
            num -= nums[i]
        elif j == '*':
            num *= nums[i]
        else:
            if num < 0:
                part = (-num) // nums[i]
                num = (-part)
            else:
                part = num // nums[i]
                num = part
        i += 1
        
    if num > maxNum:
        maxNum = num
    if num < minNum:
        minNum = num
         
print(maxNum,minNum,sep='\n')