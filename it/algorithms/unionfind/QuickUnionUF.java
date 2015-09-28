package it.algorithms.unionfind;

/**
 * Quick Union implementation of the Union Find problem.
 * 
 * @author Angelo
 */

public class QuickUnionUF implements UnionFindInterface {
	
	private int[] id;
	
	public QuickUnionUF(int N){
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
