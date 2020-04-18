public class LinkedListDeque<gentype> {
    private class Node {
        public gentype item;
        public Node next;
        public Node prev;

        public Node(gentype i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
            //System.out.println(size);
        }

    }

    /* The first item (if it exists) is at sentinel.next. */
    private Node sentinel;
    private Node sentb;
    private int size;

    /**
     * Creates an empty SLList.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentb = new Node(null, null, sentinel);
        sentinel.next = sentb;
        size = 0;
    }

    public LinkedListDeque(gentype x) {
        // initiates sentinel then attaches x and initiates sentb b = back.
        sentinel = new Node(x, null, null);
        sentb = new Node(x, null, sentinel.next);
        sentinel.next = new Node(x, sentb, sentinel);
        sentb.prev = sentinel.next;
        size = 1;
    }

    public LinkedListDeque(LinkedListDeque<gentype> other) {
        sentinel = new Node(null, null, null);
        sentb = new Node(null, null, sentinel);
        sentinel.next = sentb;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            this.addLast(other.getRecursive(i));
        }

    }


    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentb) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.println();

    }

    /**
     * Adds x to the front of the list.
     */
    public void addFirst(gentype x) {
        if (sentinel.item == null) {
            sentinel.item = x;
            sentb.item = x;
            sentinel.next = sentb;
            sentb.prev = sentinel;
        }
        sentinel.next = new Node(x, sentinel.next, sentinel);
        // makes the now seconde item of the list prev pointer point bak to the first
        sentinel.next.next.prev = sentinel.next;
        size = size + 1;
    }

    /**
     * Returns the first item in the list.
     */
    public boolean isEmpty() {
        if (sentinel.next == sentb) {
            return true;
        } else {
            return false;
        }
    }

    public gentype getFirst() {
        gentype p = sentinel.next.item;
        return p;
    }

    /**
     * Adds x to the end of the list.
     */
    public void addLast(gentype x) {
        if (sentinel.item == null) {
            sentinel.item = x;
            sentb.item = x;
            sentinel.next = sentb;
            sentb.prev = sentinel;
        }
        size++;
        Node addition = new Node(x, sentb, sentb.prev);
        sentb.prev.next = addition;
        sentb.prev = addition;
    }

    /**
     * Returns the size of the list.
     */
    public int size() {
        return size;
    }

    public gentype removeFirst() {
        Node b = sentinel.next;
        b.next.prev = sentinel;
        sentinel.next = b.next;
        size--;
        return b.item;

    }

    public gentype removeLast() {
        Node Last = sentb.prev;
        Node newLast = Last.prev;
        newLast.next = sentb;
        sentb.prev = newLast;
        return Last.item;

    }

    public gentype get(int index) {
        if (sentinel.next == null) {
            return null;
        }
        int i = 0;
        Node ptr = sentinel.next;
        while (i < index) {
            ptr = ptr.next;
            if (ptr == null) {
                return null;
            }
            i++;
        }
        return ptr.item;
    }

    private Node getR(int index) {
        if (index == 0) {
            return sentinel.next;
        } else {
            return getR(index - 1).next;
        }


    }

    public gentype getRecursive(int index) {
        return getR(index).item;
    }


    public static void main(String[] args) {
        // Creates a list of one integer, namely 10
        LinkedListDeque<String> L = new LinkedListDeque<String>("hello");
        L.addLast("every body");
        L.addLast(", :)");
        L.printDeque();
        String x = L.getRecursive(1);
        System.out.println(x);
    }
}
