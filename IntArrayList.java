public class IntArrayList {

    private int[] data;
    private int size;

    public IntArrayList() {
        data = new int[10];
        size = 0;
    }
    public IntArrayList(int capacity) {
        data = new int[capacity];
        size = 0;
    }
    
    public int size() {
        return size;
    } 
    
    public void add(int value) {
        if (size == data.length) {
            resize();
        }
        data[size] = value;
        size++;
    }

    public void add(int index, int value) {
        if (size == data.length) {
            resize();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }
    
    private void resize() {
        int[] newData = new int[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
     
    }
