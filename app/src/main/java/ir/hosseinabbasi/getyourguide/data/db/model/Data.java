package ir.hosseinabbasi.getyourguide.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Dr.jacky on 2017/05/20.
 */

@Entity
public class Data {

    @Id/*(autoincrement = true)*/
    @Expose
    private Long id;

    @Expose
    @Index
    @SerializedName("review_id")
    private Long review_id;

    @Expose
    @SerializedName("rating")
    @Property(nameInDb = "rating")
    private String rating;

    @Expose
    @SerializedName("title")
    @Property(nameInDb = "title")
    private String title;

    @Expose
    @SerializedName("message")
    @Property(nameInDb = "message")
    private String message;

    @Generated(hash = 462802085)
    public Data(Long id, Long review_id, String rating, String title,
            String message) {
        this.id = id;
        this.review_id = review_id;
        this.rating = rating;
        this.title = title;
        this.message = message;
    }

    public Data() {
    }

    public Long getReviewId() {
        return this.review_id;
    }

    public void setReviewId(Long review_id) {
        this.review_id = review_id;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReview_id() {
        return this.review_id;
    }

    public void setReview_id(Long review_id) {
        this.review_id = review_id;
    }
}
