// Arup Guha
// 5/2/2014
// Solution to 208 Spring Computer Science II Homework Problem: Ant (using Kruskal's)

import java.util.*;

public class ant_kruskals {

	final public static int NOMST = -1;

	public static void main(String[] args) {

		Scanner fin = new Scanner(System.in);

		// Process all cases.
		int numCases = fin.nextInt();
		for (int loop=1; loop<=numCases; loop++) {

			int v = fin.nextInt();
			int e = fin.nextInt();
			edge[] eList = new edge[e];

			// Read in edges.
			for (int i=0; i<e; i++) {
				int v1 = fin.nextInt()-1;
				int v2 = fin.nextInt()-1;
				int w = fin.nextInt();
				eList[i] = new edge(v1, v2, w);
			}

			// Get mst weight.
			int res = kruskals(eList, v);

			// Not connected.
			if (res == NOMST)
				System.out.println("Campus #"+loop+": I'm a programmer, not a miracle worker!");

			// This is our solution.
			else
				System.out.println("Campus #"+loop+": "+res);
		}
		fin.close();
	}

	// Returns the weight of the MST using Kruskal's Algorithm. If no MST exists,
	// -1 is returned.
	public static int kruskals(edge[] eList, int n) {

		djset myDJ = new djset(n);
		int edgeCount = 0, mstWeight = 0, i = 0;

		// Must have these in order for Kruskal's.
		Arrays.sort(eList);

		// Regular Kruskal's cutting out if all edges have been considered.
		while (edgeCount < n-1 && i < eList.length) {

			// Try to add this edge in.
			boolean res = myDJ.union(eList[i].v1, eList[i].v2);

			// Success!
			if (res) {
				edgeCount++;
				mstWeight += eList[i].w;
			}

			// Go to next edge.
			i++;
		}

		// Return weight or that no MST exists.
		if (edgeCount < n-1) return NOMST;
		return mstWeight;
	}
}

// Written in class
class djset {

	private int[] parent;

	// Creates a disjoint set of size n (0, 1, ..., n-1)
	public djset(int n) {
		parent = new int[n];
		for (int i=0; i<n; i++)
			parent[i] = i;
	}

	public int find(int v) {

		// I am the club president!!! (root of the tree)
		if (parent[v] == v) return v;

		// Find my parent's root.
		int res = find(parent[v]);

		// Attach me directly to the root of my tree.
		parent[v] = res;
		return res;
	}

	public boolean union(int v1, int v2) {

		// Find respective roots.
		int rootv1 = find(v1);
		int rootv2 = find(v2);

		// No union done, v1, v2 already together.
		if (rootv1 == rootv2) return false;

		// Attach tree of v2 to tree of v1.
		parent[rootv2] = rootv1;
		return true;
	}
}

// An edge class that sorts by weight, smallest to largest.
class edge implements Comparable<edge> {

	public int v1;
	public int v2;
	public int w;

	public edge(int start, int end, int weight) {
		v1 = start;
		v2 = end;
		w = weight;
	}

	public int compareTo(edge other) {
		return this.w - other.w;
	}
}