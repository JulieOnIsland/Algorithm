def solution(X, Y):
    answer = ''
    x = [0 for i in range(10)]  # 0 ~ 9까지의 숫자가 몇 번씩 나왔는지 세는 리스트
    y = [0 for i in range(10)]

    for x_value in X:
        x[int(x_value)] += 1

    for y_value in Y:
        y[int(y_value)] += 1

    common = [0 for i in range(10)]

    for i in range(10):
        common[i] = min(x[i], y[i])

    for j in range(9, -1, -1):  # 9, 8, ..., 0
        if common[j] > 0:
            answer += str(j) * common[j]

    if answer == "":
        answer = "-1"
    elif answer[0] == "0":
        answer = "0"

    return answer