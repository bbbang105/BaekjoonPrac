import sys
input = sys.stdin.readline

N = int(input())
i = 665              # 숫자
c = 0                # 몇 번째인지를 파악

while True:
    i += 1
    number = list(map(int,str(i))) # 리스트로 분배
    
    for j in range(len(number)-2): # 3칸씩 브루트포스
        if number[j] == 6 and number[j+1] == 6 and number[j+2] == 6:
            c += 1
            break                  # 6666과 같은 숫자를 위해 중복 방지
    
    if c == N:
        print(i)
        break