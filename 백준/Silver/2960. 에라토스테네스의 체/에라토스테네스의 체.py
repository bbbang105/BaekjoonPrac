import sys
input = sys.stdin.readline

N,K = map(int,input().split())

numbers = []            # 2부터 N까지 숫자들을 저장할 list
for i in range(2,N+1):
    numbers.append(i)
    
cnt = 0                 # 몇 번째 지우는 수인지 count
while True:
    P = numbers[0]
    multi = 1           # 배수
    temp = False        # K번째 지워진 수가 나오면 True로 변경
    while True:
        num = (P*multi) # 배수를 곱해서 제거할 숫자를 할당
        if num > N:     # N보다 값이 커지는 경우
            break
        elif num not in numbers: # 이미 지워져 리스트에 없는 숫자일 경우
            pass
        else:           # 지우는 경우
            numbers.remove(num)
            cnt += 1
        multi += 1
        if cnt == K:    # K번째 지워진 숫자일 경우
            temp = True
            break
        
    if temp:            # k번째 지워진 수 출력
        print(num)
        break    