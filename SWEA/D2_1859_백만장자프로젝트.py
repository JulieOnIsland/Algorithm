'''
SWEA 1859 백만 장자 프로젝트

역발상!!!
앞에서부터 풀 수가 없음.
가장 최고가에 팔아야 하는데 for문으로 순차적으로 진행하게 되면, 이후에 현재값보다 작은 값이 나올지, 큰값이 나올지 예측할 수 없음.
BUT 뒤에서부터 접근하면 쉽게 해결 가능!

Memory: 251,228kb
Time: 1,260ms
'''


test_case = int(input())

for idx in range(1, test_case+1):
    n = int(input())
    nums = list(map(int, input().split()))

    max_num = nums[-1] # 가장 마지막 숫자를 max 값으로 설정

    buy = [] # 매입할 가격을 담아두는 리스트
    profit = 0
    a = len(nums)-2  # 마지막 인덱스는 len(nums)-1인데 그 앞 인덱스가 필요.
    for i in range(len(nums)-1):  # 뒤에서부터 앞으로 진행해나갈 것, nums[a-i]
        if nums[a-i] < max_num:  # max 값보다 작다면
            buy.append(nums[a-i])  # 매입할 리스트에 저장
        else:  # max 값보다 큰 값이 나타난 경우
            for j in range(len(buy)):  # buy에 저장한 가격을 순차적으로 꺼내서
                temp = max_num - buy[j]
                profit += temp  # profit에 더해준다

            buy = list()  # buy 초기화

            max_num = nums[a-i] # max. num update: a-i번째 숫자를 최댓값으로 변경

    if (len(buy) != 0):  # 만약 리스트가 비어있지 않다면
        for i in range(len(buy)):
            profit += max_num - buy[i]  # 그 숫자들을 profit에 더해준다
        print(f"#{idx} {profit}")
    else:  # 리스트가 비어있다면
        print(f"#{idx} {profit}")  # profit이 최종 결과이므로 출력을 하면 된다
