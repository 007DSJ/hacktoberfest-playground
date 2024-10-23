public class heapsort {

    static class heap{

        private int heap[];
        private int size;
    
    public heap(int capacity)
    {
        heap=new int[capacity];
    }

    public boolean isFull()
    {
        return size==heap.length;
    }

    public int getParent(int index)
    {
        return (index-1)/2;
        //in java integer division always gives floored result
    }

    public void insert(int val)
    {
          if(isFull())
          {
            throw new IndexOutOfBoundsException("Heap is full");
          }
         
          heap[size]=val;

          //heapify
          fixHeap(size);
          size++;

    }

    public void fixHeap(int index)
    {
        int newVal=heap[index];

        while(index>0 && newVal > heap[getParent(index)])
        {
           heap[index]=heap[getParent(index)];
           index=getParent(index);
        }

        heap[index]=newVal;
    }

    public boolean isEmpty()
    {
        return size==0;
    }

    public int getChild(int index,boolean left)
    {
        return 2*index +(left ? 1 :2);
    }

    public int delete(int index)
    {
        if(isEmpty())
        {
            throw new IndexOutOfBoundsException("Heap is empty");
        }

        int parent=getParent(index);
        int deleteVal=heap[index];

        heap[index]=heap[size-1];

        //lookup or lookdown
        if(index==0 || heap[index] <heap[parent])
        {
            fixHeapBelow(index, size-1);
        }
        else 
        {
            fixHeap(index);
        }

        size--;
        return deleteVal;

    } 

    private void fixHeapBelow(int index,int lastNodeIndex)
    {
      int childToSwap;

      while(index <=lastNodeIndex)
      {
        int LC=getChild(index, true);
        int RC=getChild(index, false);

        if(LC <=lastNodeIndex) //has a LC
        {
            if(RC >=lastNodeIndex)
            {
                childToSwap=LC;
            } //doesnt have  RC
           else
           {
            childToSwap=(heap[LC] >heap[RC] ? LC :RC);
           } 
        

        if(heap[index] < heap[childToSwap])
        {
            int tmp=heap[index];
            heap[index]=heap[childToSwap];
            heap[childToSwap]=tmp;
        }
        else
        {
            break;
        }

        index=childToSwap;
      }
     else
     {
        break;
     } 
    }
  }

  public void printHeap()
  {
    for(int i=0;i<heap.length;i++)
    {
        System.out.print(heap[i]+" ");
    }
  }

  public int peek()
  {
    if(isEmpty())
    {
        throw new IndexOutOfBoundsException("Heap is Empty");

    }

    return heap[0];
  }

  public void heapsort()
  {
    int lastHeapIndex=size-1;
    for(int i=0;i<lastHeapIndex;i++)
    {
        int tmp=heap[0];
        heap[0]=heap[lastHeapIndex-i];
        heap[lastHeapIndex-i]=tmp;

        fixHeapBelow(0, lastHeapIndex-i-1);
    }
  }

}
    public static void main(String[] args) {
        

        heap h=new heap(10);

        h.insert(80);
        h.insert(75);
        h.insert(60);
        h.insert(68);
        h.insert(55);
        h.insert(40);
        h.insert(52);
        h.insert(67);

        h.printHeap();
        System.out.println();
        
        h.heapsort();

        h.printHeap();
    }
}
