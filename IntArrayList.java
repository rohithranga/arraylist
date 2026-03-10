public class IntArrayList {

    private int[] data;
    private int size; //how many real values have been stored

    public IntArrayList() { //initializing the array
        data = new int[10];
        size = 0;
    }

    public IntArrayList(int capacity) {//initializing the array for any possible capacity
        data = new int[capacity];
        size = 0;
    }

    private boolean isValidIndex(int index) {
        if (index < size && index >= 0) {
            return true;
        }
        return false;
    }

    private void reallocate() {
        int[] newData = new int[data.length * 2];//the Java ArrayList class makes the new array typically twice the size, but we can possibly increase it a different way
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private void shiftLeft(int position) {
        for (int i = position; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
    }

    private void shiftRight(int position) {
        if (size == data.length) {
            reallocate();
        }
        for (int i = size; i > position; i--) {
            data[i] = data[i - 1];
        }
    }
    
    public int size() {
        return size;
    } 
    
    public void add(int value) {
        if (size == data.length) {
            reallocate();
        }
        data[size] = value;
        size++;
    }

    public void add(int index, int value) {
        if (size == data.length) {
            reallocate();
        }
        shiftRight();
        data[index] = value;
        size++;
    }

    public int removeAt(int index) {
        int removed = data[index];
        shiftLeft();
        size--;
        return removed;
    }

<<<<<<< HEAD
    //Override
    public boolean equals(Object otherIntArrayList) {
        if (size != other.size) {
        return false;
    }
         for (int i = 0; i < size; i++) {
        if (data[i] != otherIntArrayList.data[i]) {
            return false;
        }
    }
    return true;
    }

    public int [] toArray() {
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = data[i];
        }
        return array; 
    }

    public boolean contains(int val) {
        for(int i = 0; i < size; i++) {
            if(data[i] == val) {
                return true; 
            }
        }
        return false; 
    }

    public int indexOf(int val) {
        for(int i = 0; i < size; i++) {
            if(data[i] == val) {
                return i;
            }
        }

        return -1; 
    }

=======
    public boolean removeVal(int val) {
        for (int i : data) {
            if (i == val) {
                removeAt(i);
                return true;
            }
        }
        return false;
>>>>>>> b5a3b5777e7d70c7210981152f23e84429cfb7dd
    }
    
    @Override
    public String toString() {
        if (isEmpty()) return "empty";
        String str = "";
        for (int i : data) {
            str += i + ", ";
        }
        return str;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    public int get(int index) {
        if (isValidIndex(index)) {
            return data[index];
        } else {
            throw new ArrayIndexOutOfBoundsException("The index " +  index + " is out of bounds.");
        }
    }
    
    public void set(int index, int val) {
        if (isValidIndex(index)) {
            data[index] = val;
        } else {
            throw new ArrayIndexOutOfBoundsException("The index " +  index + " is out of bounds.");
        }
    }
    
}
