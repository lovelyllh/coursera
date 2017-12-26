package unionFind;

import java.util.Arrays;

/* Quick Find Data Structure:
 * 1.data structre:
	(1) The QuickFindUF class represents a union–find data type, 
 	(2) The union–find data type models connectivity among a set of n sites/objects, named 0 through n–1.
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
下面的代码的find方法十分高效，因为仅仅需要一次数组读取操作就能够找到该节点的组号，但是问题随之而来，对于需要添加新路径的情况，就涉及到对于组号的修改，因为并不能确定哪些节点的组号需要被修改，因此就必须对整个数组进行遍历，找到需要修改的节点，逐一修改，这一下每次添加新路径带来的复杂度就是线性关系了，如果要添加的新路径的数量是M，节点数量是N，那么最后的时间复杂度就是MN，显然是一个平方阶的复杂度，对于大规模的数据而言，平方阶的算法是存在问题的，这种情况下，每次添加新路径就是“牵一发而动全身”，想要解决这个问题，关键就是要提高union方法的效率，让它不再需要遍历整个数组。
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


