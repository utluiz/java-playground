package challenges.graphpredecessor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * http://pt.stackoverflow.com/questions/17069/predecessor-comum-em-um-grafo-de-commits
 */
public class GraphPredecessor {

	static String predecessorComum(String[] commits, String[][] parentes, String commit1,
			String commit2) {

		//validação
		if (commit1 == null || commit2 == null || parentes == null || commits == null
				|| commits.length != parentes.length) {
			return null;
		}
		
		//caso ótimo
		if (commit1.equals(commit2)) {
			return commit1;
		}
		
		//mapa (commit, parents[])
		Map<String, String[]> parentMap = new HashMap<String, String[]>();
		for (int i = 0; i < commits.length; i++) {
			if (parentes[i] != null) {
				parentMap .put(commits[i], parentes[i]);
			}
		}

		//adds all parents of commit1 into a Set
		Set<String> commit1Parents = new HashSet<String>();
		
		//iterate over parents without recursion
		LinkedList<String> q = new LinkedList<String>();
		q.push(commit1);
		commit1Parents.add(commit1);
		while (!q.isEmpty()) {
			String s = q.pop();
			String[] parentsArray = parentMap.get(s);
			if (parentsArray != null) {
				for (String p : parentsArray) {
					//for each parent, if it's commit2, then return it!
					if (p.equals(commit2)) {
						return p;
					} else {
						//otherwise just push the node for the next loop
						q.push(p);
						//and adds to parent list
						commit1Parents.add(p);
					}
				}
			}
		}
		
		//check the first commit2 parent in commitParents
		q.push(commit2);
		while (!q.isEmpty()) {
			
			String s = q.pop();

			String[] parentsArray = parentMap.get(s);
			if (parentsArray != null) {
				for (String p : parentsArray) {
					if (commit1Parents.contains(p)) {
						return p;
					}
					q.push(p);
				}
			}
			
		}

		return null;

	}
	
	public static void displayResult(String expected, String returned) {
		System.out.println("---------- TEST ----------");
		System.out.println("Exptected " + expected);
		System.out.println("Result    " + returned);
		System.out.println("Status    " + (expected.equals(returned)?"OK":"ERROR"));
	}

	public static void main(String[] args) {
		String[] commits = {"C7", "C6", "C5", "C4", "C3", "C2", "C1"};
		String[][] parentes ={{"C6","C4"}, {"C5"}, {"C2"}, {"C3"}, {"C2"}, {"C1"}, null};
		
		displayResult("C2", predecessorComum(commits, parentes, "C4", "C6"));
		displayResult("C2", predecessorComum(commits, parentes, "C6", "C4"));
		displayResult("C1", predecessorComum(commits, parentes, "C1", "C7"));
		displayResult("C1", predecessorComum(commits, parentes, "C7", "C1"));
		displayResult("C7", predecessorComum(commits, parentes, "C7", "C7"));
		displayResult("C6", predecessorComum(commits, parentes, "C6", "C7"));
		displayResult("C6", predecessorComum(commits, parentes, "C7", "C6"));
		displayResult("C2", predecessorComum(commits, parentes, "C2", "C7"));
		displayResult("C2", predecessorComum(commits, parentes, "C7", "C2"));
		displayResult("C2", predecessorComum(commits, parentes, "C5", "C3"));
		displayResult("C2", predecessorComum(commits, parentes, "C3", "C5"));

	}
	
}
