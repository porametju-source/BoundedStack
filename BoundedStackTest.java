
/**
 * Test runner 
 */
import java.util.Arrays;
import java.util.List;


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

       

        BoundedStack length = new BoundedStack(Arrays.asList("Aomsincondee56@gmail.com"));
        check("new(list) -> email length", length.getEmails().equals(Arrays.asList("Aomsincondee56@gmail.com")));

        boolean threwcopy = false;
        try {
            new BoundedStack(Arrays.asList("t@gmail.com", "t@gmail.com"));
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


        boolean threwEmpty = false;
        try {
            stack.push("");
        } catch (IllegalArgumentException e) {
            threwEmpty = true;
        }
        check("push(empty string) -> throws IllegalArgumentException", threwEmpty);

      
        boolean threwNullEmail = false;
        try {
            stack.push(null);
        } catch (IllegalArgumentException e) {
            threwNullEmail = true;
        }
        check("push(null) -> throws IllegalArgumentException", threwNullEmail);
    }
     private static void testPop() {
        System.out.println("=== testPop ===");
        BoundedStack stack = new BoundedStack(Arrays.asList("t@gmail.com","r@gmail.com"));
        stack.pop("t@gmail.com");
        check("pop existing email -> size 1", stack.size() == 1);
        check("pop existing email -> contains false", !stack.contains("t@gmail.com"));
        check("pop existing email -> contains true", stack.contains("r@gmail.com"));
        

        boolean threwNotFound = false;
        try {
            stack.pop("nonexistent@gmail.com");
        } catch (IllegalArgumentException e) {
            threwNotFound = true;
        }
        check("pop non-existent email -> throws IllegalArgumentException", threwNotFound);

        BoundedStack emptyStack = new BoundedStack();
        boolean threwEmpty = false;
        try {
            emptyStack.pop("t@gmail.com");
        } catch (IllegalArgumentException e) {
            threwEmpty = true;
        }
        check("pop from empty stack -> throws IllegalArgumentException", threwEmpty);

        boolean threwNull = false;
        try {
            stack.pop(null);
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("pop null email -> throws IllegalArgumentException", threwNull);
    }
    private static void testProducer() {
        System.out.println("=== testProducer ===");
        BoundedStack a = new BoundedStack();

        a.push("email1@gmail.com");
        a.push("email2@gmail.com");
        a.push("email3@gmail.com");

        a.reverse();

        check("reverse() -> size 3", a.size() == 3);

        check("reverse() -> contains email3@gmail.com", a.contains("email3@gmail.com"));
    }
    private static void testObserve() {
       System.out.println("=== testObserve ===");
        BoundedStack list = new BoundedStack(Arrays.asList("t@gmail.com","r@gmail.com","e@gmail.com"));
        check("size() with emails -> 3",list.size() == 3);
        List<String> present = list.getEmails();
        check("getEmails() returns correct list", present.equals(Arrays.asList("t@gmail.com", "r@gmail.com", "e@gmail.com")));
        present.add("adjust@Gmail.com");
        check("getEmails() defensive copy, not reference", list.size() == 3);
        check("contains() -> existing email true", list.contains("t@gmail.com"));
        check("contains() -> non-existing email false", !list.contains("safe@gmail.com"));
        check("contains(null) -> false", !list.contains(null));
    }
    private static void testExposure() {
       System.out.println("=== testExposure ===");
        BoundedStack stack = new BoundedStack(Arrays.asList("scam@gmail.com","IDk345@gmail.com"));

        check("contains(existing email) -> true", stack.contains("scam@gmail.com"));
        check("contains(non-existing email) -> false", !stack.contains("safe@gmail.com"));
        check("contains(null) -> false", !stack.contains(null));
    }
}
