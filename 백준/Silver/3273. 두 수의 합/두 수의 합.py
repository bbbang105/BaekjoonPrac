import sys
input = sys.stdin.readline

def BinarySearch(start):
    global cnt, lst
    index = len(lst)-1
    while start != lst[index]:
        sumNum = start + lst[index]
        if sumNum == goal:
            cnt += 1
            lst = lst[:index]
            break
        elif sumNum < goal:
            break
        index -= 1
    return
            
if __name__ == '__main__':
    N = int(input())
    lst = sorted(list(map(int,input().rstrip().split())))
    goal = int(input())
    
    cnt = 0
    for i in lst:
        if i not in lst:
            break
        BinarySearch(i)
        
    print(cnt)