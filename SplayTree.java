public class SplayTree {
    public Node root;
    
    //---------------------The functions below this line were given-------------------------------------------------

    @Override
    public String toString() {
        return (root == null ? "Empty Tree" : toString(root, "", true));
    }

    public String toString(Node node, String prefix, boolean end) {
        String res = "";
        if (node.right != null) {
            res += toString(node.right, prefix + (end ? "│   " : "    "), false);
        }
        res += prefix + (end ? "└── " : "┌── ") + node.toString() + "\n";
        if (node.left != null) {
            res += toString(node.left, prefix + (end ? "    " : "│   "), true);
        }
        return res;
    }

    public String toStringOneLine() {
        return (root == null ? "Empty Tree" : "{" + toStringOneLine(root) + "}");
    }

    public String toStringOneLine(Node node) {
        return node.toString()
                + (node.left == null ? "{}" : "{" + toStringOneLine(node.left) + "}")
                + (node.right == null ? "{}" : "{" + toStringOneLine(node.right) + "}");
    }

    public SplayTree() {
        root = null;
    }

    //---------------------The functions above this line were given-------------------------------------------------

public SplayTree(String input) {
    if (input.equals("Empty Tree")) {
        root = null;
    } else {
        root = createNode(input.substring(1, input.length() - 1), null);
    }
}

private Node createNode(String input, Node parent) {
    if (input.isEmpty()) {
        return null;
    }

    int endOfNode = input.indexOf(']');
    String nodeData = input.substring(1, endOfNode);
    String[] parts = nodeData.split(":");
    int studentNumber = Integer.parseInt(parts[0].substring(1));
    Integer mark = "null%".equals(parts[1]) ? null : Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));

    Node node = new Node(studentNumber, mark);
    node.parent = parent; // Set the parent node

    int startOfLeft = input.indexOf('{', endOfNode);
    int endOfLeft = findMatchingBrace(input, startOfLeft);
    if (startOfLeft + 1 < endOfLeft) {
        node.left = createNode(input.substring(startOfLeft + 1, endOfLeft), node); // Set parent for left child
    }

    int startOfRight = input.indexOf('{', endOfLeft);
    int endOfRight = findMatchingBrace(input, startOfRight);
    if (startOfRight + 1 < endOfRight) {
        node.right = createNode(input.substring(startOfRight + 1, endOfRight), node); // Set parent for right child
    }

    return node;
}

