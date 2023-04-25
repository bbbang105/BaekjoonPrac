import sys
input = sys.stdin.readline

T = int(input())                     # Test Case 개수
for _ in range(T):
    floor = int(input())
    ho = int(input())
    
    if ho == 1:                      # 1호인 경우에는 무조건 1명
        print(1)
        continue
    else:
        apart = []                   # 새로운 아파트 생성
        apart.append([i for i in range(1,ho+1)])  # 0층을 만들어줌
        for i in range(1, floor+1):  # 새로운 층을 만들어줌
            new_floor = [1]          # 각 층의 1호는 1명
            for j in range(1, ho):   # 2호부터 값을 구하며 넣어줌
                new_floor.append(new_floor[j-1] + apart[i-1][j])
            apart.append(new_floor)  # 새로운 층을 아파트에 넣어줌
            new_floor = []           # 새로운 층 초기화
            
    print(apart[floor][ho-1])       