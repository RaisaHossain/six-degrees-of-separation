package moviegraph;

import java.util.*;

/**
 * Sorted symbol table implementation using Tree Maps.
 * @author Raisa Hossain
 *
 * @param <Key> generic type of keys in the symbol table
 * @param <Value> generic type of values in this symbol table
 */
public class SymTab <Key extends Comparable<Key>, Value> {

    private TreeMap<Key, Value> symTab;

    /*Initialize an empty symbol table. */
    public SymTab() {
        this.symTab = new TreeMap<Key, Value>();
    }

    /**
     * get value associated with key from symbol table.
     * @param key key
     * @return the value associated with given key from symbol table.
     * @throws IllegalArgumentException if key is null
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Called get() with 'null' key");
        return symTab.get(key);
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("Called put with 'null' key");
        }
        if (val == null) {
            symTab.remove(key);
        }
        else{
            symTab.put(key, val);
        }

    }

    /** 
     * Checks if symbol table contains key
     * @param key key
     * @return true if symbol table contains given key.
     * @throws IllegalArgumentException if key is null
     */
    public boolean contains (Key key) {
        if (key == null) throw new IllegalArgumentException("Called contains() with 'null' key");
        return symTab.containsKey(key);
    }

    /**
     * Returns the number of key-value pairs in symbol table
     * @return size of symbol table
     */
    public int size() {
        return symTab.size();
    }

    /**
     * Checks to see if symbol table is empty
     * @return true if table is empty; false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns all the keys that are in this symbol table.
     * @return all the keys from this symbol table
     */
    public Iterable<Key> keys() {
        return symTab.keySet();
    }

    /**
     * Return an iterator to all of the keys that are this symbol table.
     * @return iterator to all the keys that are in this symbol table.
     * @deprecated Replaced by {@link #keys()}.
     */
    @Deprecated
    public Iterator<Key> iterator() {
        return symTab.keySet().iterator();
    }
}

