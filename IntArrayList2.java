public class IntArrayList2 {

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
        size++;
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
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("The index " + index + " is out of bounds.");
        }
        shiftRight(index);
        data[index] = value;
    }

    public int removeAt(int index) {
        if (!isValidIndex(index)) {
            throw new ArrayIndexOutOfBoundsException("The index " + index + " is out of bounds.");
        }
        int removed = data[index];
        shiftLeft(index);
        return removed;
    }

    public boolean removeVal(int val) {
        for (int i = 0; i < size; i++) {
            if (data[i] == val) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        if (isEmpty()) return "empty";

        String str = "";
        for (int i = 0; i < size; i++) {
            str += data[i];
            if (i < size - 1) {
                str += ", ";
            }
        }
        return str;
    }

    public boolean isEmpty() {
        return size == 0;
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof IntArrayList)) {
            return false;
        }

        IntArrayList otherList = (IntArrayList) other;

        if (this.size != otherList.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (this.data[i] != otherList.data[i]) {
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

    public class IntArrayList2 {

        private int[] data;
        private int size; //how many real values have been stored
    
    
        public void clear() {
            for(int i = 0; i < size; i++) {
                data[i] = 0; 
            }
            size = 0; 
        }
    
        public int sum() {
            int sum = 0; 
            for(int i = 0; i < size; i++) {
                sum += data[i];
            }
            return sum; 
        }
    
        public double average() {
            return (double) sum()/size ; 
        }

        public boolean isSorted() {
            if (data == null || data.length < 2) {
                return true;
            }
            for (int i = 0; i < size - 1; i++) {
                if (data[i] > data[i + 1]) {
                    return false; 
                }
            }       
        return true;
        }

        public double StDev() {
            double mean = average();
            double sumOfDiff = 0;

            for (int i = 0; i < size; i++) {
                double x = ((data[i] - mean) * (data[i] - mean));
                sumOfDiff += x;
            }

            return Math.sqrt(sumOfDiff / size);
        }
        }


    }




}

}

