package de.comparus.opensource.longmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongMapImplTest {
  
  @Test()
  void shouldCatchIllegalStateException() throws IllegalStateException{
    assertThrows(IllegalStateException.class, () -> new LongMapImpl<>(-5));
  }
  
  @Test
  void shouldPutTwoStrings() {
    LongMapImpl<String> longMap = new LongMapImpl<>();
    longMap.put(1, "Hello");
    longMap.put(12345678, "World");
    
    assertEquals(2, longMap.size());
  }
  
  @Test
  void shouldPutFiveDouble() {
    LongMapImpl<Double> longMap = new LongMapImpl<>();
    longMap.put(55555, 2.56);
    longMap.put(111, 12345.4);
    longMap.put(123456, 65434.004);
    longMap.put(98503, 12323.5);
    longMap.put(999999, -0.54);
    
    assertEquals(5, longMap.size());
  }
  
  @Test
  void shouldReplaceValue() {
    LongMapImpl<Integer> longMap = new LongMapImpl<>();
    long key = 100000;
    Integer oldInteger = 555;
    Integer newInteger = 1000;
    
    longMap.put(key, oldInteger);
    
    assertEquals(1, longMap.size());
    assertTrue(longMap.containsValue(oldInteger));
    assertEquals(oldInteger, longMap.get(key));
    
    longMap.put(key, newInteger);
    
    assertEquals(1, longMap.size());
    assertTrue(longMap.containsValue(newInteger));
    assertEquals(newInteger, longMap.get(key));
  }
  
  @Test
  void shouldGetValue() {
    String hello = "hello";
    long keyHello = 1;
    String world = "world";
    long keyWorld = 120000;
    
    LongMapImpl<String> longMap = new LongMapImpl<>();
    longMap.put(keyHello, hello);
    longMap.put(keyWorld, world);
    
    assertEquals(2, longMap.size());
    assertEquals(hello, longMap.get(keyHello));
    assertEquals(world, longMap.get(keyWorld));
  }
  
  @Test
  void shouldRemoveValue() {
    String hello = "hello";
    long keyHello = 1;
    String world = "world";
    long keyWorld = 120000;
  
    LongMapImpl<String> longMap = new LongMapImpl<>();
    longMap.put(keyHello, hello);
    longMap.put(keyWorld, world);
    
    assertEquals(2, longMap.size());
    
    String deletedValue = longMap.remove(keyHello);
    
    assertEquals(hello, deletedValue);
    assertEquals(1, longMap.size());
    assertFalse(longMap.containsValue(deletedValue));
  }
  
  @Test
  void shouldReturnNullBecauseKeyIsAbsent() {
    String hello = "hello";
    long keyHello = 1;
    String world = "world";
    long keyWorld = 120000;
    
    LongMapImpl<String> longMap = new LongMapImpl<>();
    longMap.put(keyHello, hello);
    longMap.put(keyWorld, world);
    
    assertEquals(2, longMap.size());
    
    String deletedValue = longMap.remove(12223);
    
    assertNull(deletedValue);
    assertEquals(2, longMap.size());
  }
  
  @Test
  void shouldIsEmptyReturnTrue() {
    LongMapImpl<String> longMap = new LongMapImpl<>();
    longMap.put(1, "Hello");
    
    assertTrue(longMap.isEmpty());
  }
  
  @Test
  void shouldIsEmptyReturnFalse() {
    LongMapImpl<String> longMap = new LongMapImpl<>();
    
    assertFalse(longMap.isEmpty());
  }
  
  @Test
  void shouldContainsKeyReturnTrue() {
    LongMapImpl<String> longMap = new LongMapImpl<>();
    long key = 5555555;
    longMap.put(key, "Hello");
    
    assertTrue(longMap.containsKey(key));
  }
  
  @Test
  void shouldContainsKeyReturnFalse() {
    LongMapImpl<String> longMap = new LongMapImpl<>();
    long key = 5555555;
    long keyForSearch = key + 1;
    longMap.put(key, "Hello");
    
    assertFalse(longMap.containsKey(keyForSearch));
  }
  
  @Test
  void shouldContainsValueReturnTrue() {
    LongMapImpl<String> longMap = new LongMapImpl<>();
    String value = "Hello";
    longMap.put(1, value);
  
    assertTrue(longMap.containsValue(value));
  }
  
  @Test
  void shouldContainsValueReturnFalse() {
    LongMapImpl<String> longMap = new LongMapImpl<>();
    String value = "Hello";
    String valueForSearch = value + "2222";
    longMap.put(1, value);
    
    assertFalse(longMap.containsValue(valueForSearch));
  }
  
  @Test
  void shouldReturnArrayOfKeys() {
    long[] expectedKeys = new long[5];
    LongMapImpl<String> longMap = new LongMapImpl<>();
    for (int i = 0; i < expectedKeys.length; i++) {
      expectedKeys[i] = i;
      longMap.put(i, String.valueOf(i));
    }
    
    assertArrayEquals(expectedKeys, longMap.keys());
  }
  
  @Test
  void shouldReturnArrayOfValues() {
    String[] expectedValues= new String[5];
    LongMapImpl<String> longMap = new LongMapImpl<>();
    for (int i = 0; i < expectedValues.length; i++) {
      expectedValues[i] = String.valueOf(i);
      longMap.put(i, expectedValues[i]);
    }
  
    String[] actualValues = longMap.values();
    assertArrayEquals(expectedValues, actualValues);
  }
  
  @Test
  void shouldReturnZeroSize() {
    LongMapImpl<String> longMap = new LongMapImpl<>();
    assertEquals(0, longMap.size());
  }
  
  @Test
  void shouldReturnNotZeroSize() {
    LongMapImpl<String> longMap = new LongMapImpl<>();
    longMap.put(1, "Hello");
    assertTrue(longMap.size() > 0);
    assertEquals(1, longMap.size());
  }
  
  @Test
  void shouldClear() {
    LongMapImpl<String> longMap = new LongMapImpl<>();
    longMap.put(1, "Hello");
    assertEquals(1, longMap.size());
    
    longMap.clear();
    assertEquals(0, longMap.size());
    assertNull(longMap.get(1));
  }
}