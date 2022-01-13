package com.makotojava.learn.hellojunit5;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.beans.Transient;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit 5 (with JUnitPlatform.class)
 * 
 * Class-level Exercises:
 * <ol>
 * <li>Enable Eclipse JUnit support for this test class using the {@link org.junit.runner.RunWith @RunWith} and
 * {@link org.junit.platform.runner.JUnitPlatform
 * JUnitPlatform} class.</li>
 * <li>Give the class a cool {@link org.junit.jupiter.api.DisplayName @DisplayName} that shows up in the JUnit test
 * report.</li>
 * </ol>
 *
 */
public class JUnit5AppTest {

  private static final Logger log = LoggerFactory.getLogger(JUnit5AppTest.class);

  // Create a fixture for the class under test
  private App classUnderTest;

  // Do something before ANY test is run in this class
  @BeforeAll
  public static void init() {
    int x = 5;
  }

  // Do something after ALL tests in this class are run
  @AfterAll
  public static void done() {
    int y = 6;
  }

  // Create an instance of the test class before each @Test method is executed
  @BeforeEach
  public void setUp() throws Exception {
    classUnderTest = new App();
  }

  // Destroy reference to the instance of the test class after each @Test method is executed
  @AfterEach
  public void takeDown() throws Exception {
    classUnderTest = null;
  }

  // Disabled test
  @Test
  @Disabled
  @DisplayName("Disable Test")
  void testNotRun() {
    //string test = "disabled";
  }

  /**
   * testAdd() - Exercises:
   * <ol>
   * <li>Tell JUnit this method is a test method.</li>
   * <li>Give it a cool display name for the test report.</li>
   * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
   * <li>Create a group of three tests of the add methods with the following arrays of positive numbers:
   * <ol>
   * <li>1, 2, 3, 4</li>
   * <li>20, 934, 110</li>
   * <li>2, 4, 6</li>
   * </ol>
   * Ensure the actual sum matches the expected sum for each group of numbers. Make sure that all groups of numbers are
   * tested (i.e., if one fails, it should not fail the test method). Hint: use
   * {@link org.junit.jupiter.api.Assertions#assertAll(org.junit.jupiter.api.function.Executable...) assertAll()}
   * </ol>
   */
  @Test
  @DisplayName("Test classUnderTest Add functionality")
  public void testAdd() {
    assertNotNull(classUnderTest);

    assertAll(
      () -> assertEquals(10, classUnderTest.add(new long[]{1,2,3,4})),
      () -> assertEquals(1064, classUnderTest.add(new long[]{20,934,110})),
      () -> assertEquals(12, classUnderTest.add(new long[]{2,4,6}))
    );
  }

  /**
   * Class-level Exercises:
   * <ol>
   * <li>Make this class a nested test class (hint: use {@link org.junit.jupiter.api.Nested @Nested}).
   * <li>Give the class a cool {@link org.junit.jupiter.api.DisplayName @DisplayName} that shows up in the JUnit test
   * report.</li>
   * <li>Create an instance of the {@link com.makotojava.learn.hellojunit5.App App} class specifically for this nested
   * class:
   * <ul>
   * <li>Set the <code>classUnderTest</code> variable in a method called <code>setUp()</code> that runs before the test
   * method does (hint: use {@link org.junit.jupiter.api.BeforeEach @BeforeEach})</li>
   * </ul>
   * <li>Set the <code>classUnderTest</code> variable to null in a method called <code>tearDown()</code> that runs after
   * the
   * test method does (hint: use {@link org.junit.jupiter.api.BeforeEach @AfterEach})</li>
   * </ol>
   * 
   */
  @Nested
  @DisplayName("This class tests only negative numbers")
  class NegativeNumbersTest {

    private App classUnderTest;

    // Create an instance of the test class before each @Test method is executed
    @BeforeEach
    public void setUp() throws Exception {
      classUnderTest = new App();
    }

    // Destroy reference to the instance of the test class after each @Test method is executed
    @AfterEach
    public void takeDown() throws Exception {
      classUnderTest = null;
    }

