import sys
input = sys.stdin.readline

N = int(input()) # 도시의 수
distance = list(map(int,input().split())) # 각 도시 사이의 거리
price = list(map(int,input().split())) # 각 도시마다의 리터당 가격
price = price[:-1]

i = 0
totalPrice = (price[i] * distance[i])
cheapPrice = price[i]
while True: 
    
    if cheapPrice < price[i+1]:
        totalPrice += (cheapPrice * distance[i+1])
    else:
        cheapPrice = price[i+1]
        totalPrice += (cheapPrice * distance[i+1])
    i += 1
    
    if i == N-2:
        break
        
print(totalPrice)