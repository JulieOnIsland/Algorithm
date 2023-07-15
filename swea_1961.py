'''
Rotate the number array: 90, 180, 270 degrees
(input)
1 2 3
4 5 6
7 8 9

(output)
741 987 369
852 654 258
963 321 147
'''

def rotate(board):
    new_board = []
    temp = ''
    for i in range(len(board)):
        for j in range(len(board)):
            temp += str(board[(len(board)-1) - j][i])
        new_board.append(temp)
        temp = ''
    new_board = [list(i) for i in new_board]
    return new_board

T = int(input())
for idx in range(1, T+1):
    N = int(input())
    board = []
    for i in range(N):
        row = list(map(int, input().split()))
        board.append(row)
    ninety = rotate(board)  # 90 degrees
    eighty = rotate(ninety)  # 180 degrees
    seventy = rotate(eighty)  # 270 degrees
    print(f'#{idx}')

    for i in range(len(board)):
        print("".join(ninety[i]), end=" ")
        print("".join(eighty[i]), end=" ")
        print("".join(seventy[i]), end=" ")
        print()