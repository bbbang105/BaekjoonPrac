import sys
input = sys.stdin.readline

# 신발끈 공식
def sinbal(x,y):
    xr,yr = 0,0
    for i in range(N):
        xr += x[i]*y[i+1]
        yr += y[i]*x[i+1]
        
    return round(abs((xr-yr)/2),1)
    
N = int(input())
X,Y= [],[]
for _ in range(N):
    a,b = map(int,input().split())
    X.append(a); Y.append(b)
X.append(X[0]); Y.append(Y[0])

print(sinbal(X,Y))