package lt.ziltom.zilvinastomkevicius.projektas;

import java.util.Objects;
import java.util.function.Supplier;

public class DynamicArray<E> {

    private E[] items;
    private int size;
    private final int growFactor = 2;

    public DynamicArray() {
        this.size = 0;
        items = (E[]) new Game[4];
    }

    public int count() {
        return size;
    }

    public void add(E item) {
        if(size + 1 > items.length) {
            E[] newArray = (E[]) new Object[items.length * growFactor];
            System.arraycopy(items, 0, newArray, 0, items.length);
            items = newArray;
        }
        items[size] = item;
        size++;
    }

    public void clear() {
        items = (E[]) new Object[2];
        size = 0;
    }

    public boolean contains(E item) {
        for (E i : items) {
            if(i.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public E get(int position) {
        for(int i = 0; i < items.length; i++) {
            if(i == position) {
                return items[i];
            }
        }

        return null;
    }
}
