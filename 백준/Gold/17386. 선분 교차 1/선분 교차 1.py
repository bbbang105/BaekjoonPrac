import sys
input = sys.stdin.readline

# 사선 공식
def sinbal(p1,p2,p3):
    A = p1[0]*p2[1] + p2[0]*p3[1] + p3[0]*p1[1]
    B = p1[1]*p2[0] + p2[1]*p3[0] + p3[1]*p1[0]
    return A-B
# CCW
def ccw(a,b):
    fx,fy,sx,sy = a[:2],a[2:],b[:2],b[2:]
    # (A,B,C)*(A,B,D)
    first = sinbal(fx,fy,sx)*sinbal(fx,fy,sy)
    # (C,D,A)*(C,D,B)
    second = sinbal(sx,sy,fx)*sinbal(sx,sy,fy)
    # 네 점이 한 직선에 있는 경우
    if first == 0 and second == 0:
        # 비교를 위해 x좌표에 더 작은 값을 넣어줌
        if fx > fy:
            fx,fy = fy,fx
        if sx > sy:
            sx,sy = sy,sx
        # 한 점의 큰 값이 다른 점의 작은 값보다 큰 경우
        if fx <= sy and sx <= fy:
            return True   
    else:
        # 교차하는 경우
        if first <= 0 and second <= 0:
            return True
        # 교차하지 않는 경우
        else:
            return False
        
# one = [A,B] , two = [C,D]
one = list(map(int,input().split()))
two = list(map(int,input().split()))
possible = ccw(one,two)
if possible:
    print(1)
else:
    print(0)