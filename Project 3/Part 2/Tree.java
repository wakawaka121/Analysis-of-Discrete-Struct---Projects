
public class Tree {
	private Node root;
	private Integer height;
	public int size;
	
	private class Node{
		public Integer key;
		public String val;
		public Integer height;
		public Node left;
		public Node right;
		
		public Node(int key, String value) {
			this.key = key;
			this.val = value;
			height = 0;
			left = null;
			right = null;
		}
	}
	
	public Tree() {
		root = null;
		height = 0;
		size = 0;
	}
		
	public void put(Integer key, String value) {
		Node newNode = new Node(key,value);
		if(contains(key)) {
			Node updateNode = findNode(root, key);
			updateNode.val = value;
		}
		if(isEmpty()) {
			size++;
			root = newNode;
			
		} else {
			root =putHelper(root, newNode);
		}
	}
	
	private Node putHelper(Node curr, Node newElement) {
		if(curr == null) {
			size++;
			if(height < newElement.height) {
				height = newElement.height;
			}
			return newElement;
		}
		if(newElement.key < curr.key) {
			newElement.height++;
			curr.left = putHelper(curr.left, newElement);
		} else if(newElement.key > curr.key) {
			newElement.height++;
			curr.right = putHelper(curr.right, newElement);
		} 
		return curr;
	}
	
	public String get(Integer key) {
		if(contains(key)) {
			Node curr = root;
			return getHelper(root,key);
		}
		return null;
	}
	
	private String getHelper(Node curr, Integer key) {
		if(curr.key.equals(key)) {
			return curr.val;
		}
		if(curr.key > key) {
			curr = curr.left;
			return getHelper(curr,key);
		}
		if(curr.key < key) {
			curr= curr.right;
			return getHelper(curr,key);
		}
		return null;
	}

	public boolean contains(Integer key) {
		Node curr = root;
		return containsHelper(curr,key);
	}
	
	private boolean containsHelper(Node curr, Integer key) {
		if(curr == null) {
			return false;
		} else {
			Node foundNode = findNode(curr,key);
			if(foundNode == null) {
				return false;
			}else {
				return foundNode.key.equals(key);
			}
		}
	}
	
	public int height() {
		return height;
	}
	
	public int height(Integer key) {
		if(contains(key)) {
			Node curr = root;
			return height - nodeHeightHelper(curr,key);
		}
		return -1;
	}
	
	private int nodeHeightHelper(Node curr, Integer key) {
		Node found = findNode(curr,key);
		return found.height;
	}
	
	public int size(Integer key) {
		Node curr = root;
		int subNodes = -1;
		if(contains(key)) {
			Node curRoot = findNode(curr,key);
			subNodes = countNodes(curRoot);
		} 
		return subNodes;
	}
	
	private int countNodes(Node curRoot) {
		if(curRoot == null) {
			return 0;
		}
		return 1 + countNodes(curRoot.left) + countNodes(curRoot.right);
	}
	
	private Node findNode(Node curr, Integer key) {
		if(curr == null) {
			return null;
		}
		if(curr.key.equals(key)) {
			return curr;
		}
		if(curr.key > key) {
			curr = curr.left;
			return findNode(curr,key);
		}
		if(curr.key < key) {
			curr = curr.right;
			return findNode(curr,key);
		}
		return null;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	
	
}
