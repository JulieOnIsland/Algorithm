def solution(cards1, cards2, goal):
    answer = ''
    i = j = k = 0  # cards1, cards2, goal의 인덱스

    while k < len(goal):

        # cards1에 원소가 있을 때
        if i < len(cards1) and goal[k] == cards1[i]:
            k += 1
            i += 1

        # cards2에 원소가 있을 때
        elif j < len(cards2) and goal[k] == cards2[j]:
            k += 1
            j += 1

        else:
            break

    if k == len(goal):
        answer = "Yes"
    else:
        answer = "No"

    return answer