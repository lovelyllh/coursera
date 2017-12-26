package unionFind;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*improvement 1 for Union Find- Weighted Union Find:
 *	1. avoid tall tress
 *	2. keep track of size of each tree(number of objects)
 *	3. balance the tree by linking root of the smaller tree(height shorter) to root of the larger/taller tree.
*/

/*Weighted Union Find Data Structure:
 * 	1.data structre:
		(1) The WeightedQuickUnionUF class represents a union–find data type, 
 		(2) The union–find data type models connectivity among a set of n sites/objects, named 0 through n–1.
 * 	2.data member: 
 * 		(1) int[] parent (parent[i] is i's parent)
 * 		(2) int[] size: size[i] = number of components in subtree rooted at i
 * 		(3) int count: the number of connected components
 * 
 * 	3.methods:
 *		(1) count: get the number of components
 *		(2) find: find the component identifier(root) for the current object 
 *		(3) union: connect p to q, 
 *			1> link root of the smaller tree to root of the bigger tree 
 *				1) if the height of p's root is smaller than the height of q's root-> set id of p's root to id of q's root
 *				2) if the height of q's root is smaller than the height of p's root-> set id of q's root to id of p's root
 *  		2> update size[i]
 *  4.analysis
 * 	(1) time complexity: 
 * 		initialize-> O(N)
 *      Union-> O(lgN): including the cost of finding roots??
 *      find-> O(N)
 *      connected-> O(lgN)
 *      
 *  (2) Space(memory) complexity:
 *      We creat 2 array with length N-> O(2N)->O(N)
 *		
*/

/* Concept：
 * 1. The height of a node is the number of edges on the longest path between that node and a leaf. The height of a tree is the  height of its root node. 
*  2. The depth of a node is the number of edges from the tree's root node to the node.
*/


/*通过sz数组决定如何对两棵树进行合并之后，最后得到的树的高度大幅度减小了。这是十分有意义的，因为在Quick-Union算法中的任何操作，都不可避免的需要调用find方法，而该方法的执行效率依赖于树的高度。树的高度减小了，find方法的效率就增加了，从而也就增加了整个Quick-Union算法的效率。*/

public class WeightedQU {
	private int[] parent;//parent[i] = parent of i
	private int[] size; //size[i] = number of objects in the tree rooted at i
	private int count; //the number of connected components
	
	WeightedQU(int n) {//constructor, create a WeightedQU class intance, and initialize the data members
		parent = new int[n];
		size = new int[n];
		count = n;
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}
	
	public int find(int i) {
		while(i != parent[i]) {
			i = parent[i];
		}
		return i;
	}
	
	//Merges the component containing site p with the component containing site q.
	public void union(int p, int q) {
		int rop = find(p);
		int roq = find(q);
		if (rop == roq) 
			return;
		if (size[rop] < size[roq]) {
			parent[rop] = roq;
			size[roq] += size[rop];
		}else {
			parent[roq] = rop;
			size[rop] += size[roq];
		}		
		count--; 
		/*System.out.println("p union q is " + p + " -> " + q);
		System.out.println("the parent array is " + Arrays.toString(parent));
		System.out.println("the size array is " + Arrays.toString(size) + "\n");//"\n" is new line
		*/
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public int count() {
		return count;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;
		WeightedQU wqu = new WeightedQU(n);
		wqu.union(3, 4);
		wqu.union(3, 8);
		wqu.union(6, 5);
		wqu.union(9, 4);
		wqu.union(2, 1);
		wqu.union(5, 0);
		wqu.union(7, 2);
		wqu.union(6, 1);
		wqu.union(7, 3);
		System.out.println("the parent array is " + Arrays.toString(wqu.parent));
		System.out.println("the size array is " + Arrays.toString(wqu.size));
		System.out.println("the connected components is " + wqu.count);
	}
}

