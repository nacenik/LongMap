package de.comparus.opensource.longmap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongMapOpenAddressingImplTest extends LongMapTest {
  
  @Test
  void shouldThrowIllegalStateException() {
    Assertions.assertThrows(IllegalStateException.class, () -> new LongMapOpenAddressingImpl<>(-5));
  }
  
  @Test
  void shouldPutTwoStrings() {
    super.shouldPutTwoStrings(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldPutFiveDouble() {
    super.shouldPutFiveDouble(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldReplaceValue() {
    super.shouldReplaceValue(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldGetValue() {
    super.shouldGetValue(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldRemoveValue() {
    super.shouldRemoveValue(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldReturnNullBecauseKeyIsAbsent() {
    super.shouldReturnNullBecauseKeyIsAbsent(new LongMapOpenAddressingImpl<>(14));
  }
  @Test
  void shouldIsEmptyReturnTrue() {
    super.shouldIsEmptyReturnTrue(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldIsEmptyReturnFalse() {
    super.shouldIsEmptyReturnFalse(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldContainsKeyReturnTrue() {
    super.shouldContainsKeyReturnTrue(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldContainsKeyReturnFalse() {
    super.shouldContainsKeyReturnFalse(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldContainsValueReturnTrue() {
    super.shouldContainsValueReturnTrue(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldContainsValueReturnFalse() {
    super.shouldContainsValueReturnFalse(new LongMapOpenAddressingImpl<>());
    
  }
  @Test
  void shouldReturnArrayOfKeys() {
    super.shouldReturnArrayOfKeys(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldReturnArrayOfValues() {
    super.shouldReturnArrayOfValues(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldReturnZeroSize() {
    super.shouldReturnZeroSize(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldReturnNotZeroSize() {
    super.shouldReturnNotZeroSize(new LongMapOpenAddressingImpl<>());
  }
  @Test
  void shouldClear() {
    super.shouldClear(new LongMapOpenAddressingImpl<>());
  }
}
