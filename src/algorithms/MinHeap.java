package algorithms;

import java.util.ArrayList;

public class MinHeap
{
    private ArrayList<Integer> data = null;
    
    public MinHeap()
    {
        data = new ArrayList<Integer>();
    }
    
    /**
     * 获取对应index的parent index
     * @param index
     * @return
     */
    private int parent(int index)
    {
        return index / 2;
    }
    
    /**
     * 获取对应index的左儿子index
     * @param index
     * @return
     */
    private int left(int index)
    {
        return index * 2 + 1;
    }
    
    /**
     * 获取对应index的右儿子index
     * @param index
     * @return
     */
    private int right(int index)
    {
        return index * 2 + 2;
    }
    
    /**
     * 交换data中i与j位置的值
     * @param i
     * @param j
     */
    private void swap(int i, int j)
    {
        int temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }
    
    /**
     * 插入新节点时节点上浮过程
     * @param index
     */
    private void upHead(int index)
    {
        int parent = this.parent(index);
        if (index > 0 && data.get(parent) > data.get(index)) {
            this.swap(index, parent);
            this.upHead(parent);
        }
    }
    
    /**
     * 移除堆顶时大节点下沉
     * @param index
     */
    private void downHeap(int index)
    {
        if (this.left(index) < this.data.size()) {
            int left = this.left(index);
            int small = left;
            if (this.right(index) < this.data.size()) {
                int right = this.right(index);
                if (this.data.get(right) < this.data.get(left)) {
                    small = right;
                }
            }
            if (this.data.get(small) < this.data.get(index)) {
                this.swap(index, small);
                this.downHeap(small);
            }
        }
    }
    
    /**
     * 返回堆顶
     * @return
     */
    public int min()
    {
        if (this.data.size() == 0) {
            return -1;
        }
        return this.data.get(0);
    }
    
    /**
     * 弹出并返回堆顶元素
     * @return
     */
    public int removeTop()
    {
        if (this.data.size() == 0) {
            return -1;
        }
        this.swap(0, this.data.size() - 1);
        int top = this.data.remove(this.data.size() - 1);
        this.downHeap(0);
        return top;
    }
    
    public void test()
    {
        this.data.add(3);
        this.data.add(4);
        this.swap(0, 1);
        System.out.println(this.toString());
    }
    
    /**
     * 增加一个元素
     * @param n
     * @return
     */
    public void add(int n)
    {
        this.data.add(n);
        this.upHead(this.data.size() - 1);
    }
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < data.size(); i++) {
            stringBuilder.append(data.get(i) + " ");
        }
        return stringBuilder.toString();
    }
    
    public static void main(String[] args)
    {
        MinHeap heap = new MinHeap();
//        heap.test();
        heap.add(4);
//        System.out.println(heap);
        heap.add(3);
//        System.out.println(heap);
        heap.add(1);
        heap.add(5);
        heap.add(2);
        System.out.println(heap);
        heap.add(7);
        heap.add(6);
        heap.add(26);
        System.out.println(heap);
        System.out.println(heap.removeTop());
        System.out.println(heap);
        System.out.println(heap.removeTop());
        System.out.println(heap);
        System.out.println(heap.removeTop());
        System.out.println(heap);
        System.out.println(heap.removeTop());
        System.out.println(heap);
    }
}
