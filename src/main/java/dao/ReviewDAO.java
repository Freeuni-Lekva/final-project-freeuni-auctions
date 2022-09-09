package dao;
import models.Report;
import models.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//needs to be added average review on profile page
public class ReviewDAO extends DAO{
    private static final String TABLE_NAME = "reviews";
    public static final String ATTRIBUTE = "ReviewDAO";
    private Connection conn;
    public ReviewDAO(Connection conn){
        this.conn = conn;
    }

    public Review getReviewFromID(long id)  throws SQLException {
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
        stm.setLong(1, id);
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            stm.close();
            return createReview(rs);
        } else return null;
    }

    public List<Review> getAllReviewsForAccount(long user_id) throws SQLException {
        List<Review> res = new ArrayList<Review>();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE account_id = ?");
        stm.setLong(1, user_id);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            res.add(createReview(rs));
        }
        return res;
    }
    public List<Review> getAllReviewsForProduct(long product_id) throws SQLException {
        List<Review> res = new ArrayList<Review>();
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE product_id = ?");
        stm.setLong(1, product_id);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            res.add(createReview(rs));
        }
        return res;
    }

    private Review createReview(ResultSet rs) throws SQLException {
        long id = Long.parseLong(rs.getString("id"));
        long user_id = Long.parseLong(rs.getString("user_id"));
        long product_id = Long.parseLong(rs.getString("product_id"));
        long costumer_id =  Long.parseLong(rs.getString("costumer_id"));
        String review = rs.getString("reviewText"); //add review pagedan unda wamoigos review text an type
        return new Review(id, user_id, product_id, costumer_id, review);
    }

    public void addReview(Review rev) throws SQLException {
        PreparedStatement stm = conn.prepareStatement("INSERT INTO " + TABLE_NAME +
                " (user_id, producy_id, costumer_id, reviewText) VALUES ( ?, ?, ?, ? )");
        stm.setLong(1, rev.getUser_id());
        stm.setLong(2, rev.getProduct_id());
        stm.setLong(3, rev.getCostumer_id());
        stm.setString(4, rev.getReviewText());
        stm.executeQuery();
        stm.close();
    }
}
