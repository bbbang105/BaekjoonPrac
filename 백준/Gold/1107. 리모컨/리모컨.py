from itertools import product
import sys
input = sys.stdin.readline

go_num = input().rstrip()
out_cnt = int(input())
if out_cnt == 0: 
    result = min(len(go_num), abs(int(go_num) - 100))
    print(result)
    exit(0)
elif out_cnt == 10:
    a = set(input().rstrip().split())
    print(abs(int(go_num) - 100))
    exit(0)

out_lst = set(input().rstrip().split())
lst = set(['0','1','2','3','4','5','6','7','8','9'])
lst -= out_lst
up_down = abs(int(go_num) - 100)
minNum = sys.maxsize

if '0' not in lst and len(go_num) > 1:
    for i in product(lst,repeat=len(go_num)-1):
        num = ''
        for j in i:
            num += j
        
        diff = abs(int(go_num) - int(num))
        click = len(str(int(num)))
        diff += click
        if minNum > diff:
            minNum = diff
    
for i in product(lst,repeat=len(go_num)):
    num = ''
    for j in i:
        num += j
      
    diff = abs(int(go_num) - int(num))
    click = len(str(int(num)))
    diff += click
    if minNum > diff:
        minNum = diff

for i in product(lst,repeat=len(go_num)+1):
    num = ''
    for j in i:
        num += j
      
    diff = abs(int(go_num) - int(num))
    click = len(str(int(num)))
    diff += click
    if minNum > diff:
        minNum = diff

result = min(minNum, up_down)
print(result)