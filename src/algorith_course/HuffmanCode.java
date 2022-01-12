package algorith_course;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class HuffmanCode
{
	public static String writeString;

	// 内部类：单个节点（实现哈夫曼编码）
	public static class HNode
	{
		String data = "";
		String code = "";

		@Override
		public String toString()
		{
			return "HNode [code = " + code + ", data = " + data + "]";
		}

		public HNode(String data)
		{
			super();
			this.data = data;
		}

		// 重写hashCode方法，使其计算并返回哈夫曼编码
		@Override
		public int hashCode()
		{
			final int prime = 5;
			int result = 1;
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			return result;
		}

		// 重写equals比较方法
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
			{
				return true;
			}
			if (obj == null)
			{
				return false;
			}
			if (getClass() != obj.getClass())
			{
				return false;
			}
			HNode other = (HNode) obj;
			if (data == null)
			{
				if (other.data != null)
				{
					return false;
				}
			}
			else if (!data.equals(other.data))
			{
				return false;
			}
			return true;
		}

	}

	// 内部类：哈弗曼树节点
	public static class Node
	{
		HNode data;
		int weight;
		Node leftChild;
		Node rightChild;

		public Node(HNode data, int weight)
		{
			this.data = data;
			this.weight = weight;
		}

		public String toString()
		{
			return "Node[data=" + data + ", weight=" + weight + "]";
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
			{
				return true;
			}
			if (obj == null)
			{
				return false;
			}
			if (getClass() != obj.getClass())
			{
				return false;
			}
			Node other = (Node) obj;
			if (data == null)
			{
				if (other.data != null)
				{
					return false;
				}
			}
			else if (!data.equals(other.data))
			{
				return false;
			}
			return true;
		}

	}

	// main函数
	public static void main(String[] args)
	{
		System.out.println("请输入字符串：");
		Scanner sc = new Scanner(System.in);
		HuffmanCode.writeString = sc.nextLine();
		char[] chars = writeString.toCharArray();
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < chars.length; i++)
		{
			Node t = new Node(new HNode(String.valueOf(chars[i])), 1);
			if (nodes.contains(t))
			{
				nodes.get(nodes.indexOf(t)).weight++;
			}
			else
			{
				nodes.add(t);
			}
		}
		sc.close();
		Node root = HuffmanCode.createTree(nodes);
		breadthFirst(root, nodes);

		HashMap<String, String> codeMap = new HashMap<String, String>();
		for (int i = 0; i < chars.length; i++)
		{
			Node t = new Node(new HNode(String.valueOf(chars[i])), 1);
			// 加入codeMap
			if (!codeMap.containsKey(nodes.get(nodes.indexOf(t)).data.data))
			{
				codeMap.put(nodes.get(nodes.indexOf(t)).data.data, nodes.get(nodes.indexOf(t)).data.code);
			}
		}
		System.out.println("Data & Code : ");
		System.out.println(codeMap);
	}

	// 生成哈弗曼树
	private static Node createTree(List<Node> nodess)
	{
		List<Node> nodes = new ArrayList<Node>(nodess);
		// 只要nodes数组中还有2个以上的节点
		while (nodes.size() > 1)
		{
			quickSort(nodes);
			// 获取权值最小的两个节点
			Node left = nodes.get(nodes.size() - 1);
			Node right = nodes.get(nodes.size() - 2);
			// 生成新节点，新节点的权值为两个子节点的权值之和
			Node parent = new Node(new HNode(null), left.weight + right.weight);
			// 让新节点作为权值最小的两个节点的父节点
			parent.leftChild = left;
			parent.rightChild = right;
			// 删除权值最小的两个节点
			nodes.remove(nodes.size() - 1);
			nodes.remove(nodes.size() - 1);
			// 将新生成的父节点添加到集合中
			nodes.add(parent);
		}
		// 返回nodes集合中唯一的节点，也就是根节点
		return nodes.get(0);
	}

	// 排序
	public static void quickSort(List<Node> nodes)
	{
		subSort(nodes, 0, nodes.size() - 1);
	}

	private static void subSort(List<Node> nodes, int start, int end)
	{
		if (start < end)
		{
			Node base = nodes.get(start);
			int i = start;
			int j = end + 1;
			while (true)
			{
				while (i < end && nodes.get(++i).weight >= base.weight)
					;
				while (j > start && nodes.get(--j).weight <= base.weight)
					;

				if (i < j)
				{
					swap(nodes, i, j);
				}
				else
				{
					break;
				}
			}
			swap(nodes, start, j);
			// 递归左子序列
			subSort(nodes, start, j - 1);
			// 递归右边子序列
			subSort(nodes, j + 1, end);
		}
	}

	// 交换
	private static void swap(List<Node> nodes, int i, int j)
	{
		Node tmp;
		tmp = nodes.get(i);
		nodes.set(i, nodes.get(j));
		nodes.set(j, tmp);
	}

	// 广度优先遍历
	public static void breadthFirst(Node root, List<Node> nodes)
	{
		// System.out.println("我 "+nodes);
		Queue<Node> queue = new ArrayDeque<Node>();
		List<Node> list = new ArrayList<Node>();
		if (root != null)
		{
			// 将根元素入队列
			queue.offer(root);
		}
		while (!queue.isEmpty())
		{
			// 将该队列的“队尾”的元素添加到List中
			list.add(queue.peek());
			Node p = queue.poll();
			// 如果左子节点不为null，将它加入队列
			if (p.leftChild != null)
			{
				queue.offer(p.leftChild);
				p.leftChild.data.code = p.data.code + "0";
			}
			else
			{
				((Node) nodes.get(nodes.indexOf(p))).data.code = p.data.code;
			}
			// 如果右子节点不为null，将它加入队列
			if (p.rightChild != null)
			{
				queue.offer(p.rightChild);
				p.rightChild.data.code = p.data.code + "1";
			}
		}

	}
}
