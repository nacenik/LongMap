package de.comparus.opensource.longmap;

import java.lang.reflect.Array;

public class LongHashMap<V> implements LongMap<V> {
    
    private static final int CAPACITY_BUCKETS = 1 << 3;
    private static final float MAX_LOAD_FACTOR = 0.75f;
    private int buckets;
    private int size;
    private Node<V>[] nodes;
    
    public LongHashMap() {
        clear();
    }
    
    public LongHashMap(int buckets) {
        if (buckets < 0) {
            throw new IllegalStateException("bed number for buckets");
        }
        this.size = 0;
        this.buckets = nearestPowerOfTwo(buckets);
        nodes = new Node[this.buckets];
    }
    
    @Override
    public V put(long key, V value) {
        
        if(verifyLoadFactor()) {
            rehashMap();
        }
        return putInto(nodes, key, value);
    }
    
    @Override
    public V get(long key) {
        Node<V> node = getNode(key);
        return node == null ? null : node.value;
    }
    
    @Override
    public V remove(long key) {
        int pos = getBucketNumber(key);
        if(nodes[pos] != null){
            return nodes[pos].next == null ? removeHeadValue(key, pos)
                    : removeValue(key, pos);
        }
        return null;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
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
    
    @Override
    public V[] values() {
        return getArrayValues();
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
    
    private int nearestPowerOfTwo(int bucket) {
        int powerOfTwo = 1;
        while (powerOfTwo < bucket) {
            powerOfTwo <<= 1;
        }
        return powerOfTwo;
    }
    
    private V putInto(Node<V>[] nodes, long key, V value) {
        int pos = getBucketNumber(key);
        return nodes[pos] == null ? putIntoBucket(nodes, pos, key, value)
                : putIntoBucketList(nodes[pos], key, value);
    }
    
    private int getBucketNumber(long key) {
        return Long.hashCode(key) & (buckets - 1);
    }
    
    private V putIntoBucket(Node<V>[] nodes, int pos, long key, V value) {
        nodes[pos] = new Node<>(key, value, null);
        size++;
        return nodes[pos].value;
    }
    
    private V putIntoBucketList(Node<V> head, long key, V value) {
        Node<V> node = head;
        for(; node != null; head = node, node = node.next ) {
            if (node.key == key) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }
        node = new Node<>(key, value, null);
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
    
    private V putIntoNewMap(Node<V>[] nodes, long key, V value) {
        int pos = getBucketNumber(key);
        return nodes[pos] == null ? putIntoNewBucket(nodes, pos, key, value)
                : putIntoNewBucketList(nodes[pos], key, value);
    }
    
    private V putIntoNewBucket(Node<V>[] nodes, int pos, long key, V value) {
        nodes[pos] = new Node<>(key, value, null);
        return nodes[pos].value;
    }
    
    private V putIntoNewBucketList(Node<V> head, long key, V value) {
        Node<V> node = head;
        for(; node != null; head = node, node = node.next );
        node = new Node<>(key, value, null);
        head.next = node;
        return node.value;
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
    
    private V removeHeadValue(long key, int pos) {
        if (nodes[pos].key == key) {
            V value = nodes[pos].value;
            nodes[pos] = null;
            size--;
            return value;
        }
        return null;
    }
    
    private V removeValue(long key, int pos) {
        Node<V> node = nodes[pos];
        Node<V> previousNode = null;
        for(; node != null; previousNode = node, node = node.next ) {
            if (node.key == key) {
                previousNode.next = node.next;
                V value = node.value;
                node = null;
                size--;
                return value;
            }
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
  
  private static class Node<R> {
    long key;
    R value;
    Node<R> next;
    
    public Node(long key, R value, Node<R> next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }
}
