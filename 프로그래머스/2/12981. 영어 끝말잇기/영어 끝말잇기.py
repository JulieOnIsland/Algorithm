def solution(n, words):
    pointer = 1
    turn = 1
    round_num = 1
    flag = True
    answer = []  # 번호, 차례
    already_used = set()
    already_used.add(words[0])
    while pointer < len(words) and flag:
        # logic
        turn = (turn + 1) % n
        if turn == 1:
            round_num += 1
        if pointer != len(words) - 1:
            last = words[pointer - 1][-1]
            first = words[pointer][0]
            if last == first:  # 끝말잇기 성공
                # already_used에 있는지 체크
                if words[pointer] in already_used:
                    flag = False
                    if turn % n == 0:
                        answer.append(n)
                    else:
                        answer.append(turn)
                    answer.append(round_num)
                else:
                    already_used.add(words[pointer])
            else:  # 끝말잇기 실패
                flag = False
                if turn % n == 0:
                    answer.append(n)
                else:
                    answer.append(turn)
                answer.append(round_num)

        if pointer == len(words) - 1:
            if words[pointer] in already_used:
                if turn % n == 0:
                    answer.append(n)
                else:
                    answer.append(turn)
                answer.append(round_num)
            else:
                answer.append(0)
                answer.append(0)

        pointer += 1

    return answer