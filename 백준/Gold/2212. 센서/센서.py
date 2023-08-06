import sys
input = sys.stdin.readline

N = int(input())
K = int(input())
# 집중국의 개수가 같거나 더 많은 경우
if K >= N:
    print(0)
    exit(0)
# 가까운 위치별로 정렬    
sensor = list(map(int,input().split()))
sensor = list(set(sensor))
sensor.sort()
# 센서 간 차이 파악
gaps = []
for i in range(1,len(sensor)):
    gap = abs(sensor[i] - sensor[i-1])
    gaps.append(gap)
# 가까운 거리부터 추가
gaps.sort(reverse=True)    
res = sum(gaps[K-1:])
print(res)