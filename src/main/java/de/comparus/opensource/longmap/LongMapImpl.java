package de.comparus.opensource.longmap;

import java.lang.reflect.Array;

public class LongMapImpl<V> implements LongMap<V> {
    
    private static final int CAPACITY_BUCKETS = 1 << 3;
    private static final float MAX_LOAD_FACTOR = 0.75f;
    private int buckets;
    private int size;
    private Node<V>[] nodes;
    
    public LongMapImpl() {
        clear();
    }
    
    public LongMapImpl(int buckets) {
        this.size = 0;
        this.buckets = buckets;
        nodes = new Node[buckets];
    }
    
    public V put(long key, V value) {
    
        V val = putInto(nodes, key, value, true);
        if(verifyLoadFactor()) {
            rehashMap();
        }
        return val;
    }
    
    private V putInto(Node[] nodes, long key, V value, boolean sizeFactor) {
        int pos = getBucketNumber(key);
        V val = nodes[pos] == null ? putIntoBucket(nodes, pos, key, value, sizeFactor)
                : (V) putIntoBucketList(nodes[pos], key, value, sizeFactor);
        return val;
    }
    
    private int getBucketNumber(long key) {
        return Long.hashCode(key) & buckets - 1;
    }
    
    private V putIntoBucket(Node[] nodes, int pos, long key, V value, boolean sizeFactor) {
        nodes[pos] = new Node<>(key, value, null);
        if (sizeFactor){
            size++;
        }
        return (V) nodes[pos].value;
    }
    
    private V putIntoBucketList(Node<V> head, long key, V value, boolean sizeFactor) {
        Node<V> node = head;
        for(; node != null; head = node, node = node.next ) {
            if (node.key == key) {
                node.value = value;
                return node.value;
            }
        }
        node = new Node(key, value, null);
        head.next = node;
        if (sizeFactor){
            size++;
        }
        return node.value;
    }
    
    private boolean verifyLoadFactor() {
        return size > buckets * MAX_LOAD_FACTOR;
    }
    
    private void rehashMap() {
        Node<V>[] newNodes = new Node[buckets <<= 1];
        for (int i = 0; i < buckets >> 1; i++) {
            Node<V> node = nodes[i];
            for(; node != null; node = node.next ) {
                putInto(newNodes, node.key, node.value, false);
            }
        }
        nodes = newNodes;
    }
    
    public V get(long key) {
        Node<V> node;
        return (node = getNode(key)) == null ? null : node.value;
    }
    
    private Node<V> getNode(long key) {
        int pos = getBucketNumber(key);
        Node<V> node = nodes[pos];
        while (node != null) {
            if (node.key == key) {
                return node;
            }
            node = node.next;
        }
        return null;
    }
    
    public V remove(long key) {
        int pos = getBucketNumber(key);
        if(nodes[pos] != null){
            return removeValue(key, pos);
        }
        return null;
    }
    
    private V removeValue(long key, int pos) {
        Node<V> node = nodes[pos];
        for(; nodes[pos] != null; node = nodes[pos], nodes[pos] = nodes[pos].next ) {
            if (nodes[pos].key == key) {
                node.next = nodes[pos].next;
                V value = nodes[pos].value;
                nodes[pos] = null;
                size--;
                return value;
            }
        }
        return null;
    }
    
    public boolean isEmpty() {
        return size > 0;
    }
    
    public boolean containsKey(long key) {
        return getNode(key) != null;
    }
    
    public boolean containsValue(V value) {
        for (int i = 0; i < buckets; i++) {
            Node<V> node = nodes[i];
            for(; node != null; node = node.next ) {
                if (node.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public long[] keys() {
        if (size > 0) {
            long[] keys = new long[size];
            getKeys(keys);
            return keys;
        }
        return null;
    }
    
    private void getKeys(long[] keys) {
        int pos = 0;
        for (int i = 0; i < buckets; i++) {
            Node<V> node = nodes[i];
            for(; node != null; node = node.next ) {
                keys[pos] = node.key;
                pos++;
            }
        }
    }
    
    public V[] values() {
        return getArrayValues();
    }
    
    private V[] getArrayValues() {
        V[] vs = null;
        int pos = 0;
        for (int i = 0; i < buckets; i++) {
            Node<V> node = nodes[i];
            for(; node != null; node = node.next ) {
                if (pos == 0) {
                    vs = (V[]) Array.newInstance(node.value.getClass(), size);
                }
                vs[pos] = node.value;
                pos++;
            }
        }
        return vs;
    }
    
    public long size() {
        return size;
    }
    
    public void clear() {
        buckets = CAPACITY_BUCKETS;
        size = 0;
        nodes = new Node[buckets];
    }
  
  
  private static class Node<V> {
    long key;
    V value;
    Node next;
    
    public Node(long key, V value, Node next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }
}
