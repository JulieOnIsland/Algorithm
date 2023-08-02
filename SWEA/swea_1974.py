
'''
Check whether this is a proper Sudoku
'''
def checkRow(value, pos, bo):
    for i in range(len(bo[0])):
        if value == bo[pos[0]][i] and i != pos[1]:
            return False
    return True

def checkCol(value, pos, bo):
    for i in range(len(bo)):
        if value == bo[i][pos[1]] and i != pos[0]:
            return False
    return True

def checkBox(value, pos, bo):
    pos_row = pos[0] // 3    # int
    pos_col = pos[1] // 3    # int
    for i in range(pos_row * 3, pos_row * 3 + 3):
        for j in range(pos_col * 3, pos_col * 3 + 3):
            if value == bo[i][j] and i != pos[0] and j != pos[1]:
                return False
    return True

def solve(bo):
    for i in range(len(bo)): # row
        for j in range(len(bo[0])): # col
            current_value = bo[i][j]
            current_pos = [i, j]
            if (checkRow(current_value, current_pos, bo) == False) or \
                (checkCol(current_value, current_pos, bo) == False) or \
                (checkBox(current_value, current_pos, bo) == False):
                return 0
    return 1

T = int(input())
for idx in range(1, T+1):
    board = []
    for _ in range(9):
        row = list(map(int, input().split()))
        board.append(row)

    print(f'#{idx} {solve(board)}')



