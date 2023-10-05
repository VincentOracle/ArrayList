
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

    public MyArrayList() {
        data = new Object[INITIAL_CAPACITY];
    }

    /*
     * Need to implement this in step 5
     */
    public MyArrayList(E[] arr) {
        if (arr == null || arr.length == 0) {
            data = new Object[INITIAL_CAPACITY];
        } else {
            data = new Object[Math.max(arr.length, INITIAL_CAPACITY)];
            System.arraycopy(arr, 0, data, 0, arr.length);
            size = arr.length;
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

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E target = (E) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;
        if (size * 4 <= data.length) {
            Object[] newData = new Object[data.length / 2];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        return target;
    }

    /*
     * Need to implement this in Step 2.
     */
    public boolean remove(E obj) {
        for (int i = 0; i < size; i++) {
            if (obj == data[i] || (data[i] != null && data[i].equals(obj))) {
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
                size--;
                data[size] = null;
                if (size * 4 <= data.length) {
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
     */
    public E set(int index, E obj) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldVal = (E) data[index];
        data[index] = obj;
        return oldVal;
    }

}