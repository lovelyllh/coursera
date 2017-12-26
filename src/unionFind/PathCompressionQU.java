package unionFind;

import java.util.Arrays;

/*improvement 2 for Union Find- path compression quick union:
 *	1. keep trees completely flat
 *	2. 
*/

/*Weighted Union Find Data Structure:
 * 	1.data structre:
		(1) The PathCompressionUF class represents a union�Cfind data type, 
 		(2) The union�Cfind data type models connectivity among a set of n sites/objects, named 0 through n�C1.
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
 * 1. ����quick-union�㷨���ԣ��ڵ���֯���������Ӧ����һ��ʮ�ֱ�ƽ���������еĺ��ӽڵ�Ӧ�ö���heightΪ1�ĵط��������еĺ��Ӷ�ֱ�����ӵ����ڵ㡣��������֯�ṹ�ܹ���֤find���������Ч�ʡ���ô��ι�����������ṹ�أ�---��find������ִ�й����У�������Ҫ����һ��whileѭ���ҵ����ڵ�������������·�����м�ڵ㵽һ�������У�Ȼ����whileѭ������֮�󣬽���Щ�м�ڵ�ĸ��ڵ�ָ����ڵ㣬��������ô�������������Ҳ�����⣬��Ϊfind������Ƶ���ԣ������Ƶ�������м�ڵ����飬��Ӧ�ķ������ٵ�ʱ����Ȼ�������ˡ���ô��û�и��õķ����أ������еģ������ڵ�ĸ��ڵ�ָ��ýڵ��үү�ڵ㣬��һ������ʮ�ַ�������Ч���൱����Ѱ�Ҹ��ڵ��ͬʱ����·��������ѹ����ʹ�������ṹ��ƽ����
 * 
 * 2. �����Ҫ�Ĺ��ܲ������Ǽ�������ڵ��Ƿ���ͨ������Ҫ����ͨʱ�õ������·������ô����Ҫ�õ�����㷨�ˣ�����DFS����BFS��
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
			parent[p] = parent[parent[p]]; //���ǽ���ֻ��Ҫһ�д���(��ɫ�Ĳ���)�Ϳ��Ի���ʵ�ֽ�����ƽ,���д����ǽ�nodeָ�������游ĸ(grandnparents)��
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


