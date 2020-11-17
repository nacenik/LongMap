package de.comparus.opensource.longmap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongMapOpenAddressingTest extends LongMapTest {
  
  @Test
  void shouldThrowIllegalStateException() {
    Assertions.assertThrows(IllegalStateException.class, () -> new LongMapOpenAddressing<>(-5));
  }
  
  @Test
  void shouldPutTwoStrings() {
    super.shouldPutTwoStrings(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldPutFiveDouble() {
    super.shouldPutFiveDouble(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldReplaceValue() {
    super.shouldReplaceValue(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldGetValue() {
    super.shouldGetValue(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldRemoveValue() {
    super.shouldRemoveValue(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldReturnNullBecauseKeyIsAbsent() {
    super.shouldReturnNullBecauseKeyIsAbsent(new LongMapOpenAddressing<>(14));
  }
  @Test
  void shouldIsEmptyReturnTrue() {
    super.shouldIsEmptyReturnTrue(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldIsEmptyReturnFalse() {
    super.shouldIsEmptyReturnFalse(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldContainsKeyReturnTrue() {
    super.shouldContainsKeyReturnTrue(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldContainsKeyReturnFalse() {
    super.shouldContainsKeyReturnFalse(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldContainsValueReturnTrue() {
    super.shouldContainsValueReturnTrue(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldContainsValueReturnFalse() {
    super.shouldContainsValueReturnFalse(new LongMapOpenAddressing<>());
    
  }
  @Test
  void shouldReturnArrayOfKeys() {
    super.shouldReturnArrayOfKeys(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldReturnArrayOfValues() {
    super.shouldReturnArrayOfValues(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldReturnZeroSize() {
    super.shouldReturnZeroSize(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldReturnNotZeroSize() {
    super.shouldReturnNotZeroSize(new LongMapOpenAddressing<>());
  }
  @Test
  void shouldClear() {
    super.shouldClear(new LongMapOpenAddressing<>());
  }
}
