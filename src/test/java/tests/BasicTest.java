package tests;

import org.checkerframework.checker.securerandom.SecureRandomChecker;
import org.checkerframework.framework.test.CheckerFrameworkPerFileTest;
import org.checkerframework.framework.test.TestUtilities;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.AbstractProcessor;

public class BasicTest extends CheckerFrameworkPerFileTest {

    public BasicTest(File testFile) {
        super(testFile, SecureRandomChecker.class, "basic", "-Anomsgtext", "-nowarn");
    }

    @Parameters
    public static List<File> getTestFiles() {
        return new ArrayList<>(
            TestUtilities.findRelativeNestedJavaFiles("tests", "basic"));
    }
}
