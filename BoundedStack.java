import java.util.ArrayList;
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
    private static final int MAX_EMAIL = 1000;

    // ===== representation =====
    final List<String> emails;

    private void checkRep() {
        assert emails != null : "อีเมลต้องไม่เป็นnull";
        assert emails.size() <= MAX_EMAIL : "จำนวนอีเมลเกินจำนวนที่กำหนด"; 
       
        Set<String> seen = new HashSet<>();
        for (String s : emails) {
            assert s != null : "ชื่ออีเมลต้องไม่เป็นnull";
            assert !s.trim().isEmpty() : "ชื่ออีเมลต้องไม่เป็นสตริงว่าง";
            assert !seen.contains(s) : "ชื่ออีเมลต้องไม่ซ้ำกัน";
            seen.add(s);
        }
    }





// ===== Creator =====
   
    /**
     * สร้างเพลย์ลิสต์ว่าง
     */
    public BoundedStack() {
        this.emails = new ArrayList<>();
        checkRep();
    }

// ===== Creator2 =====
    /**
     * 
     * 
     * @param emails รายชื่อพนักงานที่ต้องการเพิ่มในระบบ ต้องไม่เป็น null และไม่เป็นช่องว่าง       
     */

    public BoundedStack(List<String> emails) {
        if (emails == null || emails.isEmpty()) {
            throw new IllegalArgumentException("อีเมลต้องไม่เป็น null หรือช่องว่าง: " );
        }
        if (emails.size() >= MAX_EMAIL) {
            throw new IllegalStateException("อีเมลเต็มแล้ว: ");
        }


        // เมื่อผ่าน Validation แล้วค่อยสร้าง Copy
        this.emails = new ArrayList<>(emails);
        checkRep();
    }





// ===== Mutators =====

     /**
     * TODO 6: เพิ่มรายชื่อในระบบ
     *
     * @param email ชื่ออีเมล ต้องไม่เป็น null และไม่เป็นสตริงว่าง
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้ามีอีเมลนี้อยู่แล้วหรือเต็มแล้ว
     * @throws IllegalArgumentException ถ้า email เป็น null หรือสตริงว่าง
     */

    public void push(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("ชื่ออีเมลต้องไม่เป็น null หรือสตริงว่าง");
        }
        if (emails.contains(email) || emails.size() >= MAX_EMAIL) {
            throw new IllegalStateException("อีเมลเต็มแล้ว");
        }
        emails.add(email);
        checkRep();
    }


        /**
     * TODO 7: ลบรายชื่อพนักงานออกจากระบบ
     *
     * @param email ชื่ออีเมลที่ต้องการลบ
     * @return true ถ้าลบสำเร็จ, false ถ้าไม่พบอีเมลนี้
     */
    public void pop(String email) {
        if (!emails.contains(email)) {
            throw new IllegalArgumentException("ไม่พบอีเมลนี้");
        }
        emails.remove(email);
        checkRep();
    }
        


    /**
     * obsever
     * 
     * 
     */
    public int size() {
        return emails.size();
    }

    public List<String> getEmails() {
        return new ArrayList<>(emails); 
    }

    /**
     * producer
     *
     */
    
    public boolean search(String email) {
        return emails.contains(email);
    }





}
