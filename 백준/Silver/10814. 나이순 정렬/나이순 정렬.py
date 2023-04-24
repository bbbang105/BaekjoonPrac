import sys
input = sys.stdin.readline

arr = []
for i in range(1, int(input())+1):       # i를 입력 받은 순서로 사용
    age, name = input().rstrip().split()
    arr.append([int(age), name, i])      # arr[2]에 입력 받은 순서를 넣어줌

arr.sort(key = lambda x : (x[0], x[2]))  # age로 오름차순 정렬, 같으면 i로 오름차순 정렬

for x,y,z in arr:                        # 3개의 인자 중 age와 name만 출력
    print(x,y)