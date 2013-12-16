package list.impl.poly;

/**
 * ����һ������ȥ�ص�����
 * 
 * @author qipeng
 * 
 */
public class Polynomial {
	private Node first;
	private Node last;
	private int size = 0;

	/**
	 * ���һ���ڵ�
	 * 
	 * @param coef
	 * @param expn
	 */
	public void add(int coef, int expn) {
		if (first == null) {
			Node current = new Node(coef, expn);
			first = current;
			last = current;
			size++;
		} else {
			Node exist = find(expn);
			if (exist != null) {
				exist.coef += coef;
			} else {
				Node newNode = new Node(coef, expn);
				// �жϲ���Ľڵ��Ƿ���Ҫfirst��lastָ����ƶ�
				if (expn < first.expn) {
					newNode.next = first;
					first = newNode;
				} else if (expn > last.expn) {
					last.next = newNode;
					last = newNode;
				} else {
					Node previous = first;
					Node current = previous.next;
					while (current != null) {
						if (current.expn > expn) {
							break;
						}
						previous = previous.next;
						current = current.next;
					}
					previous.next = newNode;
					newNode.next = current;
				}
				size++;
			}
		}
	}

	/**
	 * ģ�����ʽ���
	 * @param p
	 */
	public void addPolyn(Polynomial p) {
		if (p != null) {
			Node currentP = p.first;
			while (currentP != null) {
				add(currentP.coef, currentP.expn);
				currentP = currentP.next;
			}
		}
	}

	private Node find(int expo) {
		if (first == null)
			return null;
		Node current = first;
		while (current != null) {
			if (current.expn == expo) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	/**
	 * ģ�����ʽ���
	 * @param p
	 * @return
	 */
	public Polynomial multiplyNode(Polynomial p) {
		Polynomial newPoly = new Polynomial();
		if (p != null) {
			Node currentP = p.first;
			while (currentP!= null) {
				for (Node current = first; current != null; current = current.next) {
					newPoly.add(currentP.coef * current.coef, currentP.expn
							+ current.expn);
				}
				currentP = currentP.next;
			}
		}
		return newPoly;
	}

	private class Node {
		public int coef;
		public int expn;
		public Node next;

		public Node(int coef, int expn) {
			this.coef = coef;
			this.expn = expn;
		}
	}
	
	public static void main(String[] args){
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		p1.add(2, 1);
		p1.add(3, 2);
		p1.add(10, 3);
		p2.add(3, 2);
		p1.addPolyn(p2);
		Polynomial newPoly =p1.multiplyNode(p2);
		System.out.println(newPoly);
	}
}
