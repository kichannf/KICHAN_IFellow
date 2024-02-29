package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@DisplayName("Набор тестов HW_3")
@SelectClasses({AuthJiraTest.class, ActionsTest.class})
public class SuitCaseTest {

}
