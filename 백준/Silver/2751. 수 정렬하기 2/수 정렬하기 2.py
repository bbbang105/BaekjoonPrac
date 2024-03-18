import sys
input = sys.stdin.readline

num_lst = []
for _ in range(int(input())):
    num_lst.append(int(input()))

for i in sorted(num_lst):
    print(i)