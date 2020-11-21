package de.comparus.opensource.longmap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongHashMapTest extends LongMapTest {
  
  @Test
  void shouldThrowIllegalStateException() {
    Assertions.assertThrows(IllegalStateException.class, () -> new LongHashMap<>(-5));
  }
  
  @Test
  void shouldDeleteFromOneBucket() {
    LongHashMap<Integer> longHashMap = new LongHashMap<>();
    for (int i = 0; i < 5; i++) {
      Assertions.assertEquals(i, longHashMap.put(8 * i, i));
    }
    
    Assertions.assertEquals(4, longHashMap.remove(8 * 4));
    Assertions.assertTrue(longHashMap.containsKey(8 * 3));
    Assertions.assertFalse(longHashMap.containsKey(8 * 4));
    Assertions.assertEquals(4, longHashMap.size());
    
    Assertions.assertEquals(1, longHashMap.remove(8));
    Assertions.assertTrue(longHashMap.containsKey(8 * 3));
    Assertions.assertFalse(longHashMap.containsKey(8));
    Assertions.assertEquals(3, longHashMap.size());
  }
  
  @Test
  void shouldReturnNullWhenDeleteFromOneBucket() {
    LongHashMap<Integer> longHashMap = new LongHashMap<>();
    for (int i = 0; i < 5; i++) {
      Assertions.assertEquals(i, longHashMap.put(8 * i, i));
    }
    
    Assertions.assertNull(longHashMap.remove(8 * 5));
    Assertions.assertEquals(5, longHashMap.size());
  }
  
  @Test
  void shouldPutTwoStrings() {
    super.shouldPutTwoStrings(new LongHashMap<>());
  }
  
  @Test
  void shouldPutFiveDouble() {
    super.shouldPutFiveDouble(new LongHashMap<>());
  }
  @Test
  void shouldReplaceValue() {
    super.shouldReplaceValue(new LongHashMap<>());
  }
  
  @Test
  void shouldGetValue() {
    super.shouldGetValue(new LongHashMap<>());
  }
  
  @Test
  void shouldGetNullValue() {
    super.shouldGetNullValue(new LongHashMap<>());
  }
  
  @Test
  void shouldRemoveValue() {
    super.shouldRemoveValue(new LongHashMap<>());
  }
  
  @Test
  void shouldReturnNullWhenRemoveValue() {
    super.shouldReturnNullWhenRemoveValue(new LongHashMap<>());
  }
  
  @Test
  void shouldReturnNullBecauseKeyIsAbsent() {
    super.shouldReturnNullBecauseKeyIsAbsent(new LongHashMap<>(5));
  }
  
  @Test
  void shouldIsEmptyReturnFalse() {
    super.shouldIsEmptyReturnFalse(new LongHashMap<>());
  }
  
  @Test
  void shouldIsEmptyReturnTrue() {
    super.shouldIsEmptyReturnTrue(new LongHashMap<>());
  }
  
  @Test
  void shouldContainsKeyReturnTrue() {
    super.shouldContainsKeyReturnTrue(new LongHashMap<>());
  }
  
  @Test
  void shouldContainsKeyReturnFalse() {
    super.shouldContainsKeyReturnFalse(new LongHashMap<>());
  }
  
  @Test
  void shouldContainsValueReturnTrue() {
    super.shouldContainsValueReturnTrue(new LongHashMap<>());
  }
  
  @Test
  void shouldContainsValueReturnFalse() {
    super.shouldContainsValueReturnFalse(new LongHashMap<>());
    
  }
  
  @Test
  void shouldReturnArrayOfKeys() {
    super.shouldReturnArrayOfKeys(new LongHashMap<>());
  }
  
  @Test
  void shouldReturnNullArrayOfKeys() {
    super.shouldReturnArrayOfKeys(new LongHashMap<>());
  }
  
  @Test
  void shouldReturnArrayOfValues() {
    super.shouldReturnArrayOfValues(new LongHashMap<>());
  }
  
  @Test
  void shouldReturnNullArrayOfValues() {
    super.shouldReturnNullArrayOfKeys(new LongHashMap<>());
  }
  
  @Test
  void shouldReturnZeroSize() {
    super.shouldReturnZeroSize(new LongHashMap<>());
  }
  
  @Test
  void shouldReturnNotZeroSize() {
    super.shouldReturnNotZeroSize(new LongHashMap<>());
  }
  
  @Test
  void shouldClear() {
    super.shouldClear(new LongHashMap<>());
  }
  
  @Test
  void shouldWorkAfterRehash() {
    super.shouldWorkAfterRehash(new LongHashMap<>());
  }
}
