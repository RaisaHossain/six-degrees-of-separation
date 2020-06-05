package moviegraph;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implemented singly linked list stack algorithm.
 * @author Raisa Hossain
 *
 * @param <E> generic type
 */
public class Stack<E> implements Iterable<E> {
    /*top of stack*/
    private Node<E> top;

    /*size of stack*/
    private int n;

    /* helper linked list class*/
    private static class Node<E> {
        private E item;
        private Node<E> next;
    }
    /**
     * Initializes an empty stack.
     */
    public Stack() {
        top = null;
        n = 0;
    }

    /**
     * Returns true if this stack is empty.
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Returns the number of items in this stack.
     *
     * @return the number of items in this stack
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this stack.
     *
     * @param  item the item to add
     */
    public void push(E item) {
        Node<E> oldtop = top;
        top = new Node<E>();
        top.item = item;
        top.next = oldtop;
        n++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public E pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        E item = top.item;        // save item to return
        top = top.next;           // delete top node
        n--;
        return item;              // return the saved item
    }


    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return top.item;
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in this stack in LIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (E item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }


    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order
     */
    public Iterator<E> iterator() {
        return new ListIterator(top);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<E> {
        private Node<E> current;

        public ListIterator(Node<E> top) {
            current = top;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E item = current.item;
            current = current.next;
            return item;
        }
    }
}


