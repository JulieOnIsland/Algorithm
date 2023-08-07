
def prepareInsert(inputList):
    xList = []
    yList = []
    sList = []
    i = 0
    while i < len(inputList):
        if inputList[i] == 'I':
            i += 1
            xList.append(int(inputList[i]))
            i += 1
            yList.append(int(inputList[i]))
            temp = []
            for j in range(int(inputList[i])):
                i += 1
                temp.append(int(inputList[i]))
            sList.append(temp)
            i += 1
    return xList, yList, sList

#################### Main ####################

test_case = 10
for idx in range(1, test_case+1):
    n = int(input())
    original = list(map(int, input().split()))
    repeatNum = int(input())
    inputList = list(input().split())
    xList, yList, sList = prepareInsert(inputList)

    # Make a password
    for i in range(repeatNum):
        x = xList[i]
        y = yList[i]
        s = sList[i]
        for j in range(y):
            original.insert(x+j, s[j])

    print(f'#{idx}', end=' ')
    for i in range(10):
        print(original[i], end=' ')
    print()

###############################################