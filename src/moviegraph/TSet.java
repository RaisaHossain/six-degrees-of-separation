package moviegraph;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Tree set version of set.
 * @author Raisa Hossain
 */
public class TSet<Key extends Comparable<Key>> implements Iterable<Key>{
    private TreeSet<Key> set;

    /**
     * Initializes an empty set.
     */
    public TSet() {
        set = new TreeSet<Key>();
    }

    /**
     * Initializes a new set that is a copy of the specified set.
     *
     * @param s the set to copy
     */
    public TSet(TSet<Key> s) {
        set = new TreeSet<Key>(s.set);
    }

    /**
     * Checks if set contains key
     *
     * @param key key
     * @return true if set contains given key.
     * @throws IllegalArgumentException if key is null
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Called contains() with 'null' key");
        return set.contains(key);
    }

    /**
     * Returns the number of key-value pairs in symbol table
     *
     * @return size of symbol table
     */
    public int size() {
        return set.size();
    }

    /**
     * Checks to see if symbol table is empty
     *
     * @return true if table is empty; false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns all of the keys in this set as an iterator.
     * @return iterator to all of the keys in this set
     */
    public Iterator<Key> iterator() {
        return set.iterator();
    }

    public void add(Key key) {
        if(key == null) {
            throw new IllegalArgumentException("Called add() with a null key");
        }
        set.add(key);
    }
}
