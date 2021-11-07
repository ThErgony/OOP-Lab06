package it.unibo.oop.lab.generics1;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphImpl<N> implements Graph<N> {

	private Map<N, Set<N>> graphMap = new LinkedHashMap<>();
	
	public void addNode(final N node) {
		if (node != null) {
			this.graphMap.put(node, null);
		}	
	}

	public void addEdge(final N source, final N target) {
		if (source != null && target != null && source != target) {
			Set<N> value = new LinkedHashSet<>();
			if (this.graphMap.get(source) != null) {
				value.addAll(this.graphMap.get(source));
			}
			value.add(target);
			this.graphMap.put(source, value);
			}
		}
	

	@Override
	public Set<N> nodeSet() {
		return new LinkedHashSet<>(this.graphMap.keySet());
	}

	@Override
	public Set<N> linkedNodes(final N node) {
		Set<N> linkedNodes = new LinkedHashSet<>();
		if (this.graphMap.get(node) == null) {
			linkedNodes.clear();
		} else {
			linkedNodes.addAll(this.graphMap.get(node));
	/*
	 * down code is for all linked nodes (from node and to node), up only from start node
	 */
//			for (final N key : this.graphMap.keySet()) {
//				if (this.graphMap.get(key) != null) {
//					for (final N value : this.graphMap.get(key)) {
//						if (value == node) {
//							linkedNodes.add(key);
//						}
//					}
//				}
//			}
		}
		return linkedNodes;
	/*
	 * alternative solution for get only nodes from start node
	 */
//		if (this.graphMap.get(node) == null) {
//			return new LinkedHashSet<>();
//		}
//		return new LinkedHashSet<>(this.graphMap.get(node));
	}

	@SuppressWarnings("unchecked")
	public List<N> getPath(final N source, final N target) {
		List<List<N>> edge = new LinkedList<>();
		edge.add(0, new LinkedList<>());
		edge.get(0).add(source);
//		System.out.println("First step " + edge);
		for (int i = 0; i < edge.size(); i++) {
			N start = edge.get(i).get(edge.get(i).size()-1);
			while (start != target && this.graphMap.get(start) != start && this.graphMap.get(start) != null) {
//				System.out.println("into while get(start) " + this.graphMap.get(start) + " start " + start);
				int j = 1;
				for (final N value : this.graphMap.get(start)) {
						if (j < this.graphMap.get(start).size()) {
						edge.add(edge.size(), new LinkedList<>(edge.get(i)));
						edge.get(edge.size()-1).add(value);
						j++;
					} else {
						edge.get(i).add(value);
						start = value;
					}
				}
//				System.out.println("step "+ i + " " + edge + " " + edge.size() + " " + start);
			}
		}
		/*
		 * get a list for dead node, path do not reach destination
		 */
		List<List<N>> deadNode = new LinkedList<>();
		int i = 0;
		for (List<N> list : edge) {
			if (list.get(list.size() - 1) != target) {
				deadNode.add(i, list);
				i++;
			}
		}
//		System.out.println(deadNode);
		edge.removeAll(deadNode);
		/*
		 * choose the first min path
		 */
		var minPath = new LinkedList<>();
		for (final List<N> path : edge) {
			if (minPath.size() > path.size() || minPath.size() == 0) {
				minPath.clear();
				minPath.add(path);
			}
		}
		return (List<N>) minPath;
	}
	
}