import sys
input = sys.stdin.readline

def sinbal(p1,p2,p3):
    A = p1[0]*p2[1] + p2[0]*p3[1] + p3[0]*p1[1]
    B = p1[1]*p2[0] + p2[1]*p3[0] + p3[1]*p1[0]
    return A-B

def ccw(a,b,c,d):
    first = sinbal(a,b,c)*sinbal(a,b,d)
    second = sinbal(c,d,a)*sinbal(c,d,b)

    if first < 0 and second < 0:
        return 1
    else:
        return 0

a = list(map(int,input().split()))
fx,fy,sx,sy = a[:2],a[2:4],a[4:6],a[6:]
print(ccw(fx,fy,sx,sy))