    /**
     * testAdd() - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
     * <li>Create a group of three tests of the add methods with the following arrays of positive numbers:
     * <ol>
     * <li>-1, -2, -3, -4</li>
     * <li>-20, -934, -110</li>
     * <li>-2, -4, -6</li>
     * </ol>
     * Ensure the actual sum matches the expected sum for each group of numbers. Make sure that all groups of numbers
     * are
     * tested (i.e., if one fails, it should not fail the test method). Hint: use
     * {@link org.junit.jupiter.api.Assertions#assertAll(org.junit.jupiter.api.function.Executable...) assertAll()}
     * </ol>
     */
    @Test
    @DisplayName("Test Add functionality with Negative numbers")
    public void testAdd() {
      assertNotNull(classUnderTest);

      assertAll(
        () -> assertEquals(-10, classUnderTest.add(new long[]{-1,-2,-3,-4})),
        () -> assertEquals(-1064, classUnderTest.add(new long[]{-20,-934,-110})),
        () -> assertEquals(-12, classUnderTest.add(new long[]{-2,-4,-6}))
      );
    }
  }

  /**
   * Class-level Exercises:
   * <ol>
   * <li>Make this class a nested test class (hint: use {@link org.junit.jupiter.api.Nested @Nested}).
   * <li>Give the class a cool {@link org.junit.jupiter.api.DisplayName @DisplayName} that shows up in the JUnit test
   * report.</li>
   * </ol>
   * 
   */
  @Nested
  @DisplayName("This class tests positive and negative numbers")
  class PositiveAndNegativeNumbersTest {

    /**
     * testAdd() - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
     * <li>Create a group of three tests of the add methods with the following arrays of positive numbers:
     * <ol>
     * <li>-1, 2, -3, 4</li>
     * <li>-20, 934, -110</li>
     * <li>-2, -4, 6</li>
     * </ol>
     * Ensure the actual sum matches the expected sum for each group of numbers. Make sure that all groups of numbers
     * are
     * tested (i.e., if one fails, it should not fail the test method). Hint: use
     * {@link org.junit.jupiter.api.Assertions#assertAll(org.junit.jupiter.api.function.Executable...) assertAll()}
     * </ol>
     */
    @Test
    @DisplayName("Test Add functionality with Positive and Negative numbers")
    public void testAdd() {
      assertNotNull(classUnderTest);

      assertAll(
        () -> assertEquals(2, classUnderTest.add(new long[]{-1,2,-3,4})),
        () -> assertEquals(804, classUnderTest.add(new long[]{-20,934,-110})),
        () -> assertEquals(0, classUnderTest.add(new long[]{-2,-4,6}))
      );
    }

    /**
     * testAdd_OnlyOnFriday - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>Use the JDK 8 Date/Time API to get the current local date/time, along with a simple Assumption (hint: use
     * {@link org.junit.jupiter.api.Assumptions#assumeTrue(boolean) assumeTrue()}) that today is Friday
     * (or any other day of the week of your choosing, just so you pick one), or the test is skipped.</li>
     * <li>Pass the following numbers as operands to the {@link com.makotojava.learn.hellojunit5.App#add(long[]) add}
     * method: 1, 2, 3, 4, 5</li>
     * <li>Ensure the actual sum matches the expected sum.</li>
     * </ol>
     */
    @Test
    @DisplayName("Test Add functionality only on Friday")
    public void testAdd_OnlyOnFriday() {
      assertNotNull(classUnderTest);
      LocalDateTime ldt = LocalDateTime.now();
      assumeTrue(ldt.getDayOfWeek().getValue() == 5, "Test skipped... it's not Friday!");
      assumeTrue(ldt.getDayOfWeek().getValue() == 5);
      assertEquals(15, classUnderTest.add(new long[]{1, 2, 3, 4, 5}));
    }

    /**
     * testAdd_OnlyOnFriday_WithLambda - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>Use the JDK 8 Date/Time API to get the current local date/time, along with a simple Assumption (hint: use
     * {@link org.junit.jupiter.api.Assumptions#assumingThat(boolean, org.junit.jupiter.api.function.Executable)
     * assumingThat()}) that today is Friday
     * (or any other day of the week of your choosing, just so you pick one), or the test is skipped.</li>
     * <li>Pass the following numbers as operands to the {@link com.makotojava.learn.hellojunit5.App#add(long[]) add}
     * method: 1, 2, 3, 4, 5</li>
     * <li>Ensure the actual sum matches the expected sum.</li>
     * </ol>
     */
    @Test
    @DisplayName("Test Add functionality only on Friday using lambda")
    public void testAdd_OnlyOnFriday_WithLambda() {
      assertNotNull(classUnderTest);
      LocalDateTime ldt = LocalDateTime.now();
      assumingThat(ldt.getDayOfWeek().getValue() == 5, 
        () -> 
        classUnderTest.add(new long[]{1, 2, 3, 4, 5}));
    }

  }

