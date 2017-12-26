package unionFind;

import java.util.Arrays;

/*Quck Union Data Structure:
 * 1.data structre:
		(1) The QuickUnionUF class represents a union¨Cfind data type, 
 		(2) The union¨Cfind data type models connectivity among a set of n sites/objects, named 0 through n¨C1.
 * 2. member variable:
 *    integer array id[] with length n; i is the child of id[i]; 
 * 3. methods:
 *	(1)find:
 *      find the component identifier(root) for the current object 
 *  (2)union:
 *     	connect p and q, set the id of p's root to the id of q's root.
 *  (3)Connected:
 *		check if p and q are connected->q and p are connected iff id[p] = id[q]  	
 *  (4)count:
 *  	number of components 
 * 4.analysis
 * 	(1) time complexity: 
 * 		initialize-> O(N)
 *      Union-> O(N): including the cost of find root
 *      find-> O(N)
 *      connected-> O(N)
 *      
 *  (2) Space(memory) complexity:
 *      We creat an array with length N-> O(N)
 * 	
 *  
 * */

/*features:
1.pro: Union operation uses O(n) time complexity (including the cost of find root)
2.cons: find operation uses o(n) time complexity, tree is too tall, find operation is too expensive
*/

public class QuickUnionUF {
	private int[] parent;
	private int count;
	
	public QuickUnionUF(int n) {
		parent = new int[n];//parent[] initialization
		count = n;
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}
	
	//find the group(root) i belongs to
	private int find(int i) {//no one use getRoot() outside the class
		while (i != parent[i]) {
			i = parent[i];
		}
		return i;
	}
	
	public void union(int p, int q) {
		int rop = find(p);
		int roq = find(q);
		if (p == rop) {//if p is root
			parent[p] = q;//update id[p]
		}else{//if p is not root
			if (rop != roq) {//check if p and q are already connected
				parent[rop] = roq;//set root of p's parent to root of q					
			}					
		}
		count--;	
	}
	
	public boolean connected(int p, int q) {
		int rop = find(p);
		int roq = find(q);
		System.out.println(p + "'s root is: " + rop);
		System.out.println(q + "'s root is: " + roq);
		if (rop == roq) {
			System.out.println(p + " and " + q + " are connected");
			return true;
		}else {
			System.out.println(p + " and " + q + " are not connected");
			return false;
		}
	}
 
	//client 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;
		QuickUnionUF quuf = new QuickUnionUF(n);
		quuf.union(2, 9);
		quuf.union(4, 9);
		quuf.union(3, 4);
		quuf.union(5, 6);
		quuf.connected(3, 5);
		quuf.union(3, 5);
		quuf.connected(3, 5);
		System.out.println(Arrays.toString(quuf.parent));	
		System.out.println(quuf.count);	
	}

}
