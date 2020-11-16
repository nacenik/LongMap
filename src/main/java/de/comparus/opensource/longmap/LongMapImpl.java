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
    
    @Override
    public V put(long key, V value) {
    
        if(verifyLoadFactor()) {
            rehashMap();
        }
        V val = putInto(nodes, key, value);
        return val;
    }
    
    private V putInto(Node[] nodes, long key, V value) {
        int pos = getBucketNumber(key);
        return nodes[pos] == null ? putIntoBucket(nodes, pos, key, value)
                : (V) putIntoBucketList(nodes[pos], key, value);
    }
    
    private int getBucketNumber(long key) {
        return Long.hashCode(key) & buckets - 1;
    }
    
    private V putIntoBucket(Node[] nodes, int pos, long key, V value) {
        nodes[pos] = new Node<>(key, value, null);
        size++;
        return (V) nodes[pos].value;
    }
    
    private V putIntoBucketList(Node<V> head, long key, V value) {
        Node<V> node = head;
        for(; node != null; head = node, node = node.next ) {
            if (node.key == key) {
                node.value = value;
                return node.value;
            }
        }
        node = new Node(key, value, null);
        head.next = node;
        size++;
        return node.value;
    }
    
    private boolean verifyLoadFactor() {
        return size + 1 > buckets * MAX_LOAD_FACTOR;
    }
    
    private void rehashMap() {
        long oldBuckets = buckets;
        buckets <<= 1;
        Node<V>[] newNodes = new Node[buckets];
        for (int i = 0; i < oldBuckets; i++) {
            Node<V> node = nodes[i];
            for(; node != null; node = node.next ) {
                putIntoNewMap(newNodes, node.key, node.value);
            }
        }
        nodes = newNodes;
    }
    
    private V putIntoNewMap(Node[] nodes, long key, V value) {
        int pos = getBucketNumber(key);
        return nodes[pos] == null ? putIntoNewBucket(nodes, pos, key, value)
                : (V) putIntoNewBucketList(nodes[pos], key, value);
    }
    
    private V putIntoNewBucket(Node[] nodes, int pos, long key, V value) {
        nodes[pos] = new Node<>(key, value, null);
        size++;
        return (V) nodes[pos].value;
    }
    
    private V putIntoNewBucketList(Node<V> head, long key, V value) {
        Node<V> node = head;
        for(; node != null; head = node, node = node.next ) {
        }
        node = new Node(key, value, null);
        head.next = node;
        size++;
        return node.value;
    }
    
    @Override
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
    
    @Override
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
    
    @Override
    public boolean isEmpty() {
        return size > 0;
    }
    
    @Override
    public boolean containsKey(long key) {
        return getNode(key) != null;
    }
    
    @Override
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
    
    @Override
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
    
    @Override
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
    
    @Override
    public long size() {
        return size;
    }
    
    @Override
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
