import java.util.Iterator;

public class IntArrayListIterator implements Iterator<Integer> {

    private int[] data;
    private int size;
    private int index;

    public IntArrayListIterator(int[] data, int size) {
        this.data = data;
        this.size = size;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public Integer next() {
        return data[index++];
    }
}