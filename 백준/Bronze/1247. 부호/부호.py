cnt = 0
while cnt < 3:
    res = 0
    N = int(input())
    for _ in range(N):
        number = int(input())
        res += number
    if res > 0:
        print("+")
    elif res < 0:
        print("-")
    else:
        print("0")

    cnt += 1