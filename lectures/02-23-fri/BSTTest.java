package search;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class BSTTest {

	static class BSTNode {
		String key;
		BSTNode left, right;

		public BSTNode(String key, BSTNode left, BSTNode right) {
			this.key = key;
			this.left = left;
			this.right = right;
		}
	}

	// Return true if key is in the tree, false otherwise
	public static boolean get(BSTNode node, String key) {
		if (node == null) return false;
		int compared = key.compareTo(node.key);
		if (node.key.equals(key)) {
			return true;
		} else if (compared < 0) {
			return get(node.left, key);
		} else {
			return get(node.right, key);
		}
	}

	// If node is null, returns a new BSTNode containing just key
	// If node is not null, add the key at the appropriate ordered place in the
	// tree, and return the same node reference that was passed in.
	public static BSTNode add(BSTNode node, String key) {
		if(node == null) { return new BSTNode(key, null, null); }
		int compared = key.compareTo(node.key);
		if(compared < 0) {
			node.left = add(node.left, key);
			return node;
		}
		else {
			node.right = add(node.right, key);
			return node;
		}
	}

	public void testAdd() {
		BSTNode mtTree = null;
		
		BSTNode mOnEmpty = add(mtTree, "M");
		assertEquals("M", mOnEmpty.key);
		assertTrue(get(mOnEmpty, "M"));

		BSTNode pAndM = add(mOnEmpty, "P");
		assertEquals("M", pAndM.key); assertEquals("P", pAndM.right.key);
		BSTNode mpq = add(pAndM, "Q");
	}

	public static BSTNode doRemove(BSTNode node) {
		if(node.left == null && node.right == null) {
			return null;
		}
		else if(node.left != null && node.right == null) {
			return node.left;
		}
		else if(node.left == null && node.right != null) {
			return node.right;
		}
		else {
			// Find the max node on the left, and replacing current node, and REMOVE the max on left
			BSTNode current = node.left;
			while(current.right != null) {
				current = current.right;
			}
			String maxOnLeft = current.key;
			node.key = maxOnLeft;
			BSTNode newLeft = remove(node.left, maxOnLeft);
			node.left = newLeft;
			return node;
		}
	}
	public static BSTNode remove(BSTNode node, String key) {
		if(node == null) { return null; }
		int compared = key.compareTo(node.key);
		if(compared == 0) {
			return doRemove(node);
		}
		else if(compared < 0) {
			node.left = remove(node.left, key);
			return node;
		}
		else {
			node.right = remove(node.right, key);
			return node;
		}
	}
	
	@Test
	public void testRemove() {
		BSTNode mt = null;
		BSTNode larger = add(add(add(add(mt, "C"), "D"), "A"), "B");
		BSTNode removedL = remove(larger, "C");
		assertEquals("B", removedL.key);
		assertEquals("A", removedL.left.key);
		assertEquals("D", removedL.right.key);
		
		BSTNode leftNoRight = new BSTNode("B", new BSTNode("A", null, null), null);
		BSTNode removedLNR = remove(leftNoRight, "B");
		assertEquals("A", removedLNR.key);
		assertNull(removedLNR.left); assertNull(removedLNR.right);
		
		BSTNode n1 = new BSTNode("A", null, null);
		assertNull(remove(n1, "A"));
		
		BSTNode n2 = new BSTNode("B", null, null);
		assertEquals("B", remove(n2, "A").key);
		
		assertNull(remove(null, "Z"));
	}
	
	@Test
	public void testBST() {
		BST bst = new BST();
		bst.add("A");
		bst.add("M");
		bst.add("C");
		assertEquals("A", bst.root.key);
		assertEquals("M", bst.root.right.key);
		assertEquals("C", bst.root.right.left.key);
	}
	
	
	public static class BST {
		BSTNode root;
		public void add(String key) {
			this.root = BSTTest.add(this.root, key);
		}
		public boolean hasKey(String key) {
			return BSTTest.get(this.root, key);
		}
	}
	
	
	
	
	
	

}
