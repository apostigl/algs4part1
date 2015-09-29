/**
 * Weighted Quick Union implementation of the Union Find problem.
 * 
 * @author Angelo Postiglione
 */

package it.algorithms.unionfind;

public class WeightedQuickUnionUF implements UnionFindInterface {
	
	private int[] id;
	private int[] sz; //the count of nodes of the tree rooted at i
	
	public WeightedQuickUnionUF(int N){
		id = new int[N];
		
		for (int i = 0; i < N; i++)
			id[i] = i;
	}
	
	private int root(int i){
		while(i != id[i]){
			i = id[i];
		}
		return i;
	}
	
	
	@Override
	public boolean connected(int p, int q){
		return root(p) == root(q); 
	}
	
	@Override
	public void union(int p, int q){
		int i = root(p);
		int j = root(q);
	
		if(i==j) return;
		//link root of smaller tree to root of larger tree
		if(sz[i] < sz[j]){
			id[i] = j;
			sz[j] += sz[i];
		}
		else{
			id[j] = i;
			sz[i] += sz[j];
		}
		
		id[i] = j;
	}

	@Override
	public int find(int p) {
		return root(p);
	}

	@Override
	public int count() {
		return id.length;
	}
	
	@Override
	public String toString(){
		String values = "";
		for (int i : id) {
			values = values.concat(id[i]+", ");
		}
		return values.substring(0,values.length()-2);
	}
	
}
