import sys, math
input = sys.stdin.readline

for _ in range(int(input())):
    x1,y1,r1,x2,y2,r2 = map(int,input().split())
    
    distance = math.sqrt((x2-x1)**2 + (y2-y1)**2) # 두 원 중심 사이의 거리 계산
    # 두 원의 중심이 같은 경우
    if distance == 0:
        if r1 == r2:  # 두 원의 크기도 같아 아예 겹치는 경우
            print(-1)
        else:         # 한 원이 다른 원 안에 들어가 있는 경우
            print(0)
    # 두 원의 중심이 다른 경우        
    else:
        if r1+r2 == distance or abs(r2-r1) == distance:     # 내접, 외접하는 경우 (접점이 1개)
            print(1)
        elif r1+r2 > distance and abs(r2-r1) < distance:    # 접점이 2개인 경우
            print(2)
        else:
            print(0)