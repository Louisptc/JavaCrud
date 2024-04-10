package AbsenceManager.Model;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int promotionId;
    private int absenceCount;
    private boolean isDelegate;

     

    public Student(String firstName, String lastName, String email, String phone, int promotionId, int absenceCount, boolean isDelegate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.promotionId = promotionId;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public int getAbsenceCount() {
        return absenceCount;
    }

    public boolean isIsDelegate() {
        return isDelegate;
    }

    public void setAbsenceCount(int absenceCount) {
        this.absenceCount = absenceCount;
    }

    public void setIsDelegate(boolean isDelegate) {
        this.isDelegate = isDelegate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }
    
}
