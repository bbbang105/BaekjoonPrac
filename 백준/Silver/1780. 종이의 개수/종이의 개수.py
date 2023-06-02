import sys
input = sys.stdin.readline

def DAC(x,y,n):
    global minusPaper, zeroPaper, onePaper
    first = papers[x][y]
    
    for i in range(x,x+n):
        for j in range(y,y+n):
            if papers[i][j] != first:
                for k in range(3):
                    for l in range(3):
                        DAC(x+k*n//3, y+l*n//3, n//3)
                return
            
    if first == -1:
        minusPaper += 1
    elif first == 0:
        zeroPaper += 1
    else:
        onePaper += 1

N = int(input())
papers = [list(map(int,input().split())) for _ in range(N)]
minusPaper, zeroPaper, onePaper = 0, 0, 0
DAC(0,0,N)
print(minusPaper, zeroPaper, onePaper, sep='\n')