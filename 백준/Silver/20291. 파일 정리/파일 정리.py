import sys
input = sys.stdin.readline

dct = dict()
for _ in range(int(input())):
    file = input().rstrip().split('.')  # .을 기준으로 구분
    if file[1] in dct:                  # 이미 나온 확장자일 경우
        dct[file[1]] += 1               
    else:                               # 처음 나온 확장자일 경우
        dct[file[1]] = 1               
        
files = sorted(dct)                     # 확장자명의 사전순으로 정렬
for file in files:                      # 하나씩 확장자명과 개수를 출력
    print(file, dct[file])