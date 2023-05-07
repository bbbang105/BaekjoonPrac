import sys
input = sys.stdin.readline

N,M = map(int,input().rstrip().split())

DNAs = []
for _ in range(N):
    DNAs.append(list(input().rstrip()))

DNA = ''                # 최소가 되는 DNA
HD = 0                  # Hamming Distance의 합
for i in range(M):
    dct = dict()
    for j in range(N):
        if DNAs[j][i] in dct:
            dct[DNAs[j][i]] += 1
        else:
            dct[DNAs[j][i]] = 1
    DNA += sorted(dct.items(), key = lambda x : (-x[1],x[0]), reverse = False)[0][0]
    HD += sum(sorted(list(dct.values()), reverse = True)[1:])
            
print(DNA,HD,sep = '\n')