private int findMatchingBrace(String input, int start) {
    int counter = 1;
    int end = start;
    while (counter != 0) {
        end++;
        if (input.charAt(end) == '{') {
            counter++;
        } else if (input.charAt(end) == '}') {
            counter--;
        }
    }
    return end;
}


    //----------------------------------------------------------------------------------------------------------------

    public Node access(int studentNumber) {
        return access(studentNumber, null);
    }

    //----------------------------------------------------------------------------------------------------------------
    public Node access(int studentNumber, Integer mark) {
        Node node = findNode(root, studentNumber);
        
        if (node != null) { //node exists then we must either just splay OR change mark and splay
            if (mark == null) {
                splay(node);
            }
            else if (mark != null) {
                node.mark = mark;
                splay(node);
            }
        }
        if (node == null) { //If node was not found then we create new node and insert and splay
            node = new Node(studentNumber, mark);
            Node y = null;
            Node x = this.root;

            while (x != null) {
                y = x;
                if (node.studentNumber < x.studentNumber) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
            // y is parent of x
            node.parent = y;
            if (y == null) {
                root = node;
            } else if (node.studentNumber < y.studentNumber) {
                y.left = node;
            } else {
                y.right = node;
            }
            
            // splay node
            splay(node);
        }

        //root = splay(studentNumber, root);
        return node;
    }

    private Node findNode(Node node, int studentNumber) {
        if (node == null || node.studentNumber == studentNumber) {
            return node;
        }
        return studentNumber < node.studentNumber ? findNode(node.left, studentNumber) : findNode(node.right, studentNumber);
    }

	private void splay(Node x) {
		while (x.parent != null) {
			if (x.parent.parent == null) {
				if (x == x.parent.left) {
					// zig rotation
					rightRotate(x.parent);
				} else {
					// zag rotation
					leftRotate(x.parent);
				}
			} else if (x == x.parent.left && x.parent == x.parent.parent.left) {
				// zig-zig rotation
				rightRotate(x.parent.parent);
				rightRotate(x.parent);
			} else if (x == x.parent.right && x.parent == x.parent.parent.right) {
				// zag-zag rotation
				leftRotate(x.parent.parent);
				leftRotate(x.parent);
			} else if (x == x.parent.right && x.parent == x.parent.parent.left) {
				// zig-zag rotation
				leftRotate(x.parent);
				rightRotate(x.parent);
			} else {
				// zag-zig rotation
				rightRotate(x.parent);
				leftRotate(x.parent);
			}
		}
	}

	// rotate left at node x
	private void leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		if (y.left != null) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}

	// rotate right at node x
	private void rightRotate(Node x) {
		Node y = x.left;
		x.left = y.right;
		if (y.right != null) {
			y.right.parent = x;
		}
		y.parent = x.parent;
		if (x.parent == null) {
			this.root = y;
		} else if (x == x.parent.right) {
			x.parent.right = y;
		} else {
			x.parent.left = y;
		}
		y.right = x;
		x.parent = y;
	}


    //----------------------------------------------------------------------------------------------------------------

    public Node remove(int studentNumber) {

        Node node = access(studentNumber);

        if (node != null) {
            Node x = null;
            Node t = null; 
            Node s = null;

            // split operation
            if (node.right != null) {
                t = node.right;
                t.parent = null;
            } else {
                t = null;
            }
            s = node;
            s.right = null;
            node = null;

            // join operation
            if (s.left != null){ // remove x
                s.left.parent = null;
            }
            root = join(s.left, t);
            s = null;
        }

        return node;
    }

    private Node join(Node s, Node t){
        if (s == null) {
            return t;
        }

        if (t == null) {
            return s;
        }
        Node x = maximum(s);
        splay(x);
        x.right = t;
        t.parent = x;
        return x;
    }

    public Node maximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    //----------------------------------------------------------------------------------------------------------------

    public String sortByStudentNumber() {
        if (root == null) {
            return "Empty Tree";
        } else {
            StringBuilder sb = new StringBuilder();
            inOrderTraversal(root, sb);
            return sb.toString();
        }
    }

    private void inOrderTraversal(Node node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversal(node.left, sb);
            sb.append(node.toString());
            inOrderTraversal(node.right, sb);
        }
    }

    //----------------------------------------------------------------------------------------------------------------

    public String sortByMark() {
        if (root == null) {
            return "Empty Tree";
        } else {
            int size = countNodes(root);
            Node[] nodes = new Node[size];
            collectNodes(root, nodes, 0);
            selectionSort(nodes);
            StringBuilder sb = new StringBuilder();
            for (Node node : nodes) {
                sb.append(node.toString());
            }
            return sb.toString();
        }
    }

    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + countNodes(node.left) + countNodes(node.right);
        }
    }

    private int collectNodes(Node node, Node[] nodes, int index) {
        if (node != null) {
            index = collectNodes(node.left, nodes, index);
            nodes[index++] = node;
            index = collectNodes(node.right, nodes, index);
        }
        return index;
    }

    private void selectionSort(Node[] nodes) {
        for (int i = 0; i < nodes.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nodes.length; j++) {
                if (nodes[j].compareToMark(nodes[minIndex]) < 0 ||
                    (nodes[j].compareToMark(nodes[minIndex]) == 0 &&
                     nodes[j].compareTo(nodes[minIndex]) < 0)) {
                    minIndex = j;
                }
            }
            Node temp = nodes[minIndex];
            nodes[minIndex] = nodes[i];
            nodes[i] = temp;
        }
    }

    //----------------------------------------------------------------------------------------------------------------
}
