def solution(keymap, targets):
    answer = []
    key_dict = dict()

    for i in range(len(keymap)):
        for idx, key in enumerate(keymap[i]):
            # print(idx, key)
            if key_dict.get(key) is None:  # 딕셔너리에 없다면 저장
                key_dict[key] = idx + 1
            else:  # 딕셔너리에 있다면 현재값과 비교 후 현재값이 작으면 값 교체
                original = key_dict.get(key)
                new = idx + 1
                if original > new:
                    key_dict[key] = new

    for target in targets:  # target = "ABCD"
        key_sum = 0
        flag = True
        for j in target:  # j = "A"
            if key_dict.get(j) is None:
                answer.append(-1)
                flag = False
                break
            else:
                key_sum += key_dict.get(j)

        if flag is True: # flag가 True일 때만 추가해야 함
            answer.append(key_sum)

    return answer