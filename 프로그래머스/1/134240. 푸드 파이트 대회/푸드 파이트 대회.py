food = [1, 1, 1,1,1,1 ,9,32,2]
answer = ""
food_num = []
for i in range(1, len(food)):
    # print(food[i])
    if food[i] > 1:
        if food[i] % 2 == 0: # even
            food_num.append(int(food[i] / 2))
        else: # odd
            food_num.append(int((food[i]-1) / 2))
    else:
        food_num.append(0)

print(food_num)

for i in range(len(food_num)):
    val = str(i+1)
    if food_num[i] != 0:
        answer += val * food_num[i]

answer += "0"


for i in range(len(food_num)):
    val = str(len(food_num) - i)
    if food_num[len(food_num) -1] != 0:
        answer += val * food_num[len(food_num) -1 - i]
print(answer)




def solution(food):
    answer = ''
    food_num = []

    for i in range(1, len(food)):
        # print(food[i])
        if food[i] > 1:
            if food[i] % 2 == 0:  # even
                food_num.append(food[i] // 2)
            else:  # odd
                food_num.append((food[i] - 1) // 2)
        else:
            food_num.append(0)

    for i in range(len(food_num)):
        val = str(i + 1)
        if food_num[i] != 0:
            answer += val * food_num[i]

    answer += "0"

    for i in range(len(food_num)):
        val = str(len(food_num) - i)
        if food_num[len(food_num) -1 -i] != 0: # 오답 부분
            answer += val * food_num[len(food_num) - 1 - i]

    return answer