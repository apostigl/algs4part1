/**
 * Given a set of N objects.
 * 
 * Union command: connect two objects.
 * Find/connected query: is there a path connecting the two objects?
 * 
 */

package it.algorithms.unionfind;

public interface UnionFindInterface {

	//add connection between p and q
	public void union(int p, int q);
	
	//are p and q in the same component?
	public boolean connected(int p, int q);
	
	//component identifier for p (0 to N – 1)
	public int find(int p);
	
	//number of components
	public int count();
}