import sys

t = int(sys.stdin.readline())

for _ in range(t):

    dict = {}

    n = int(sys.stdin.readline())

    for _ in range(n):

        # 의상의 이름과 종류 입력
        name, category = sys.stdin.readline().split()

        # 딕셔너리에 해당 의상 종류가 존재할 경우
        if (category in dict):
            dict[category] += 1
        # 딕셔너리에 해당 의상 종류가 존재하지 않을 경우
        else:
            dict[category] = 1

    # 의상 종류에 따른 조합의 개수 계산
    result = 1
    for count in dict.values():
        result *= count + 1
    result -= 1

    print(result)