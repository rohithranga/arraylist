Built by: Rohith Ranga, Anu Vadera, Ananya Agarwal, and Josh Anderson

---
# IntArrayList

`IntArrayList` is a custom implementation of a dynamic array for integers in Java, similar to Java's built-in `ArrayList<Integer>`, but optimized for primitive `int` values. The class automatically resizes when capacity is exceeded and provides a wide range of utility functions for data manipulation, statistical analysis, and forecasting.

This implementation supports iteration, sorting, statistical calculations, outlier detection, and time-series style analysis.

---

# Core Features

## Dynamic Array
The list automatically grows when it reaches capacity using a reallocation strategy that doubles the array size.

## Iterable Support
The class implements `Iterable<Integer>`, allowing the list to be used in enhanced for-loops.

Example:

```java
for (int value : list) {
    System.out.println(value);
}
```

---

# Constructors

### `IntArrayList()`
Creates a list with an initial capacity of 10.

### `IntArrayList(int capacity)`
Creates a list with a user-defined initial capacity.

---

# Basic List Operations

### `add(int value)`
Adds a value to the end of the list.

### `add(int index, int value)`
Inserts a value at a specific index, shifting elements to the right.

### `removeAt(int index)`
Removes and returns the value at a specific index.

### `removeVal(int val)`
Removes the first occurrence of a value.

### `get(int index)`
Returns the value at a given index.

### `set(int index, int val)`
Replaces the value at a given index.

### `size()`
Returns the number of elements stored in the list.

### `isEmpty()`
Returns true if the list contains no elements.

### `clear()`
Removes all elements from the list.

---

# Searching and Utility Methods

### `contains(int val)`
Returns true if the list contains the given value.

### `indexOf(int val)`
Returns the index of the first occurrence of a value, or -1 if not found.

### `toArray()`
Returns a copy of the list as a standard Java array.

### `equals(Object other)`
Compares two `IntArrayList` objects and returns true if they contain identical values in the same order.

### `toString()`
Returns a formatted string representation of the list.

---

# Sorting

### `mergeSort()`
Sorts the list using the Merge Sort algorithm.

Merge Sort is a divide-and-conquer sorting algorithm with time complexity:

```
O(n log n)
```

### `isSorted()`
Returns true if the list is already sorted in ascending order.

---

# Statistical Functions

### `sum()`
Returns the sum of all values in the list.

### `average()`
Returns the average (mean) of the values.

### `StDev()`
Calculates the standard deviation of the dataset.

### `median()`
Returns the median value of the dataset.

### `percentile(double p)`
Returns the value at a given percentile using interpolation.

Examples:

```
percentile(25) → first quartile
percentile(50) → median
percentile(75) → third quartile
```

### `iqr()`
Returns the interquartile range:

```
IQR = Q3 - Q1
```

### `minVal()`
Returns the smallest value in the list.

### `maxVal()`
Returns the largest value in the list.

---

# Outlier Detection

### `findOutliersIQR()`
Detects outliers using the **Interquartile Range method**, which is ideal for skewed datasets.

Outliers are defined as values outside:

```
Q1 - 1.5 × IQR
Q3 + 1.5 × IQR
```

### `findOutliersStdDev()`
Detects outliers using the **standard deviation method**, which works best for symmetric distributions.

Outliers are defined as values outside:

```
mean ± 2 × standard deviation
```

---

# Data Analysis Functions

### `valuesAbove(int threshold)`
Returns a new list containing all values greater than a given threshold.

### `movingAverage(int period)`
Calculates a moving average over a specified period, often used for smoothing time-series data.

Example:

```
period = 3
[10, 20, 30, 40] → [20, 30]
```

---

# Forecasting and Growth Analysis

### `avgGrowthRate()`
Calculates the average percentage growth rate between consecutive values.

Formula:

```
growth = (current - previous) / previous × 100
```

### `forecast(int periods)`
Generates a forecast of future values based on the average growth rate.

The method returns a new `IntArrayList` containing the original data plus predicted values.

---

# Volatility Analysis

### `volatility()`
Calculates volatility as a percentage using:

```
volatility = (standard deviation / mean) × 100
```

This metric is commonly used in financial analysis to measure variability.

---

# Example Usage

```java
IntArrayList list = new IntArrayList();

list.add(10);
list.add(20);
list.add(30);
list.add(40);

System.out.println(list.average());
System.out.println(list.median());
System.out.println(list.percentile(75));

list.mergeSort();

IntArrayList outliers = list.findOutliersIQR();
```

---

# Summary

This class provides a full-featured dynamic array implementation with additional capabilities including:

- Dynamic resizing
- Sorting algorithms
- Statistical analysis
- Outlier detection
- Forecasting
- Time-series smoothing
- Data filtering

It is designed to combine the functionality of a traditional list structure with tools commonly used in data analysis.
