
"""
Input:
TestCase
N
String

ex)
3
6
S A S S W S
S W A S C S
S W S W S S
S W S S W S
S B S S W S
S S S S S S
3
S S A
B W W
S W C
10
C S S S S S S S S C
S W S S S S S S W S
S S W S S S S W S S
A S S W S S W S S A
S S S S S B S S S S
S S S S B S S S S S
A S S W S S W S S A
S S W S S S S W S S
S W S S S S S S W S
C S S S S S S S S C

Output:
#1 9
#2 0
#3 62

"""
def makeGrid(n):
    grid = []
    for i in range(n):
        row = list(input().split())
        grid.append(row)
    return grid

# A는 오른쪽으로만 이동 가능, B는 좌우로 이동가능, C는 상하좌우로 이동가능
# 다른 로봇이 있던 초기 위치를 넘어가지 못함. 벽을 만나면 더 이상 이동 못 함.

test_case = int(input())

for idx in range(1, test_case + 1):
    n = int(input())
    distance = 0

    # Make a grid
    grid = makeGrid(n)

    for i in range(n):
        for j in range(n):

            if grid[i][j] == 'A':
                for k in range(j + 1, len(grid)):
                    if grid[i][k] == 'W' or grid[i][k] == 'A' or grid[i][k] == 'B' or grid[i][k] == 'C':
                        break
                    else:
                        distance += 1

            elif grid[i][j] == 'B':
                # Check right
                for k in range(j + 1, len(grid)):
                    if grid[i][k] == 'W' or grid[i][k] == 'A' or grid[i][k] == 'B' or grid[i][k] == 'C':
                        break
                    else:
                        distance += 1

                # Check left
                for k in range(1, j + 1):
                    if grid[i][j - k] == 'W' or grid[i][j - k] == 'A' or grid[i][j - k] == 'B' or grid[i][j - k] == 'C':
                        break
                    else:
                        distance += 1

            elif grid[i][j] == 'C':
                # Check right
                for k in range(j + 1, len(grid)):
                    if grid[i][k] == 'W' or grid[i][k] == 'A' or grid[i][k] == 'B' or grid[i][k] == 'C':
                        break
                    else:
                        distance += 1
                # Check left
                for k in range(1, j + 1):
                    if grid[i][j - k] == 'W' or grid[i][j - k] == 'A' or grid[i][j - k] == 'B' or grid[i][j - k] == 'C':
                        break
                    else:
                        distance += 1
                # Check down
                for k in range(i + 1, len(grid)):
                    if grid[k][j] == 'W' or grid[k][j] == 'A' or grid[k][j] == 'B' or grid[k][j] == 'C':
                        break
                    else:
                        distance += 1
                # Check up
                for k in range(1, i):
                    if grid[i - k][j] == 'W' or grid[i - k][j] == 'A' or grid[i - k][j] == 'B' or grid[i - k][j] == 'C':
                        break
                    else:
                        distance += 1

    print(f'#{idx} {distance}')








