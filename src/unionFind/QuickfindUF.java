package unionFind;

import java.util.Arrays;

/* Quick Find Data Structure:
 * 1.data structre:
	(1) The QuickFindUF class represents a union�Cfind data type, 
 	(2) The union�Cfind data type models connectivity among a set of n sites/objects, named 0 through n�C1.
 * 2. member variable: 
 * 	(1) integer array id[] of length n, 
 * 3. methods:
 *  (1)find:
 *  	get the component identifier
 *  (2)union:
 *     connect q and p, change all entries whose id equals id[p] to id[q]
 *  (2)Connected:
 *		check if p and q are connected->q and p are connected iff id[p] = id[q]  
 * 4.analysis:
 *	(1) time complexity: 
 * 		initialize-> O(N)
 *      Union-> O(N)
 *      find-> O(1)
 *      connected-> O(1)
 *      
 *  (2) Space(memory) complexity:
 *      We creat an array with length N-> O(N)
*/

/*features:
	1.pro: find operation uses O(1) time complexity
	2.cons: Union operation uses o(n) time complexity, Union is too expensive
*/
/*
����Ĵ����find����ʮ�ָ�Ч����Ϊ������Ҫһ�������ȡ�������ܹ��ҵ��ýڵ����ţ�����������֮������������Ҫ�����·������������漰��������ŵ��޸ģ���Ϊ������ȷ����Щ�ڵ�������Ҫ���޸ģ���˾ͱ��������������б������ҵ���Ҫ�޸ĵĽڵ㣬��һ�޸ģ���һ��ÿ�������·�������ĸ��ӶȾ������Թ�ϵ�ˣ����Ҫ��ӵ���·����������M���ڵ�������N����ô����ʱ�临�ӶȾ���MN����Ȼ��һ��ƽ���׵ĸ��Ӷȣ����ڴ��ģ�����ݶ��ԣ�ƽ���׵��㷨�Ǵ�������ģ���������£�ÿ�������·�����ǡ�ǣһ������ȫ������Ҫ���������⣬�ؼ�����Ҫ���union������Ч�ʣ�����������Ҫ�����������顣
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//create a class to hold the data and methods
public class QuickfindUF {
	private int[] id;
	private int count; 
	
	//constructor to create the array, initialize N sites(objects) with integer names 0 to n-1
	public QuickfindUF(int n) {
		id = new int[n];
		count = n;
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	
	public int find(int p) {
		return id[p];
	}

	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		if (pid != qid) {
			for (int i = 0; i < id.length; i++) {
				if (id[i] == pid) {
					id[i] = qid;
				}
			}
		}	
		count--;
	}
	
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n =6;
		QuickfindUF qf = new QuickfindUF(n);
		qf.union(0, 3);
		qf.union(1, 2);
		qf.union(5, 2);
		System.out.println(Arrays.toString(qf.id));
		System.out.println(qf.count);	
	}

}


