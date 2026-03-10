# IntArrayList

`IntArrayList` is a custom implementation of a dynamic array in Java, similar to Java's built-in `ArrayList` class but specifically for integers (`int`). It provides efficient storage, manipulation, and retrieval of integer elements with a variety of utility functions.  

## Features

- Dynamically resizes as elements are added.  
- Provides standard list operations: add, remove, get, set, and contains.  
- Includes statistical and array analysis methods.  
- Supports iteration using the enhanced for-each loop.  

---

## Constructor

- **`IntArrayList()`**  
  Creates an empty list with an initial capacity of 10.  

- **`IntArrayList(int capacity)`**  
  Creates an empty list with a specified initial capacity.  

---

## Core Methods

- **`void add(int value)`**  
  Adds a value to the end of the list.  

- **`void add(int index, int value)`**  
  Inserts a value at a specific index, shifting elements to the right.  

- **`int removeAt(int index)`**  
  Removes the element at a specified index and shifts remaining elements to the left. Returns the removed value.  

- **`boolean removeVal(int val)`**  
  Removes the first occurrence of the specified value in the list. Returns true if removed.  

- **`int get(int index)`**  
  Returns the value at the specified index.  

- **`void set(int index, int val)`**  
  Sets the value at the specified index to the new value.  

- **`int size()`**  
  Returns the number of elements currently stored in the list.  

- **`boolean isEmpty()`**  
  Returns true if the list has no elements.  

- **`boolean contains(int val)`**  
  Checks if the list contains the specified value.  

- **`int indexOf(int val)`**  
  Returns the index of the first occurrence of a value, or -1 if not found.  

- **`void clear()`**  
  Removes all elements from the list and resets its size.  

---

## Utility Methods

- **`int[] toArray()`**  
  Returns a new array containing all elements in the list.  

- **`boolean equals(Object other)`**  
  Checks whether another `IntArrayList` contains the same values in the same order.  

- **`boolean isSorted()`**  
  Returns true if the list is sorted in ascending order.  

- **`int minVal()`**  
  Returns the minimum value in the list. Efficiently returns the first element if the list is already sorted.  

- **`int maxVal()`**  
  Returns the maximum value in the list. Efficiently returns the last element if the list is already sorted.  

- **`double median()`**  
  Returns the median of the list. Sorts a copy of the array if the list is unsorted.  

- **`int sum()`**  
  Returns the sum of all elements in the list.  

- **`double average()`**  
  Returns the arithmetic mean of the list.  

- **`double StDev()`**  
  Returns the standard deviation of the values in the list.  

---

## Iteration

`IntArrayList` implements **`Iterable<Integer>`**, allowing it to be used in enhanced for-each loops:

```java
IntArrayList list = new IntArrayList();
list.add(5);
list.add(10);
for (int value : list) {
    System.out.println(value);
}
