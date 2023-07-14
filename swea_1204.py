
'''
SWEA 1204 최빈수 구하기
'''

# 1st solution using functions (Success)
def findMax(numList):
    maxValue = 0
    maxPos = 0
    for i in range(len(numList)):
        if numList[i] < maxValue:
            continue
        else:
            maxPos = i
            maxValue = numList[i]
    return maxPos

def findMode(numList):
    freq = [0] * 101 # 0 ~ 100
    for i in range(len(numList)):
        freq[numList[i]] += 1
    return freq

for T in range(int(input())):
    idx = int(input())
    numList = list(map(int, input().split()))
    freq = findMode(numList)
    pos = findMax(freq)

    print(f'#{idx} {pos}')


# 2nd solution w/o function (Fail)
T = int(input())

for i in range(T):
    idx = int(input())
    numbers = list(map(int, input().split()))
    freq = [0] * 101
    for i in range(len(numbers)):
        freq[numbers[i]] += 1
    # print(freq)

    maxValue = 0
    maxPos = 0
    for i in range(len(numbers)):
        if freq[i] < maxValue:
            continue
        else:
            maxPos = i
            maxValue = freq[i]

    print(f'#{idx} {maxPos}')




