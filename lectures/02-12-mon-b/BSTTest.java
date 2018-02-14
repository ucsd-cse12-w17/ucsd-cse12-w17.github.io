package search;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class BSTTest {

	class BSTNode {
		String key;
		BSTNode left, right;

		public BSTNode(String key, BSTNode left, BSTNode right) {
			this.key = key;
			this.left = left;
			this.right = right;
		}
	}

	BSTNode emptyTree = null;
	BSTNode one = new BSTNode("A", null, null);
	BSTNode tree = new BSTNode("D", new BSTNode("B", null, null), new BSTNode("E", null, new BSTNode("F", null, null)));

	// Return true if key is in the tree, false otherwise
	public static boolean get(BSTNode node, String key) {
		if (node == null) {
			return false;
		}

		int compared = key.compareTo(node.key);

		// This is the version that gets to skip nodes
		if (node.key.equals(key)) {
			return true;
		} else if (compared < 0) {
			return get(node.left, key);
		} else {
			return get(node.right, key);
		}

		/*
		 * // This is the version that might visit _every_ node return get(node.left,
		 * key) || get(node.right, key) || node.key.equals(key);
		 */
	}

	public static String max(BSTNode node) {
		if (node == null) {
			return null;
		} else if (node.right != null) {
			return max(node.right);
		} else {
			return node.key;
		}
	}

	@Test
	public void testBST() {
		assertEquals(null, max(emptyTree));
		assertEquals("A", max(one));
		assertEquals("F", max(tree));
	}

	/*
	 * Some things to try:
	 * 
	 * Write allKeys, which is like allDogs from the last lecture, but returns a
	 * List<String> containing all keys
	 * 
	 * Write count, which takes a node and returns the number of nodes in the tree
	 * 
	 * Write add, which takes a node and a string and returns a new node where the
	 * key is added in the correct place in the tree (or with no change if the key
	 * is already in the tree)
	 * 
	 * Write uppercaseEntireTree, which takes a node and changes all of the key
	 * fields in the whole tree to be their uppercased version
	 * 
	 */

}
