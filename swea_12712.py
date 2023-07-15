'''
Handling Descartes Coordinate
'''

def plusShape(board, M):
    spray_power = M - 1
    board_coordinate = []
    total_kill = 0
    temp_kill = 0
    for i in range(len(board)):
        for j in range(len(board)):
            center = [i, j]
            board_coordinate.append(center)
            for power in range(1, spray_power + 1):
                if center[0] - power < 0:
                    continue
                else:
                    board_coordinate.append([center[0] - power, center[1]])

            for power in range(1, spray_power + 1):
                if center[0] + power > (len(board) - 1):
                    continue
                else:
                    board_coordinate.append([center[0] + power, center[1]])

            for power in range(1, spray_power + 1):
                if center[1] - power < 0:
                    continue
                else:
                    board_coordinate.append([center[0], center[1] - power])

            for power in range(1, spray_power + 1):
                if center[1] + power > (len(board) - 1):
                    continue
                else:
                    board_coordinate.append([center[0], center[1] + power])

            for coordinate in board_coordinate:
                temp_kill += board[coordinate[0]][coordinate[1]]

            if temp_kill > total_kill:
                total_kill = temp_kill

            temp_kill = 0
            board_coordinate = []

    return total_kill

def xShape(board, M):
    spray_power = M - 1
    board_coordinate = []
    total_kill = 0
    temp_kill = 0
    for i in range(len(board)):
        for j in range(len(board)):
            center = [i, j]
            board_coordinate.append(center)

            for power in range(1, spray_power + 1):  # check upper right
                if (center[0] - power < 0) or (center[1] + power > (len(board) - 1)):
                    continue
                else:
                    board_coordinate.append([center[0] - power, center[1] + power])

            for power in range(1, spray_power + 1):  # check lower right
                if (center[0] + power > (len(board) - 1)) or (center[1] + power > (len(board) - 1)):
                    continue
                else:
                    board_coordinate.append([center[0] + power, center[1] + power])

            for power in range(1, spray_power + 1):  # check upper left
                if (center[0] - power < 0) or (center[1] - power < 0):
                    continue
                else:
                    board_coordinate.append([center[0] - power, center[1] - power])

            for power in range(1, spray_power + 1):  # check lower left
                if (center[0] + power > (len(board) - 1)) or (center[1] - power < 0):
                    continue
                else:
                    board_coordinate.append([center[0] + power, center[1] - power])

            for coordinate in board_coordinate:
                temp_kill += board[coordinate[0]][coordinate[1]]

            if temp_kill > total_kill:
                total_kill = temp_kill

            temp_kill = 0
            board_coordinate = []

    return total_kill

# -----------------------------------------------------------------------------
T = int(input())
for idx in range(1, T+1):
    N, M = map(int, input().split())
    board = []
    for i in range(N):
        row = list(map(int, input().split()))
        board.append(row)

    total_kill_plus = plusShape(board, M)
    total_kill_x = xShape(board, M)
    total_kill_final = max(total_kill_plus, total_kill_x)

    print(f'#{idx} {total_kill_final}')




