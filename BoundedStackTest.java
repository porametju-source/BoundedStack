
/**
 * Test runner 
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;   
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoundedStackTest {

    private static int passed = 0;
    private static int failed = 0;

    /** helper กลาง — พิมพ์ PASS/FAIL และนับผลให้เอง */
    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
    }
    public static void main(String[] args) {
       boolean assertOn = false;
       assert assertOn = true; // ถ้าเปิด assert จะทำให้ assertOn เป็น true
       if (!assertOn) {
           System.out.println("Please enable assertions (VM option -ea)");
       }
       System.out.println("=== EmployeeTest: start ===\n");

       testCreators();
       testPush();
       testPop();
       testObserve();
       testProducer();
       testExposure();

        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) {
            System.exit(1);
        }

}  
     private static void testCreators() {
       System.out.println("=== testCreators ===");
         BoundedStack empty = new BoundedStack();
        check("new() -> empty", empty.size() == 0);
        check("new() -> contains nothing", empty.size() == 0);

        BoundedStack r = new BoundedStack(Arrays.asList("A", "B", "C"));
        check("new(list) -> size 3", r.size() == 3);
        check("new(list) -> contains B", r.contains("B"));
        check("new((list) -> preserve order", r.employees.equals(Arrays.asList("A", "B", "C"))); 

        BoundedStack formEmpty = new BoundedStack(new ArrayList<String>());
        check("new(emptyList) -> empty", formEmpty.size() == 0);

        boolean threwcopy = false;
        try {
            new BoundedStack(Arrays.asList("A", "A"));
        } catch (IllegalArgumentException e) {
            threwcopy = true;
     }
        check("new(list with duplicates) -> throws", threwcopy);

        boolean threwNull = false;
        try {
            new BoundedStack(Arrays.asList("A", null));
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("new(list with null) -> throws", threwNull);
         
        boolean threwNullList = false;
        try {
            new BoundedStack(null);
        } catch (IllegalArgumentException e) {
            threwNullList = true;
        }
        check("new(null) -> throws", threwNullList);
    }

     private static void testPush() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testPush'");
    }
     private static void testPop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testPop'");
    }
    private static void testProducer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testProducer'");
    }
    private static void testObserve() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testObserve'");
    }
    private static void testExposure() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testExposure'");
    }
}
