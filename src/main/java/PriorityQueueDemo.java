import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        int capacity = 16;
        int size = 0;
        PriorityQueueNode[] priorityQueue = new PriorityQueueNode[capacity];
    }
    public PriorityQueueNode[] push(PriorityQueueNode[] priorityQueue, int size, int val, int priority){
        if(size==priorityQueue.length){
            priorityQueue = grow(priorityQueue);
        }
        int index = searchUsingPriority(priorityQueue, size, priority);
        priorityQueue = shift(priorityQueue, index, size);
        priorityQueue[index] = new PriorityQueueNode(val, priority);
        return priorityQueue;
    }
    public int pop(PriorityQueueNode[] priorityQueue, int value, int size) {
        if(priorityQueue[size].getValue() == value){
            return size -1;
        }
        return size;
    }
    public int pop(PriorityQueueNode[] priorityQueue, PriorityQueueNode value, int size){
        if(priorityQueue[size].equals(value)){
            return size -1;
        }
        return size;
    }
    public int popUsingPriority(PriorityQueueNode[] priorityQueue, int priority, int size){
        if(priorityQueue[size].getPriority() == priority){
            return size -1;
        }
        return size;
    }

//    direction :
//    true - means right shift;
//    false - means left shift;
    private PriorityQueueNode[] shift(PriorityQueueNode[] priorityQueue, int from, int to){
        for(int i=to; i > from; i--){
            priorityQueue[i] = priorityQueue[i-1];
        }
        return priorityQueue;
    }

    private PriorityQueueNode[] grow(PriorityQueueNode[] priorityQueue){
        if(priorityQueue.length < (Integer.MAX_VALUE>>1)){
            return Arrays.copyOf(priorityQueue, (priorityQueue.length << 1));
        }else{
            throw new OutOfMemoryError();
        }
    }

    private int searchUsingPriority(PriorityQueueNode[] priorityQueue, int size, int priority){
       if(priorityQueue[size].getPriority() < priority ){
           return size;
       }
       int start = 0, end = size - 1 ;
       int mid;
       while(start<end){
           if(priorityQueue[end].getPriority() <= priority ){
               return end + 1;
           }
           mid = start + (end -start)/2;
           if(priorityQueue[mid].getPriority() < priority ){
               start = mid;
           }else if(priorityQueue[mid].getPriority() > priority ){
               end = mid;
           }
       }
       return end;
    }
}

@Getter
@Setter
@AllArgsConstructor
class PriorityQueueNode{
    Integer value;
    Integer priority;
}