/* Invariants:
	 addLast: The next item we want to add, will go into position size
	 getLast: The item we want to return is in position size - 1
	 size: The number of items in the list should be size	*/

public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextfirst;

    private int nextlast;

    /**
     * Creates an empty list.
     */


    public ArrayDeque() {
        items = (Item[]) new Object[100];
        nextfirst = ((items.length) / 2) - 1;
        nextlast = items.length / 2;
        size = 0;

    }


    /**
     * Resizes the underlying array to the target capacity.
     */


    private void resize(int capacity) {
        int first = index(0);
        int last = index(size - 1);
        Item[] temp = (Item[]) new Object[capacity];
        if (first > last) {
            int r = (items.length - 1) - (nextlast - 1);
            System.arraycopy(items, nextlast, temp, 0, r);
            System.arraycopy(items, 0, temp, r, nextlast);
            items = temp;
            nextfirst = -1;
            nextlast += r;

        } else if (last > first) {
            System.arraycopy(items, first, temp, 0, size);
            items = temp;
            nextfirst = -1;
            nextlast = size;

        }


    }


    /**
     * Inserts X into the back of the list.
     */


    public void addLast(Item x) {
        if (size == items.length) {
            //should resize
            resize(size * 2);

        }

        items[nextlast] = x;
        nextlast = (nextlast + 1) % items.length;
        size++;

    }


    public void addFirst(Item x) {
        if (size == items.length) {
            //should resize
            resize(size * 2);
        }
        if (nextfirst < 0) {
            //plus sign because nextfirst is negative
            items[items.length + nextfirst] = x;
            size++;

        } else {
            items[nextfirst] = x;
            size++;

        }
        nextfirst--;
    }

    // returns the index of a item (helper method )
    private int index(int i) {
        int index = nextfirst + 1 + i;
        if (index < 0) {
            index = items.length + index;
        }
        return index;
    }

    // returns ith item starts from 0
    public Item get(int i) {
        int index = index(i);
        return items[index];

    }


    public int size() {
        return size;

    }

    /**
     * Deletes item from back of the list and
     * returns deleted item.
     */

    public Item removeLast() {
        if ((float) size / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        int index = index(size - 1);
        Item x = items[index];
        items[index] = null;
        nextlast--;
        return x;
    }

    // removes first item in the list and returns it
    public Item removeFirst() {
        if ((float) size / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        int index = index(0);
        Item x = items[index];
        items[index] = null;
        nextfirst++;
        return x;
    }


    // checks list if empty
    public boolean isEmpty() {
        if (size == 0) {
            return true;

        } else {
            return false;

        }

    }

    // prints list in order
    public void printD() {
        int first = index(0);
        int last = index(size - 1);
        if (first > last) {
            for (int i = first; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int j = 0; j <= last; j++) {
                System.out.print(items[j] + " ");
            }
            System.out.println();
            return;
        } else {
            for (int i = first; i <= last; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println();
            return;
        }
    }

    public static void main(String[] args) {

        ArrayDeque intarr = new ArrayDeque<Integer>();
        for (int i = 0; i < 15; i++) {
            intarr.addLast(i);

        }
        intarr.removeFirst();

    }
}
