import java.util.Iterator;

public class IntArrayList implements Iterable<Integer> {
    private int[] data;
    private int size; //how many real values have been stored

    @Override
    public Iterator<Integer> iterator() {
        return new IntArrayListIterator(data, size);
    }

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
    
        StringBuilder str = new StringBuilder();
    
        for (int i = 0; i < size; i++) {
            str.append(data[i]);
    
            if (i < size - 1) {
                str.append(", ");
            }
        }
    
        return str.toString();
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
    
    public void clear() {
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
        if (size == 0) return 0;
        return (double) sum()/size; 
    }

    public void mergeSort() {
        if (size <= 1) {
            return;
        }
        mergeSortHelper(0, size - 1);
    }
    
    private void mergeSortHelper(int left, int right) {
        if (left >= right) {
            return;
        }
    
        int mid = (left + right) / 2;
    
        mergeSortHelper(left, mid);
        mergeSortHelper(mid + 1, right);
    
        merge(left, mid, right);
    }
    
    private void merge(int left, int mid, int right) {
    
        int n1 = mid - left + 1;
        int n2 = right - mid;
    
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
    
        for (int i = 0; i < n1; i++) {
            leftArr[i] = data[left + i];
        }
    
        for (int j = 0; j < n2; j++) {
            rightArr[j] = data[mid + 1 + j];
        }
    
        int i = 0;
        int j = 0;
        int k = left;
    
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                data[k] = leftArr[i];
                i++;
            } else {
                data[k] = rightArr[j];
                j++;
            }
            k++;
        }
    
        while (i < n1) {
            data[k] = leftArr[i];
            i++;
            k++;
        }
    
        while (j < n2) {
            data[k] = rightArr[j];
            j++;
            k++;
        }
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

    public double stDev() {
        if (size == 0) return 0;
    
        double mean = average();
        double sumOfDiff = 0;
    
        for (int i = 0; i < size; i++) {
            double x = (data[i] - mean) * (data[i] - mean);
            sumOfDiff += x;
        }
    
        return Math.sqrt(sumOfDiff / size);
    }

    public int minVal() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
    
        if (isSorted()) {
            return data[0];
        }
    
        int min = data[0];
    
        for (int i = 1; i < size; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }
    
        return min;
    }

    public int maxVal() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
    
        if (isSorted()) {
            return data[size - 1];
        }
    
        int max = data[0];
    
        for (int i = 1; i < size; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
    
        return max;
    }

    public double median() {
        if (isEmpty()) {
            return 0;
        }
    
        int[] arr = toArray(); // copy of list
    
        // sort the copy
        java.util.Arrays.sort(arr);
    
        if (size % 2 == 1) {
            return arr[size / 2];
        } else {
            return (arr[size / 2 - 1] + arr[size / 2]) / 2.0;
        }
    }

    public double percentile(double p) {
        if (p < 0 || p > 100) {
            throw new IllegalArgumentException("Percentile must be between 0 and 100");
        }

        if (!isSorted()) {
            mergeSort();
        }
        double index = (p / 100.0) * (size - 1);
        int lower = (int) index;
        int upper = lower + 1;

        if (upper >= size) {
            return data[lower];
        }
        double fraction = index - lower;
        return data[lower] + fraction * (data[upper] - data[lower]);
    }

    public double avgGrowthRate() {
        if (size < 2) {
            return 0;
        }
        int count = 0;
        double growthR = 0;

        for (int i = 1; i < size; i++) {
            if (data[i - 1] == 0) continue;

            growthR += (double)(data[i] - data[i - 1]) / data[i - 1] * 100;
            count++;
        }

        if (count == 0) return 0;

        return growthR / count;
    }

    public IntArrayList forecast(int periods) {
        if (size == 0) {
            throw new IllegalStateException("Cannot forecast with empty dataset");
        }
        if (periods <= 0) {
            throw new IllegalArgumentException("Periods must be greater than 0");
        }
        double avgGrowth = avgGrowthRate() / 100.0;
        IntArrayList forecastV = new IntArrayList();
        for (int i = 0; i < size; i++) {
            forecastV.add(data[i]);
        }
        int lastValue = data[size - 1];
        for (int i = 0; i < periods; i++) {
            lastValue = (int)(lastValue * (1 + avgGrowth));
            forecastV.add(lastValue);
        }
    
        return forecastV;
    }

    public double volatility() {
        if (average() == 0) return 0;
        return (stDev() / average()) * 100;
    }

    public double iqr() {
        return percentile(75) - percentile(25);
    }

    public IntArrayList findOutliersIQR() { //for skewed datasets
        if (!isSorted()) {
            mergeSort();
        }

        double lowerBound = percentile(25) - 1.5 * iqr();
        double upperBound = percentile(75) + 1.5 * iqr();

        IntArrayList outliers = new IntArrayList();
        for (int i = 0; i < size; i++) {
            if (data[i] < lowerBound || data[i] > upperBound) {
                outliers.add(data[i]);
            }
        }
        return outliers;
    }

    public IntArrayList findOutliersStdDev() { //for symmetrical datasets and distrubutions
        double mean = average();
        double stdDev = stDev();
        double lowerBound = mean - 2 * stdDev;
        double upperBound = mean + 2 * stdDev;

        IntArrayList outliers = new IntArrayList();
        for (int i = 0; i < size; i++) {
            if (data[i] < lowerBound || data[i] > upperBound) {
                outliers.add(data[i]);
            }
        }
        return outliers;
    }



    public IntArrayList valuesAbove(int threshold) {
        IntArrayList aboveThreshold = new IntArrayList();
        for (int i = 0; i < size; i++) {
            if (data[i] > threshold) {
                aboveThreshold.add(data[i]);
            }
        }
        return aboveThreshold;
    }

    public IntArrayList movingAverage(int period) {
        if (period <= 0 || period > size) {
            throw new IllegalArgumentException("Invalid period");
        }
    
        IntArrayList smoothedData = new IntArrayList();
    
        for (int i = 0; i <= size - period; i++) {
            int total = 0;
    
            for (int j = i; j < i + period; j++) {
                total += data[j];
            }
    
            smoothedData.add(total / period);
        }
    
        return smoothedData;
    }

    public boolean isSkewed() {
        if (size == 0) {
            return false;
        }
    
        double mean = average();
        double median = percentile(50);
        double sd = stDev();
    
        if (sd == 0) {
            return false;
        }
    
        double skewnessCoefficient = 3 * (mean - median) / sd;
    
        return Math.abs(skewnessCoefficient) > 1;
    }

    public static void main(String[] args) {
        // IntArrayList list = new IntArrayList();

        // list.add(5);
        // list.add(10);
        // list.add(15);

        // for(int x : list){
        //     System.out.println(x);
        // }

        // list.add(5);
        // list.add(1);
        // list.add(9);
        // list.add(2);
        // list.add(6);

        // list.mergeSort();

        // System.out.println(list);
        IntArrayList list1 = new IntArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        System.out.println("List1: " + list1);
        System.out.println("isSorted: " + list1.isSorted());
        System.out.println("Standard Deviation: " + list1.stDev());
        System.out.println();


        // TEST 2: Unsorted list
        IntArrayList list2 = new IntArrayList();
        list2.add(5);
        list2.add(1);
        list2.add(8);
        list2.add(3);

        System.out.println("List2: " + list2);
        System.out.println("isSorted: " + list2.isSorted());
        System.out.println("Standard Deviation: " + list2.stDev());
        System.out.println();


        // TEST 3: Single element
        IntArrayList list3 = new IntArrayList();
        list3.add(10);

        System.out.println("List3: " + list3);
        System.out.println("isSorted: " + list3.isSorted());
        System.out.println("Standard Deviation: " + list3.stDev());
        System.out.println();


        // TEST 4: Identical numbers (stdev should be 0)
        IntArrayList list4 = new IntArrayList();
        list4.add(7);
        list4.add(7);
        list4.add(7);
        list4.add(7);

        System.out.println("List4: " + list4);
        System.out.println("isSorted: " + list4.isSorted());
        System.out.println("Standard Deviation: " + list4.stDev());
    }

}