package AbsenceManager.Repository;

import AbsenceManager.Model.DatabaseConnection;
import AbsenceManager.Model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private DatabaseConnection dbConnection;

    public StudentRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void createStudent(Student student) {
        String sql = "INSERT INTO students (first_name, last_name, email, phone_number, promotion_id, absence_count, is_delegate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPhone());
            pstmt.setInt(5, student.getPromotionId());
            pstmt.setInt(6, student.getAbsenceCount());
            pstmt.setBoolean(7, student.isIsDelegate());
            pstmt.executeUpdate();
            System.out.println("Student created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateStudent(Student student) {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ?, phone_number = ?, promotion_id = ?, absence_count = ?, is_delegate = ? WHERE student_id = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPhone());
            pstmt.setInt(5, student.getPromotionId());
            pstmt.setInt(6, student.getAbsenceCount());
            pstmt.setBoolean(7, student.isIsDelegate());
            pstmt.setInt(8, student.getId());
            pstmt.executeUpdate();
            System.out.println("Student updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
            System.out.println("Student deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> seeAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getInt("promotion_id"),
                        rs.getInt("absence_count"),
                        rs.getBoolean("is_delegate"));
                student.setId(rs.getInt("student_id"));
                students.add(student);
            }
            students.forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }
}
