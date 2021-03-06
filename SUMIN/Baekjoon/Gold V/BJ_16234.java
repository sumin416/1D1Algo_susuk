import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16234 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int answer =0;
		
		while(true) {
			int[][] visited = new int[N][N];
			int visitCnt = 1;
			int maxSumCnt=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j]==0) {
						//BFS로 그래프 탐색
						visited[i][j]=visitCnt;
						Queue<int[]> queue = new LinkedList<>();
						queue.offer(new int[] {i,j});
						int sum=map[i][j];
						int sumCnt = 1;
						while(!queue.isEmpty()) {
							
							int[] cur = queue.poll();
							for (int k = 0; k < dir.length; k++) {
								int X = cur[0]+dir[k][0];
								int Y = cur[1]+dir[k][1];
								
								if(X<0||Y<0||X>=N||Y>=N||visited[X][Y]!=0)	continue;
								int tmp = Math.abs(map[cur[0]][cur[1]]-map[X][Y]);
								if(tmp>=L && tmp<=R) {
									
									queue.offer(new int[] {X,Y});
									visited[X][Y]=visitCnt;
									sum+=map[X][Y];
									++sumCnt;
								}
								
							}
						}
						
						if(sumCnt>1) {
							int chgVal = (int)(sum/sumCnt);
							for (int k = 0; k < N; k++) {
								for (int k2 = 0; k2 < N; k2++) {
									if(visited[k][k2]==visitCnt)	map[k][k2]=chgVal;
								}
							}
							maxSumCnt=Math.max(maxSumCnt, sumCnt);
						}
						++visitCnt;
						
						
					}
				}
			}
			if(maxSumCnt == 0)	break;
			++answer;
		}
		System.out.println(answer);
	}

}
