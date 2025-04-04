# factorial 메소드 생성
def factorial(x):
    num = 1
    for i in range(1,x+1):
        num *= i
    return num

for _ in range(int(input())):
    n,m = map(int,input().split())
    # 조합 공식 사용
    bridge = factorial(m) // (factorial(n) * factorial(m-n)) 
    print(bridge)
