import sys
input = sys.stdin.readline
dct = dict()

cnt = 0
while True:
        wood = input().rstrip()
        if not wood:        # 입력이 없으면
            break
        cnt += 1            # 몇 개나 입력 받았는지 확이
        if wood in dct:     # dct에 이미 있는 종인 경우
            dct[wood] += 1
        else:               # 처음 만나는 종인 경우
            dct[wood] = 1
        
names = sorted(list(dct.keys()), reverse = False) # 이름 사전순 정렬

for name in names:
    rate = (dct[name]/cnt*100)  # 비율 계산
    print(f'{name} {rate:.4f}') # 소수점 넷째자리까지 반올림으로 출력