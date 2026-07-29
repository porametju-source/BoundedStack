
/**
 * Test runner 
 */
import java.util.Arrays;

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
       System.out.println("=== EmailTest: start ===\n");

       testCreators();
       testPush();
       testPop();
       testPeek();
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
        check("new() -> contains nothing", !empty.contains("Thanphat@gmail.com"));

        BoundedStack r = new BoundedStack(Arrays.asList("t@gmail.com", "r@gmail.com", "e@gmail.com"));
        check("new(list) -> size 3", r.size() == 3);
        check("new(list) -> contains t@gmail.com", r.contains("t@gmail.com"));
        check("new((list) -> preserve order", r.getEmails().equals(Arrays.asList("t@gmail.com", "r@gmail.com", "e@gmail.com"))); 

        // BoundedStack formEmpty = new BoundedStack(new ArrayList<String>());
        // check("new(emptyList) -> empty", formEmpty.size() == 0);

        BoundedStack length = new BoundedStack(Arrays.asList("Aomsincondee56@gmail.com"));
        check("new(list) -> email length", length.getEmails().equals(Arrays.asList("Aomsincondee56@gmail.com")));

        boolean threwcopy = false;
        try {
            new BoundedStack(Arrays.asList());
        } catch (IllegalArgumentException e) {
            threwcopy = true;
     }
        check("new(list with duplicates) -> throws IllegalArgumentException", threwcopy);

        boolean threwNull = false;
        try {
            new BoundedStack(Arrays.asList("t@gmail.com", null));
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("new(list with null) -> throws IllegalArgumentException", threwNull);
         
        boolean threwNullList = false;
        try {
            new BoundedStack( null);
        } catch (IllegalArgumentException e) {
            threwNullList = true;
        }
        check("new(null) -> throws IllegalArgumentException", threwNullList);
    }

    
     private static void testPush() {
        System.out.println("=== testPush ===");
        BoundedStack stack = new BoundedStack();
        stack.push("poramet123@gmail.com");
        check("push() -> size 1", stack.size() == 1);
        check("push() -> contains email", stack.contains("poramet123@gmail.com"));

        // test pushing empty
        boolean threwEmpty = false;
        try {
            stack.push("");
        } catch (IllegalArgumentException e) {
            threwEmpty = true;
        }
        check("push(empty string) -> throws IllegalArgumentException", threwEmpty);

        // test pushing null
        boolean threwNullEmail = false;
        try {
            stack.push(null);
        } catch (IllegalArgumentException e) {
            threwNullEmail = true;
        }
        check("push(null) -> throws IllegalArgumentException", threwNullEmail);
        



    }
     private static void testPop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testPop'"); 
       
    } private static void testPeek() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testPeek'");
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
