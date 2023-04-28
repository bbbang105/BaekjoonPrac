import sys
input = sys.stdin.readline

PKNum,need = map(int,input().split())

PKdct1 = dict()                 # 번호가 key인 dict
PKdct2 = dict()                 # 이름이 key인 dict
for i in range(1,PKNum+1):      # 1부터 번호 할당
    name = input().rstrip()
    PKdct1[i] = name
    PKdct2[name] = i
    
for j in range(need):
    a = input().rstrip()
    if a.isalpha():             # 이름을 입력받을 경우
        print(PKdct2[a])
    else:                       # 번호를 입력받을 경우
        print(PKdct1[int(a)])