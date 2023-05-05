import sys
input = sys.stdin.readline

dct = dict()
for _  in range(int(input())):
    book = input().rstrip()
    if book in dct:
        dct[book] += 1
    else:
        dct[book] = 1

mn = max(dct.values())
c = list(dct.values()).count(mn)
sorted_lst = list(sorted(dct.items(), key = lambda item : (item[1],item[0]), reverse = True))
print(sorted_lst[c-1][0])
