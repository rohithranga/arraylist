public class IntArrayList2 {

    private int[] data;
    private int size; //how many real values have been stored

    public IntArrayList2() { //initializing the array
        data = new int[10];
        size = 0;
    }

    public IntArrayList2(int capacity) {//initializing the array for any possible capacity
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

    public double percentile(double p) {
        if (p < 0 || p > 100) {
            throw new IllegalArgumentException("Percentile must be between 0 and 100");
        }

        if (!isSorted()) {
            mergeSort(0, size - 1);
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
        double growthR = 0;
        for (int i = 1; i < size; i++) {
            totalGrowth += (double)(data[i] - data[i - 1]) / data[i - 1] * 100;
        }
        return totalGrowth / (size - 1);
    }

    public IntArrayList forecast(int periods) {
        if (periods <= 0) {
            throw new IllegalArgumentException("Periods must be greater than 0");
        }
        double avgGrowth = avgGrowthRate() / 100.0;

        IntArrayList forecastV = new IntArrayList();
        for (int i = 0; i < size; i++) {
            result.add(data[i]);
        }
        int lastValue = data[size - 1];
        for (int i = 0; i < periods; i++) {
            lastValue = (int)(lastValue * (1 + avgGrowth));
        forecastV.add(lastValue);
        }
        return forecastV;

    }

    public double volatility() {
        return (standardDeviation() / average()) * 100;
    }

    public double iqr() {
        return percentile(75) - percentile(25);
    }

    public IntArrayList findOutliersIQR() { //for skewed datasets
        if (!isSorted()) {
            mergeSort(0, size - 1);
        }
        double lowerBound = q1 - 1.5 * data.iqr();
        double upperBound = q3 + 1.5 * data.iqr();

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
        double stdDev = standardDeviation();
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
                result.add(data[i]);
            }
        }
        return result;
    }

    public IntArrayList movingAverage(int period) {
        IntArrayList smoothedData = new IntArrayList();
        for (int i = 0; i <= size - period; i++) {
            int total = 0;
            for (int j = i; j < i + period; j++) {
                total += data[j];
            }
            smoothedData.add(total / window);
        }
        return smoothedData;
    }

    }
    










