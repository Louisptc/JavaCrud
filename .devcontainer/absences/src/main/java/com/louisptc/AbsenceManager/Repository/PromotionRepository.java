package com.louisptc.AbsenceManager.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.louisptc.AbsenceManager.Model.DatabaseConnection;
import com.louisptc.AbsenceManager.Model.Promotion;


public class PromotionRepository {

    private DatabaseConnection dbConnection;

    public PromotionRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

   public List<Promotion> seePromotions() {
        List<Promotion> promotions = new ArrayList<>();
        String sql = "SELECT promotion_id, promotion_name, promotion_year FROM promotions";
        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                promotions.add(new Promotion(
                    rs.getInt("promotion_id"),
                    rs.getString("promotion_name"),
                    rs.getInt("promotion_year")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return promotions;
    }

    public void seeStudentsOfPromotion(int promotionId) {
        String sql = "SELECT first_name, last_name FROM students WHERE promotion_id = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, promotionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    System.out.println(firstName + " " + lastName);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createPromotion(String name, int year) {
        String sql = "INSERT INTO promotions (promotion_name, promotion_year) VALUES (?, ?)";
        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, year);
            pstmt.executeUpdate();
            System.out.println("Promotion created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePromotion(int promotionId, String name, int year) {
        String sql = "UPDATE promotions SET promotion_name = ?, promotion_year = ? WHERE promotion_id = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, year);
            pstmt.setInt(3, promotionId);
            pstmt.executeUpdate();
            System.out.println("Promotion updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePromotion(int promotionId) {
        String sql = "DELETE FROM promotions WHERE promotion_id = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, promotionId);
            pstmt.executeUpdate();
            System.out.println("Promotion deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
