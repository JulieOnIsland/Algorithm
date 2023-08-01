
test_case = int(input())

for idx in range(1, test_case+1):

    n = int(input())

    # Make a grid
    grid = []
    for i in range(n):
        temp = list(input())
        grid.append(temp)

    sum = 0
    mid = len(grid) // 2 # midpoint

    for i in range(len(grid)):
        if i <= mid:    # before and including midpoint
            for j in range(mid-i, mid+i+1):
                sum += int(grid[i][j])
        else:           # after midpoint
            diff = len(grid) - 1 - i
            for j in range(mid-diff, mid+diff+1):
                sum += int(grid[i][j])

    print(f'#{idx} {sum}')














