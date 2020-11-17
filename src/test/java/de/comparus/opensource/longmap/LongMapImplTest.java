package de.comparus.opensource.longmap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongMapImplTest extends LongMapTest {
  
  @Test
  void shouldThrowIllegalStateException() {
    Assertions.assertThrows(IllegalStateException.class, () -> new LongMapImpl<>(-5));
  }
  
  @Test
  void shouldPutTwoStrings() {
    super.shouldPutTwoStrings(new LongMapImpl<>());
  }
  @Test
  void shouldPutFiveDouble() {
    super.shouldPutFiveDouble(new LongMapImpl<>());
  }
  @Test
  void shouldReplaceValue() {
    super.shouldReplaceValue(new LongMapImpl<>());
  }
  @Test
  void shouldGetValue() {
    super.shouldGetValue(new LongMapImpl<>());
  }
  @Test
  void shouldRemoveValue() {
    super.shouldRemoveValue(new LongMapImpl<>());
  }
  @Test
  void shouldReturnNullBecauseKeyIsAbsent() {
    super.shouldReturnNullBecauseKeyIsAbsent(new LongMapImpl<>(5));
  }
  @Test
  void shouldIsEmptyReturnTrue() {
    super.shouldIsEmptyReturnTrue(new LongMapImpl<>());
  }
  @Test
  void shouldIsEmptyReturnFalse() {
    super.shouldIsEmptyReturnFalse(new LongMapImpl<>());
  }
  @Test
  void shouldContainsKeyReturnTrue() {
    super.shouldContainsKeyReturnTrue(new LongMapImpl<>());
  }
  @Test
  void shouldContainsKeyReturnFalse() {
    super.shouldContainsKeyReturnFalse(new LongMapImpl<>());
  }
  @Test
  void shouldContainsValueReturnTrue() {
    super.shouldContainsValueReturnTrue(new LongMapImpl<>());
  }
  @Test
  void shouldContainsValueReturnFalse() {
    super.shouldContainsValueReturnFalse(new LongMapImpl<>());
    
  }
  @Test
  void shouldReturnArrayOfKeys() {
    super.shouldReturnArrayOfKeys(new LongMapImpl<>());
  }
  @Test
  void shouldReturnArrayOfValues() {
    super.shouldReturnArrayOfValues(new LongMapImpl<>());
  }
  @Test
  void shouldReturnZeroSize() {
    super.shouldReturnZeroSize(new LongMapImpl<>());
  }
  @Test
  void shouldReturnNotZeroSize() {
    super.shouldReturnNotZeroSize(new LongMapImpl<>());
  }
  @Test
  void shouldClear() {
    super.shouldClear(new LongMapImpl<>());
  }
}
