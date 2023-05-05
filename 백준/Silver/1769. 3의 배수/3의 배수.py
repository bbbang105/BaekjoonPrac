import sys
input = sys.stdin.readline

num = int(input())
cnt = 0
while num >= 10:
    s = 0 
    for i in str(num):
        s += int(i)
    num = s
    cnt += 1
    
if num%3 == 0:
    print(cnt,'YES',sep='\n')
else:
    print(cnt,'NO',sep='\n')