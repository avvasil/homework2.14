package pro.sky;

import pro.sky.exceptions.ArrayIsFullException;
import pro.sky.exceptions.ItemIsAbsentException;
import pro.sky.exceptions.NullItemException;
import pro.sky.exceptions.OutOfBoundsExceptions;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList implements StringList {

    private String[] myArrayList;
    private int myArrayListSize = 10;
    int size;

    public MyArrayList() {
        this.myArrayList = new String[myArrayListSize];
    }

    @Override
    public String toString() {
        return "MyArrayList" + Arrays.toString(myArrayList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList that = (MyArrayList) o;
        return Arrays.equals(myArrayList, that.myArrayList);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(myArrayList);
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new NullItemException("Trying to manipulate with null.");
        }
        if (myArrayList.length == size) {
            throw new ArrayIsFullException("No free capacity in the array");
        }
        myArrayList[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        String[] tempArray = new String[myArrayListSize];
        if (item == null) {
            throw new NullItemException("Trying to manipulate with null.");
        }
        if (myArrayList.length == size) {
            throw new ArrayIsFullException("No free capacity in the array");
        }
        if (index < 0 || index > myArrayListSize) {
            throw new OutOfBoundsExceptions("Index is out of range");
        }
        for (int i = 0; i < myArrayList.length; i++) {
            tempArray[i] = myArrayList[i];
        }
        if (index == 0) {
            myArrayList[index + 1] = tempArray[0];
        }
        for (int k = index + 1; k < myArrayList.length; k++) {
            myArrayList[k] = tempArray[k - 1];
        }
        myArrayList[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (item == null) {
            throw new NullItemException("Trying to manipulate with null.");
        }
        if (myArrayList.length == size) {
            throw new ArrayIsFullException("No free capacity in the array");
        }
        if (index < 0 || index > myArrayListSize) {
            throw new OutOfBoundsExceptions("Index is out of range");
        }
        myArrayList[index] = item;
        size++;
        return item;
    }

    @Override
    public String remove(String item) {
        boolean itemExists = false;
        int indexNumberToRemove = 0;
        String[] tempArray = new String[myArrayListSize];
        if (item == null) {
            throw new NullItemException("Trying to manipulate with null.");
        }
        for (int i = 0; i < myArrayList.length; i++) {
            if (Objects.equals(myArrayList[i], item)) {
                itemExists = true;
                indexNumberToRemove = i;
                for (int j = 0; j < myArrayList.length; j++) {
                    tempArray[j] = myArrayList[j];
                }
                if (indexNumberToRemove == 0) {
                    myArrayList[indexNumberToRemove + 1] = tempArray[0];
                }
                for (int k = indexNumberToRemove + 1; k < myArrayList.length; k++) {
                    myArrayList[k - 1] = tempArray[k];
                }
            }
        }
        if (!itemExists) {
            throw new ItemIsAbsentException("Item is absent.");
        }
        return item;
    }

    @Override
    public String remove(int index) {
        String[] tempArray = new String[myArrayListSize];
        if (index < 0 || index > myArrayListSize) {
            throw new OutOfBoundsExceptions("Index is out of range");
        }
        String deletingItem = myArrayList[index];
        for (int i = 0; i < myArrayList.length; i++) {
            tempArray[i] = myArrayList[i];
        }
        if (index == 0) {
            myArrayList[index + 1] = tempArray[0];
        }
        for (int k = index + 1; k < myArrayList.length; k++) {
            myArrayList[k - 1] = tempArray[k];
        }
        return deletingItem;
    }

    @Override
    public boolean contains(String item) {
        if (item == null) {
            throw new NullItemException("Trying to manipulate with null.");
        }
        for (int i = 0; i < myArrayList.length; i++) {
            if (myArrayList[i] == item) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        if (item == null) {
            throw new NullItemException("Trying to manipulate with null.");
        }
        for (int i = 0; i < myArrayList.length; i++) {
            if (Objects.equals(myArrayList[i], item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) {
            throw new NullItemException("Trying to manipulate with null.");
        }
        for (int i = myArrayList.length - 1; i >= 0; i--) {
            if (Objects.equals(myArrayList[i], item)) {
                return ((myArrayList.length - 1) - i);
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index > myArrayListSize) {
            throw new OutOfBoundsExceptions("Index is out of range");
        }
        return myArrayList[index];
    }

    @Override
    public boolean equals(String[] otherList) {
        if (otherList == null) {
            throw new NullItemException("Trying to manipulate with null.");
        }
        if (Arrays.equals(myArrayList, otherList)) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        int numberOfItems = 0;
        for (int i = 0; i < myArrayList.length; i++) {
            if (myArrayList[i] != null) {
                numberOfItems++;
            }
        }
        return numberOfItems;
    }

    @Override
    public boolean isEmpty() {
        int numberOfItems = 0;
        for (int i = 0; i < myArrayList.length; i++) {
            if (myArrayList[i] != null) {
                numberOfItems++;
            }
        }
        if (numberOfItems == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < myArrayList.length; i++) {
            myArrayList[i] = null;
        }
    }

    @Override
    public String[] toArray() {
        String[] newArray = new String[myArrayListSize];
        for (int i = 0; i < myArrayList.length; i++) {
            newArray[i] = myArrayList[i];
        }
        return newArray;
    }
}