  /**
   * Class-level Exercises:
   * <ol>
   * <li>Make this class a nested test class (hint: use {@link org.junit.jupiter.api.Nested @Nested}).
   * <li>Give the class a cool {@link org.junit.jupiter.api.DisplayName @DisplayName} that shows up in the JUnit test
   * report.</li>
   * </ol>
   * 
   */
  @Nested
  @DisplayName("Test Add with one operand")
  class JUnit5AppSingleOperandTest {

    /**
     * testAdd_NumbersGt0() - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
     * <li>Create a group of two tests of the add methods with the following arrays of positive numbers:
     * <ol>
     * <li>1</li>
     * <li>0</li>
     * </ol>
     * Ensure the actual sum matches the expected sum for each group of numbers. Make sure that all groups of numbers
     * are
     * tested (i.e., if one fails, it should not fail the test method). Hint: use
     * {@link org.junit.jupiter.api.Assertions#assertAll(org.junit.jupiter.api.function.Executable...) assertAll()}
     * </ol>
     */
    @Test
    @DisplayName("Testing add with only one number")
    public void testAdd_NumbersGt0() {
      assertNotNull(classUnderTest);
      assertAll(
        () -> assertEquals(1, classUnderTest.add(new long[]{1})),
        () -> assertEquals(0, classUnderTest.add(new long[]{0}))
      );
    }

    /**
     * testAdd_NumbersLt0() - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
     * <li>Create a group of two tests of the add methods with the following arrays of positive numbers:
     * <ol>
     * <li>-1</li>
     * <li>-10</li>
     * </ol>
     * Ensure the actual sum matches the expected sum for each group of numbers. Make sure that all groups of numbers
     * are
     * tested (i.e., if one fails, it should not fail the test method). Hint: use
     * {@link org.junit.jupiter.api.Assertions#assertAll(org.junit.jupiter.api.function.Executable...) assertAll()}
     * </ol>
     */
    @Test
    @DisplayName("Testing add with only one negative number")
    public void testAdd_NumbersLt0() {
      assertNotNull(classUnderTest);
      assertAll(
        () -> assertEquals(-1, classUnderTest.add(new long[]{-1})),
        () -> assertEquals(-10, classUnderTest.add(new long[]{-10}))
      );
    }
  }

  /**
   * Class-level Exercises:
   * <ol>
   * <li>Make this class a nested test class (hint: use {@link org.junit.jupiter.api.Nested @Nested}).
   * <li>Give the class a cool {@link org.junit.jupiter.api.DisplayName @DisplayName} that shows up in the JUnit test
   * report.</li>
   * </ol>
   * 
   */
  @Nested
  @DisplayName("Class that will test no operands")
  class JUnit5AppZeroOperandsTest {

    /**
     * testAdd_ZeroOperands_EmptyArgument() - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
     * <li>Pass an empty array as operands argument to the {@link com.makotojava.learn.hellojunit5.App#add(long[]) add}
     * method.</li>
     * <li>Ensure that an {@link java.lang.IllegalArgumentException IllegalArgumentException} is thrown (hint: use the
     * {@link org.junit.jupiter.api.Assertions#assertThrows(Class, org.junit.jupiter.api.function.Executable)
     * assertThrows()} method).</li>
     * </ol>
     */
    @Test
    @DisplayName("Testing add function with no operands")
    public void testAdd_ZeroOperands_EmptyArgument() {
      assertNotNull(classUnderTest);
      assertThrows(IllegalArgumentException.class, () -> assertEquals(-10, classUnderTest.add(new long[]{})));
    }

    /**
     * testAdd_ZeroOperands_NullArgument() - Exercises:
     * <ol>
     * <li>Tell JUnit this method is a test method.</li>
     * <li>Give it a cool display name for the test report.</li>
     * <li>The reference to the class under test cannot be null. If it is, the test should fail.</li>
     * <li>Pass an empty array as operands argument to the {@link com.makotojava.learn.hellojunit5.App#add(long[]) add}
     * method.</li>
     * <li>Ensure that an {@link java.lang.IllegalArgumentException IllegalArgumentException} is thrown (hint: use the
     * {@link org.junit.jupiter.api.Assertions#assertThrows(Class, org.junit.jupiter.api.function.Executable)
     * assertThrows()} method).</li>
     * <li>The test should fail if the message in the exception is not "Operands argument cannot be null".</li>
     * </ol>
     */
    @Test
    @DisplayName("Testing add with null argument passed as array")
    public void testAdd_ZeroOperands_NullArgument() {
      assertNotNull(classUnderTest);
      Throwable expectedException = assertThrows(IllegalArgumentException.class, () -> classUnderTest.add(null));
      assertEquals("Operands argument cannot be null", expectedException.getLocalizedMessage());
    }

  }

}
