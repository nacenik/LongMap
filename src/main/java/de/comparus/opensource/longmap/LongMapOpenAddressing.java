package de.comparus.opensource.longmap;

import java.lang.reflect.Array;

public class LongMapOpenAddressing<V> implements LongMap<V> {
  
  private static final int CAPACITY_BUCKETS = 1 << 3;
  private static final float MAX_LOAD_FACTOR = 0.75f;
  private int buckets;
  private int size;
  private Node<V>[] nodes;
  
  public LongMapOpenAddressing() {
    clear();
  }
  
  public LongMapOpenAddressing(int buckets) {
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
    V val = putIntoBucket(nodes, key, value);
    return val;
  }
  
  @Override
  public V get(long key) {
    int i = searchPosition(getBucketNumber(key), key);
    return i < 0? null : nodes[i].value;
  }
  
  @Override
  public V remove(long key) {
    int pos = getBucketNumber(key);
    return removeValue(key, pos);
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
      if (nodes[i] != null && nodes[i].value.equals(value)) {
        return true;
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
  
  private int getBucketNumber(long key) {
    return Long.hashCode(key) & (buckets - 1);
  }
  
  private V putIntoBucket(Node[] nodes, long key, V value) {
    int pos = getBucketNumber(key);
    int i = pos;
    while (nodes[i] != null) {
      if (nodes[i].key == key) {
        return (V) (nodes[i].value = value);
      }
      if (i < buckets - 1) {
        i++;
      } else {
        i = 0;
      }
    }
    nodes[i] = new Node<V>(key, value);
    size++;
    return (V) nodes[i].value;
  }
  
  private boolean verifyLoadFactor() {
    return size + 1 > buckets * MAX_LOAD_FACTOR;
  }
  
  private void rehashMap() {
    long oldBuckets = buckets;
    buckets <<= 1;
    Node<V>[] newNodes = new Node[buckets];
    for (int i = 0; i < oldBuckets; i++) {
      if (nodes[i] != null) {
        putIntoNewBucket(newNodes, nodes[i].key, nodes[i].value);
      }
    }
    nodes = newNodes;
  }
  
  private void putIntoNewBucket(Node[] nodes, long key, V value) {
    int pos = getBucketNumber(key);
    int i = pos;
    while (nodes[i] != null) {
      if (i < buckets - 1) {
        i++;
      } else {
        i = 0;
      }
    }
    nodes[i] = new Node<V>(key, value);
  }
  
  private Node<V> getNode(long key) {
    int pos = getBucketNumber(key);
    int i = searchPosition(pos, key);
    if (i > -1) {
      return nodes[i];
    }
    return null;
  }
  
  private int searchPosition(int posStart, long key) {
    while (nodes[posStart] != null) {
      if (nodes[posStart].key == key) {
        return posStart;
      }
      if (posStart < buckets) {
        posStart++;
      } else {
        posStart = 0;
      }
    }
    return -1;
  }
  
  private V removeValue(long key, int pos) {
    int i = searchPosition(pos, key);
    if (i > -1) {
      V v = nodes[i].value;
      nodes[i] = null;
      size--;
      return v;
    }
    return null;
  }
  
  private void getKeys(long[] keys) {
    int pos = 0;
    for (int i = 0; i < buckets; i++) {
      if (nodes[i] != null) {
        keys[pos] = nodes[i].key;
        pos++;
      }
    }
  }
  
  private V[] getArrayValues() {
    V[] vs = null;
    int pos = 0;
    for (int i = 0; i < buckets; i++) {
      if (nodes[i] != null) {
        if (pos == 0) {
          vs = (V[]) Array.newInstance(nodes[i].value.getClass(), size);
        }
        vs[pos] = nodes[i].value;
        pos++;
      }
    }
    return vs;
  }
  
  private static class Node<V> {
    long key;
    V value;
    
    public Node(long key, V value) {
      this.key = key;
      this.value = value;
    }
  }
}
