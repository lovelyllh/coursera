package unionFind;

import java.util.Arrays;

/*improvement 2 for Union Find- path compression quick union:
 *	1. keep trees completely flat
 *	2. 
*/

/*Weighted Union Find Data Structure:
 * 	1.data structre:
		(1) The PathCompressionUF class represents a union–find data type, 
 		(2) The union–find data type models connectivity among a set of n sites/objects, named 0 through n–1.
 * 	2.data member: 
 * 		(1) int[] parent (parent[i] is i's parent)
 * 		(2) int[] size: size[i] = number of components in subtree rooted at i
 * 		(3) int count: the number of connected components
 * 
 * 	3.methods:
 *		(1) find: 
 *			1> find the component identifier(root) for the current object 
 *			2> connect p to root, store all the parents along the path to variable newp, and link them to the root
 *		(2) union: link p to q, 
 *	
 *	4.analysis
 * 	(1) time complexity: 
 * 		initialize-> O(N)
 *      Union-> O(1): including the cost of finding roots??
 *      find-> O(1)
 *      connected-> O(1)
 *      
 *  (2) Space(memory) complexity:
 *      We create 2 array with length N-> O(2N)->O(N)		
 *		
*/


/*notes:
 * 1. 对于quick-union算法而言，节点组织的理想情况应该是一颗十分扁平的树，所有的孩子节点应该都在height为1的地方，即所有的孩子都直接连接到根节点。这样的组织结构能够保证find操作的最高效率。那么如何构造这种理想结构呢？---在find方法的执行过程中，不是需要进行一个while循环找到根节点嘛？如果保存所有路过的中间节点到一个数组中，然后在while循环结束之后，将这些中间节点的父节点指向根节点，不就行了么？但是这个方法也有问题，因为find操作的频繁性，会造成频繁生成中间节点数组，相应的分配销毁的时间自然就上升了。那么有没有更好的方法呢？还是有的，即将节点的父节点指向该节点的爷爷节点，这一点很巧妙，十分方便且有效，相当于在寻找根节点的同时，对路径进行了压缩，使整个树结构扁平化。
 * 
 * 2. 如果需要的功能不仅仅是检测两个节点是否连通，还需要在连通时得到具体的路径，那么就需要用到别的算法了，比如DFS或者BFS。
 */

public class PathCompressionQU {
	int[] parent;
	int[] size;
	int count;
	
	public PathCompressionQU(int n) {
		parent = new int[n];
		size = new int[n];
		count = n;		
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}
	
// find method, way 1: Two-pass implementation: add second loop to root() to set the id[] of each examined node to the root.
	public int find(int p) {
		int root = p;
		//get p's root
		while (root != parent[root]) {
			root = parent[root];
		}
		//connect p to root, store all the parents along the path to variable newp, and link them to the root
		while(p != root) {
			int newp = parent[p]; //store the parent set
			parent[p] = root;//connect p to root
			p = newp; //p move up to parent set, later will connect parent to root
		}	
		return root;
	}
	
/*find method, way 2 Simpler one-pass variant: Make every other node in path point to its grandparent (thereby halving path length).

	public int find(int p){ 
		while (p != parent[p]){
			parent[p] = parent[parent[p]]; //我们仅仅只需要一行代码(白色的部分)就可以基本实现将树变平,这行代码是将node指向它的祖父母(grandnparents)。
			p = parent[p];
		}
		return p;
	}
*/
	
	public void union(int p, int q) {
		int rop = find(p);
		int roq = find(q);
		if (rop == roq) {
			return;
		}else {
			if (size[rop] < size[roq]) {
				parent[rop] = roq;
				size[q] += size[p];
			}else {
				parent[roq] = rop;
				size[p] += size[q];
			}
		}	
		count--;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 13;
		PathCompressionQU pqu = new PathCompressionQU(n);
		pqu.union(0, 2);
		pqu.union(3, 7);
		pqu.union(0, 3);
		pqu.union(1, 4);
		pqu.union(1, 5);
		pqu.union(0, 1);
		pqu.union(8, 10);
		pqu.union(6, 8);
		pqu.union(0, 6);
		pqu.union(9, 11);
		pqu.union(9, 12);
		pqu.union(0, 9);
		System.out.println(Arrays.toString(pqu.size));
		
	}
}


