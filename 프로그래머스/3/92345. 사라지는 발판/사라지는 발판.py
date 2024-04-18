def dfs(board, cnt, t, aloc, bloc, l_r, l_c):
    # t == 1 => A, t == -1 => B
    t_x, t_y = aloc if t == 1 else bloc    
    if board[t_x][t_y] == 0 : return t , cnt

    res_cnt = cnt # 결과 이동 횟수
    defeated = t # 진 사람 저장
    flag = 1 # 미래에 이기는 경우가 있으면 -1 아니면 1
    
    board[t_x][t_y] = 0
    for x,y in [[0,1],[0,-1],[-1,0],[1,0]] :
        next_x, next_y = t_x + x, t_y + y
        if next_x == -1 or next_x == l_r or \
            next_y == -1 or next_y == l_c or board[next_x][next_y] == 0 : continue # 갈 수 없는 곳 check
        
        # 다음 순서
        if t == 1 : i_defeated, i_cnt = dfs(board, cnt+1, -t, [next_x,next_y], bloc, l_r, l_c)
        else : i_defeated, i_cnt = dfs(board, cnt+1, -t, aloc, [next_x,next_y], l_r, l_c)
        
        # 결과
        if i_defeated == t :
            # 졌다면 이긴 적이 있나 확인 후 이긴 적이 없으면 가장 길게 도망친 횟수 저장
            if flag == 1 : res_cnt = max(res_cnt,i_cnt)
        else:
            # 이겼다면 가장 적게 이동한 횟수 저장
            if flag == 1 : 
                flag = -1
                res_cnt = i_cnt
            res_cnt = min(res_cnt,i_cnt)
    board[t_x][t_y] = 1
        
    return defeated * flag, res_cnt

def solution(board, aloc, bloc):
    return dfs(board, 0, 1, aloc, bloc, len(board), len(board[0]))[1]