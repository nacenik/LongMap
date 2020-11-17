package de.comparus.opensource.longmap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongOpenAddressingMapTest extends LongMapTest {
  
  @Test
  void shouldThrowIllegalStateException() {
    Assertions.assertThrows(IllegalStateException.class, () -> new LongOpenAddressingMap<>(-5));
  }
  
  @Test
  void shouldPutTwoStrings() {
    super.shouldPutTwoStrings(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldPutFiveDouble() {
    super.shouldPutFiveDouble(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldReplaceValue() {
    super.shouldReplaceValue(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldGetValue() {
    super.shouldGetValue(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldRemoveValue() {
    super.shouldRemoveValue(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldReturnNullBecauseKeyIsAbsent() {
    super.shouldReturnNullBecauseKeyIsAbsent(new LongOpenAddressingMap<>(14));
  }
  @Test
  void shouldIsEmptyReturnTrue() {
    super.shouldIsEmptyReturnTrue(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldIsEmptyReturnFalse() {
    super.shouldIsEmptyReturnFalse(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldContainsKeyReturnTrue() {
    super.shouldContainsKeyReturnTrue(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldContainsKeyReturnFalse() {
    super.shouldContainsKeyReturnFalse(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldContainsValueReturnTrue() {
    super.shouldContainsValueReturnTrue(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldContainsValueReturnFalse() {
    super.shouldContainsValueReturnFalse(new LongOpenAddressingMap<>());
    
  }
  @Test
  void shouldReturnArrayOfKeys() {
    super.shouldReturnArrayOfKeys(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldReturnArrayOfValues() {
    super.shouldReturnArrayOfValues(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldReturnZeroSize() {
    super.shouldReturnZeroSize(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldReturnNotZeroSize() {
    super.shouldReturnNotZeroSize(new LongOpenAddressingMap<>());
  }
  @Test
  void shouldClear() {
    super.shouldClear(new LongOpenAddressingMap<>());
  }
}
