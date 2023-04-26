from collections import Counter
import sys
input = sys.stdin.readline

N = []
n = int(input())
for _ in range(n):
    N.append(int(input()))
    
mean = sum(N)/len(N)            # 산술평균
middle = sorted(N)[int(n/2)]    # 중앙값

c = Counter(N)                  # 최빈값
order = c.most_common()
maximum = order[0][1]
fres = []
for i in order:
    if i[1] == maximum:
        fres.append(i[0])

fres.sort()
if len(fres) == 1:
    frequency = fres[0]
else:
    frequency = fres[1]

extent = max(N) - min(N)        # 범위

print(round(mean))
print(middle)
print(frequency)
print(extent)