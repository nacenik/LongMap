package de.comparus.opensource.longmap;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LongMapImpl<V> implements LongMap<V> {
    
    private static final int CAPACITY_BUCKETS = 1 << 3;
    private int capacityBuckets;
    private int size;
    private Node<V>[] nodes;
    private static final float MAX_LOAD = 0.75f;
    private float currentLoad;
    
    public LongMapImpl() {
        clear();
    }
    
    public LongMapImpl(int capacityBuckets) {
        this.size = 0;
        this.capacityBuckets = capacityBuckets;
        nodes = new Node[capacityBuckets];
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
    
    public V put(long key, V value) {
    
        V val = putInto(nodes, key, value, true);
        if(loadFactorTest()) {
            resizeCapacityBuckets();
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
        return Long.hashCode(key) & capacityBuckets - 1;
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
    
    private boolean loadFactorTest() {
        return size > capacityBuckets * MAX_LOAD;
    }
    
    private void resizeCapacityBuckets() {
        Node<V>[] newNodes = new Node[capacityBuckets <<= 1];
        for (int i = 0; i < capacityBuckets >> 1; i++) {
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
        return Arrays.stream(keys()).anyMatch(x -> x == key);
    }
    
    public boolean containsValue(V value) {
        return Arrays.stream(getArrayValues()).anyMatch(i -> i.equals(value));
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
        for (int i = 0; i < capacityBuckets; i++) {
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
        for (int i = 0; i < capacityBuckets; i++) {
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
        capacityBuckets = CAPACITY_BUCKETS;
        size = 0;
        nodes = new Node[capacityBuckets];
    }
}
