import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


    


//รายชื่อนิสิต
// 6821651477 นาย ปรเมษฐ์ จันทร หมู่801
// 6821651302 นาย ธนภัทร พรมจุ้ย หมู่801


//AF
    //AF(emails) = รายชื่อพนักงานที่อยู่ในระบบ 
    //[รายชื่อ email A@gmail.com, รายชื่อ email B@gmail.com, รายชื่อ email C@gmail.com]
    //จะต้องสามารถแสดงรายชื่อีเมลทั้งหมดที่อยู่ในระบบได้
    //จะต้องสามารถเสิร์จรายชื่ออีเมลที่อยู่ในระบบได้



//RI
    //  RI = emails != null
    //ชื่ออีเมลต้องไม่เป็น null
    //ชื่ออีเมลต้องไม่เป็นสตริงว่าง
    //ชื่ออีเมลต้องไม่ซ้ำกัน
    //อีเมล ต้องอยู่ระหว่าง 0 ถึง max
    


//Safety from rep exposure
    // Safety from rep exposure:
    //BoundedStack ต้องเป็นprivate final
    //ต้องบันทึกขาเข้าขาออก
    
/**
 * BoundedStack — ADT ระบบจัดเก็บรายชื่ออีเมล
 *
 * ค่านามธรรม (A): ลำดับของรายชื่อ เช่น [รายชื่อA@gmail.com, รายชื่อB@gmail.com, รายชื่อC@gmail.com]
 *
 * ตัวอย่างการใช้งาน:
 *     BoundedStack emailStack = new BoundedStack(3);
 *     emailStack.push("thqnaphat.pro@gmail.com");
 *     emailStack.push("krisana@gmail.com");
 *     System.out.println(emailStack.size());   // 2
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
            assert s.length() <= 30 : "ชื่ออีเมลต้องไม่เกิน 30 ตัวอักษร";
            seen.add(s);
        }
    }





// ===== Creator =====
   
    /**
     * สร้างรายชื่ออีเมลว่าง
     */
    public BoundedStack() {
        this.emails = new ArrayList<>();
        checkRep();
    }

// ===== Creator2 =====
    /**
     *@param emails รายชื่ออีเมลที่ต้องการเพิ่มในระบบ ต้องไม่เป็น null และไม่เป็นช่องว่าง       
     *@throws IllegalArgumentException ถ้า emails เป็น null หรือช่องว่าง
     */

    public BoundedStack(List<String> emails) {
        if (emails == null || emails.isEmpty()) {
            throw new IllegalArgumentException("อีเมลต้องไม่เป็น null หรือช่องว่าง: " );
        }
        if (emails.size() >= MAX_EMAIL) {
            throw new IllegalStateException("อีเมลเต็มแล้ว: ");
        }
        Set<String> seen = new HashSet<>();
        for (String email : emails) {
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("ชื่ออีเมลต้องไม่เป็น null หรือช่องว่าง: " + email);
            }
            if (seen.contains(email)) {
                throw new IllegalArgumentException("ชื่ออีเมลต้องไม่ซ้ำกัน: " + email);
            }
            if(email.length() > 30){
                throw new IllegalArgumentException("ชื่ออีเมลต้องไม่เกิน 30 ตัวอักษร: " + email);
            }
            seen.add(email);
        }
        // เมื่อผ่าน Validation แล้วค่อยสร้าง Copy
        this.emails = new ArrayList<>(emails);
        checkRep();
    }





// ===== Mutators =====

     /**
     * เพิ่มรายชื่ออีเมลในระบบ
     *
     * @param email ชื่ออีเมล ต้องไม่เป็น null และไม่เป็นสตริงว่าง
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้ามีอีเมลนี้อยู่แล้วหรือเต็มแล้ว
     * @throws IllegalArgumentException ถ้า email เป็น null หรือสตริงว่าง
     * @throws IllegalStateException ถ้าอีเมลเต็มแล้ว
     */

    public void push(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("ชื่ออีเมลต้องไม่เป็น null หรือสตริงว่าง");
        }
        if (emails.contains(email) || emails.size() >= MAX_EMAIL) {
            throw new IllegalStateException("อีเมลเต็มแล้ว");
        }
        if(email.length() > 30){
            throw new IllegalArgumentException("ชื่ออีเมลต้องไม่เกิน 30 ตัวอักษร: " + email);
        }
        emails.add(email);
        checkRep();
    }


     /**
     *  ลบรายชื่ออีเมลออกจากระบบ
     *
     * @param email ชื่ออีเมลที่ต้องการลบ
     * @return true ถ้าลบสำเร็จ, false ถ้าไม่พบอีเมลนี้
     * @throws IllegalArgumentException ถ้า email ไม่พบในระบบ
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
     * param email ชื่ออีเมลที่ต้องการค้นหา
     * @return true ถ้าพบอีเมลนี้, false ถ้าไม่พบ
        
     */
    public int size() {
        return emails.size();
    }

    public List<String> getEmails() {
        return new ArrayList<>(emails); 
       }
     public boolean contains(String string) {
		return emails.contains(string);
	}


    /**
     * producer
     * @param email ชื่ออีเมลที่ต้องการค้นหา
     * @return true ถ้าพบอีเมลนี้, false ถ้าไม่พบ
     
     */
     public boolean reverse(){
        java.util.Collections.reverse(emails);
       checkRep(); 
       return true;
     }
	





}
