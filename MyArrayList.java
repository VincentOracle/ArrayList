
/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <
 * Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/
 * >
 * Signed,
 * Author: YOUR NAME HERE
 * Penn email: <YOUR-EMAIL-HERE@seas.upenn.edu>
 * Date: <YYYY-MM-DD>
 */


/*Importing the Java util package */
import java.util.Arrays;

public class MyArrayList<E> {

    /*
     * Do not change this initial capacity; it is used by our test cases
     */
    private static final int INITIAL_CAPACITY = 4;

    /*
     * These are protected so that test cases can access them. Please do not change
     * the visibility of these fields!
     */
    protected Object[] data;
    protected int size = 0;
/*Step1:Java generics */
    public MyArrayList() {
        data = new Object[INITIAL_CAPACITY];
    }

    public MyArrayList(E[] arr) {
        if (arr == null || arr.length == 0) {
            data = new Object[INITIAL_CAPACITY];
        } else {
            size = arr.length;
            data = Arrays.copyOf(arr, Math.max(arr.length, INITIAL_CAPACITY));
        }
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else
            return (E) data[index];
    }

    private void increaseCapacity() {
        Object[] newData = new Object[Math.max(2 * data.length, INITIAL_CAPACITY)];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    /*
     * This method adds the element to the list. Except for modifying it to use Java
     * Generics, DO NOT OTHERWISE CHANGE THIS METHOD as it is used in testing your
     * code.
     */
    public void add(E value) {
        if (size == data.length) {
            increaseCapacity();
        }
        data[size++] = value;
    }

    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == data.length) {
            increaseCapacity();
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }
/*Step 2. Remove Method */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E target = (E) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        /*Step 5. Create constructor to initialize underlying array */
        size--;
        data[size] = null;
        if (size <= data.length / 4) {
            Object[] newData = new Object[data.length / 2];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        return target;
    }
/*remove  */
    public boolean remove(E obj) {
        for (int i = 0; i < size; i++) {
            if (obj == data[i] || (data[i] != null && data[i].equals(obj))) {
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
     /*Step 3. Shrink array when it is too large */
                size--;
                data[size] = null;
                if (size <= data.length / 4) {
                    Object[] newData = new Object[data.length / 2];
                    System.arraycopy(data, 0, newData, 0, size);
                    data = newData;
                }
                return true;
            }
        }
        return false;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + data[i]);
        }
    }

    public boolean contains(E obj) {
        for (int i = 0; i < size; i++) {
            if (obj == data[i] || (data[i] != null && data[i].equals(obj)))
                return true;
        }
        return false;
    }

    /*
     * Need to implement this in Step 4
     Step 4. Implement set(index, value) method
     */
    public E set(int index, E obj) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E old = (E) data[index];
        data[index] = obj;
        return old;
    }

}