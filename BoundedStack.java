import java.util.ArrayList;
import java.util.Collections;   
import java.util.HashSet;
import java.util.List;
import java.util.Set;


    


//รายชื่อนิสิต
// 6821651477 นาย ปรเมษฐ์ จันทร หมู่801
// 6821651302 นาย ธนภัทร พรมจุ้ย หมู่801


//AF
    //AF(employees) =af(employees) = รายชื่อพนักงานที่อยู่ในระบบ 



//RI
    //  RI = employees != null
    // ชื่อพนักงานต้องไม่เป็น null
    //ชื่อพนักงานต้องไม่เป็นสตริงว่าง
    //ชื่อพนักงานต้องไม่ซ้ำกัน
    //พนักงาน ต้องอยู่ระหว่าง 0 ถึง max
    


//Safety from rep exposure
    // Safety from rep exposure:
    //BoundedStack ต้องเป็นprivate final
    //ต้องบันทึกขาเข้าขาออก
    
/**
 * BoundedStack — ADT ระบบจัดเก็บรายชื่อรายชื่อพนักงาน
 *
 * ค่านามธรรม (A): ลำดับของรายชื่อ เช่น [รายชื่อA, รายชื่อB, รายชื่อC]
 *
 * ตัวอย่างการใช้งาน:
 *     BoundedStack b = new BoundedStack(3);
 *     b.add("thanaphat promjui");
 *     b.add("poramet junthorn");
 *     System.out.println(b.size());   // 2
 */
public class BoundedStack{
    private static final int MAX_EMPLOYEES = 1000;

    // ===== representation =====
    final List<String> employees;

    private void checkRep() {
        assert employees != null : "พนักงานต้องไม่เป็นnull";
        assert employees.size() <= MAX_EMPLOYEES : "จำนวนพนักงานเกินจำนวนที่กำหนด"; 
       
        Set<String> seen = new HashSet<>();
        for (String s : employees) {
            assert s != null : "ชื่อพนักงานต้องไม่เป็นnull";
            assert !s.trim().isEmpty() : "ชื่อพนักงานต้องไม่เป็นสตริงว่าง";
            assert !seen.contains(s) : "ชื่อพนักงานต้องไม่ซ้ำกัน";
            seen.add(s);
        }
    }





// ===== Creator =====
   
    /**
     * สร้างเพลย์ลิสต์ว่าง
     */
    public BoundedStack() {
        this.employees = new ArrayList<>();
        checkRep();
    }

// ===== Creator2 =====
    /**
     * 
     * 
     * @param employees
     */

    public BoundedStack(List<String> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("ชื่อพนักงานต้องไม่เป็น null หรือช่องว่าง: " );
        }
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new IllegalStateException("ชื่อพนักงานเต็มแล้ว: ");
        }


        // เมื่อผ่าน Validation แล้วค่อยสร้าง Copy
        this.employees = new ArrayList<>(employees);
        checkRep();
    }





// ===== Mutators =====

     /**
     * TODO 6: เพิ่มรายชื่อในระบบ
     *
     * @param employee ชื่อพนักงาน ต้องไม่เป็น null และไม่เป็นสตริงว่าง
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้ามีพนักงานนี้อยู่แล้วหรือเต็มแล้ว
     * @throws IllegalArgumentException ถ้า employee เป็น null หรือสตริงว่าง
     */

    public void push(String employee) {
        if (employee == null || employee.trim().isEmpty()) {
            throw new IllegalArgumentException("ชื่อพนักงานต้องไม่เป็น null หรือสตริงว่าง");
        }
        if (employees.contains(employee) || employees.size() >= MAX_EMPLOYEES) {
            throw new IllegalStateException("ชื่อพนักงานเต็มแล้ว");
        }
        employees.add(employee);
        checkRep();
    }


        /**
     * TODO 7: ลบรายชื่อพนักงานออกจากระบบ
     *
     * @param employee ชื่อพนักงานที่ต้องการลบ
     * @return true ถ้าลบสำเร็จ, false ถ้าไม่พบพนักงานนี้
     */
    public void pop(String employee) {
        if (!employees.contains(employee)) {
            throw new IllegalArgumentException("ไม่พบพนักงานนี้");
        }
        employees.remove(employee);
        checkRep();
    }
        


    /**
     * obsever
     * 
     * 
     */
    public int size() {
        return employees.size();
    }

    public List<String> getEmployees() {
        return new ArrayList<>(employees); 
    }

    /**
     * producer
     *
     */
    public void reverseemployees(List<String> employees) {
        Collections.reverse(employees);
        
    }
    public boolean contains(String employee) {
        return employees.contains(employee);
    }





}
