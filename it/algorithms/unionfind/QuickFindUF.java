package it.algorithms.unionfind;

/**
 * Quick Find implementation of the Union Find problem.
 * 
 * @author Angelo
 */

public class QuickFindUF implements UnionFindInterface {
	
	private int[] id;
	
	public QuickFindUF(int N){
		id = new int[N];
		
		for (int i = 0; i < N; i++)
			id[i] = i;
	}
	
	@Override
	public boolean connected(int p, int q){
		return id[p] == id[q]; 
	}
	
	@Override
	public void union(int p, int q){
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++)
			if (id[i] == pid) id[i] = qid;
	}

	@Override
	public int find(int p) {
		return id[p];
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
