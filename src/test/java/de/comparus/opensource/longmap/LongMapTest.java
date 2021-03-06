package de.comparus.opensource.longmap;

import static org.junit.jupiter.api.Assertions.*;

abstract class LongMapTest {
  
  protected void shouldPutTwoStrings(LongMap<String> longMap) {
    String hello = "hello";
    long keyHello = 1;
    String world = "world";
    long keyWorld = 8;
  
    assertEquals(hello, longMap.put(keyHello, hello));
    assertEquals(world, longMap.put(keyWorld, world));
    
    assertEquals(2, longMap.size());
  }
  
  protected void shouldPutFiveDouble(LongMap<Double> longMap) {
    longMap.put(1, 2.56);
    longMap.put(8, 12345.4);
    longMap.put(123456, 65434.004);
    longMap.put(98503, 12323.5);
    longMap.put(999999, -0.54);
    
    assertEquals(5, longMap.size());
  }
  
  protected void shouldReplaceValue(LongMap<Integer> longMap) {
    long key = 100000;
    Integer oldInteger = 555;
    Integer newInteger = 1000;
    
    assertEquals(oldInteger, longMap.put(key, oldInteger));
    
    assertEquals(1, longMap.size());
    assertTrue(longMap.containsValue(oldInteger));
    assertEquals(oldInteger, longMap.get(key));
    
    assertEquals(oldInteger, longMap.put(key, newInteger));
    
    assertEquals(1, longMap.size());
    assertTrue(longMap.containsValue(newInteger));
    assertEquals(newInteger, longMap.get(key));
  }
  
  protected void shouldGetValue(LongMap<String> longMap) {
    String hello = "hello";
    long keyHello = 1;
    String world = "world";
    long keyWorld = 8;
  
    assertEquals(hello, longMap.put(keyHello, hello));
    assertEquals(world, longMap.put(keyWorld, world));
    
    assertEquals(2, longMap.size());
    assertEquals(hello, longMap.get(keyHello));
    assertEquals(world, longMap.get(keyWorld));
  }
  
  protected void shouldGetNullValue(LongMap<String> longMap) {
    String hello = "hello";
    long keyHello = 1;
    String world = "world";
    long keyWorld = 120000;
  
    assertEquals(hello, longMap.put(keyHello, hello));
    assertEquals(world, longMap.put(keyWorld, world));
    
    assertEquals(2, longMap.size());
    assertNull(longMap.get(1200));
  }
  
  protected void shouldRemoveValue(LongMap<String> longMap) {
    String hello = "hello";
    long keyHello = 1;
    String world = "world";
    long keyWorld = 120000;
  
    assertEquals(hello, longMap.put(keyHello, hello));
    assertEquals(world, longMap.put(keyWorld, world));
    
    assertEquals(2, longMap.size());
    
    String deletedValue = longMap.remove(keyHello);
    
    assertEquals(hello, deletedValue);
    assertEquals(1, longMap.size());
    assertFalse(longMap.containsValue(deletedValue));
  }
  
  protected void shouldReturnNullWhenRemoveValue(LongMap<String> longMap) {
    String hello = "hello";
    long keyHello = 1;
    String world = "world";
    long keyWorld = 120000;
    
    assertEquals(hello, longMap.put(keyHello, hello));
    assertEquals(world, longMap.put(keyWorld, world));
    
    assertEquals(2, longMap.size());
    
    String deletedValue = longMap.remove(120001);
    
    assertNull(deletedValue);
    assertEquals(2, longMap.size());
  }
  
  protected void shouldReturnNullBecauseKeyIsAbsent(LongMap<String> longMap) {
    String hello = "hello";
    long keyHello = 1;
    String world = "world";
    long keyWorld = 120000;
  
    assertEquals(hello, longMap.put(keyHello, hello));
    assertEquals(world, longMap.put(keyWorld, world));
    
    assertEquals(2, longMap.size());
    
    String deletedValue = longMap.remove(12223);
    
    assertNull(deletedValue);
    assertEquals(2, longMap.size());
  }
  
  protected void shouldIsEmptyReturnFalse(LongMap<String> longMap) {
    String hello = "Hello";
    assertEquals(hello, longMap.put(1, hello));
    
    assertFalse(longMap.isEmpty());
  }
  
  protected void shouldIsEmptyReturnTrue(LongMap<String> longMap) {
    
    assertTrue(longMap.isEmpty());
  }
  
  protected void shouldContainsKeyReturnTrue(LongMap<String> longMap) {
    long key = 5555555;
    longMap.put(key, "Hello");
    
    assertTrue(longMap.containsKey(key));
  }
  
  protected void shouldContainsKeyReturnFalse(LongMap<String> longMap) {
    long key = 5555555;
    long keyForSearch = key + 1;
    String hello = "Hello";
    
    assertEquals(hello, longMap.put(key, hello));
    assertFalse(longMap.containsKey(keyForSearch));
  }
  
  protected void shouldContainsValueReturnTrue(LongMap<String> longMap) {
    String value = "Hello";
    assertEquals(value, longMap.put(1, value));
    
    assertTrue(longMap.containsValue(value));
  }
  
  protected void shouldContainsValueReturnFalse(LongMap<String> longMap) {
    String value = "Hello";
    String valueForSearch = value + "2222";
    assertEquals(value, longMap.put(1, value));
    
    assertFalse(longMap.containsValue(valueForSearch));
  }
  
  protected void shouldReturnArrayOfKeys(LongMap<String> longMap) {
    long[] expectedKeys = new long[5];
    for (int i = 0; i < expectedKeys.length; i++) {
      expectedKeys[i] = i;
      assertEquals(String.valueOf(i), longMap.put(i, String.valueOf(i)));
    }
    
    assertArrayEquals(expectedKeys, longMap.keys());
  }
  
  protected void shouldReturnNullArrayOfKeys(LongMap<String> longMap) {
    assertNull(longMap.keys());
  }
  
  protected void shouldReturnArrayOfValues(LongMap<String> longMap) {
    String[] expectedValues= new String[5];
    for (int i = 0; i < expectedValues.length; i++) {
      expectedValues[i] = String.valueOf(i);
      assertEquals(expectedValues[i], longMap.put(i, expectedValues[i]));
    }
    
    String[] actualValues = longMap.values();
    assertArrayEquals(expectedValues, actualValues);
  }
  
  protected void shouldReturnNullArrayOfValues(LongMap<String> longMap) {
    assertNull(longMap.values());
  }
  
  protected void shouldReturnZeroSize(LongMap<String> longMap) {
    assertEquals(0, longMap.size());
  }
  
  protected void shouldReturnNotZeroSize(LongMap<String> longMap) {
    String hello = "Hello";
  
    assertEquals(hello, longMap.put(1, hello));
    assertTrue(longMap.size() > 0);
    assertEquals(1, longMap.size());
  }
  
  protected void shouldClear(LongMap<String> longMap) {
    String hello = "Hello";
  
    assertEquals(hello, longMap.put(1, hello));
    assertEquals(1, longMap.size());
    
    longMap.clear();
    assertEquals(0, longMap.size());
    assertNull(longMap.get(1));
  }
  
  protected void shouldWorkAfterRehash(LongMap<Integer> longMap) {
    int size = 16;
    int keyForGet = 99999 * 2 + 1;
    for (int i = 0; i < size; i++) {
      int p = 99999 * i;
      if (i > 1) {
        p++;
      }
      assertEquals(i, longMap.put(p, i));
    }
    
    assertEquals(size, longMap.size());
    assertEquals(2, longMap.get(keyForGet));
  }
}