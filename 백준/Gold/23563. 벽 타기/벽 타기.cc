#include<bits/stdc++.h>

using namespace std;
using ll = long long;

struct info {
	int x, y, w;
	bool operator<(const info& cur) const {
		return w > cur.w;
	}
};

int n, m;
string board[510];
int memo[510][510];
int dx[] = { -1,1,0,0 }, dy[] = { 0,0,-1,1 };

void solve();
int djik(int sx, int sy, int ex, int ey);
int check_wall(int x, int y);

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int t = 1;
	while (t--)solve();
	return 0;
}
void solve()
{
	int sx, sy, ex, ey;
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> board[i];
		for (int j = 0; j < m; j++) {
			if (board[i][j] == 'S')
				sx = i, sy = j;
			else if (board[i][j] == 'E')
				ex = i, ey = j;
		}
	}
	cout << djik(sx, sy, ex, ey);
}
int djik(int sx, int sy, int ex, int ey)
{
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			memo[i][j] = (int)1e9;

	priority_queue<info> pq;
	pq.push({ sx,sy,0 });
	memo[sx][sy] = 0;

	while (!pq.empty())
	{
		info cur = pq.top();
		pq.pop();

		if (memo[cur.x][cur.y] < cur.w)continue;

		int cflag = check_wall(cur.x, cur.y);
		for (int i = 0; i < 4; i++) {
			int tx = cur.x + dx[i];
			int ty = cur.y + dy[i];

			if (tx <= 0 || tx >= n - 1 || ty <= 0 || ty >= m - 1 || board[tx][ty]=='#')continue;

			int tflag = check_wall(tx, ty);

			if (cflag && tflag) {
				if (memo[tx][ty] > memo[cur.x][cur.y]) {
					memo[tx][ty] = memo[cur.x][cur.y];
					pq.push({ tx,ty,memo[tx][ty] });
				}
			}
			else {
				if (memo[tx][ty] > memo[cur.x][cur.y] + 1) {
					memo[tx][ty] = memo[cur.x][cur.y] + 1;
					pq.push({ tx,ty,memo[tx][ty] });
				}
			}
		}
	}
	return memo[ex][ey];
}
int check_wall(int x, int y)
{
	int cnt = 0;
	for (int i = 0; i < 4; i++) {
		if (board[x + dx[i]][y + dy[i]] == '#')
			cnt++;
	}
	return cnt > 0 ? 1 : 0;
}