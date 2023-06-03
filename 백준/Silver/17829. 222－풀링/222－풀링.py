import sys
input = sys.stdin.readline

n = int(input())
graph = [list(map(int,input().split())) for _ in range(n)]

def DAC(x,y,n):
    if n == 2:
        lst = []
        for i in range(x,x+n):
            for j in range(y,y+n):
                lst.append(graph[i][j])
                
        return sorted(lst, reverse = True)[1]
    
    one = DAC(x, y, n//2)
    two = DAC(x, y+n//2, n//2)
    three = DAC(x+n//2, y, n//2)
    four = DAC(x+n//2, y+n//2, n//2)
    
    return sorted([one, two, three, four], reverse = True)[1]
    
print(DAC(0,0,n))