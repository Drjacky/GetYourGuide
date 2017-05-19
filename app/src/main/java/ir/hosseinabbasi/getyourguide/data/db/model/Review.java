package ir.hosseinabbasi.getyourguide.data.db.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

@Entity(nameInDb = "reviews")
public class Review {

    @Id
    private Long id;

    @Expose
    @SerializedName("data")
    @Property(nameInDb = "data")
    @ToMany(referencedJoinProperty = "review_id")
    private List<dataz> datas;

    @Entity
    public static class dataz {
        @Id
        private Long id;

        @Expose
        @SerializedName("review_id")
        @Id
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
    }

    public List<dataz> getDatas() {
        return datas;
    }

    public void setDatas(List<dataz> datas) {
        this.datas = datas;
    }

    @Expose
    @SerializedName("total_reviews")
    @Property(nameInDb = "total_reviews")
    private Long total_reviews;

    @Generated()
    public Review(Long review_id, String rating, String title, String message,
            Long total_reviews) {
        /*this.review_id = review_id;
        this.rating = rating;
        this.title = title;
        this.message = message;
        this.total_reviews = total_reviews;*/
    }

    @Generated()
    public Review() {
    }



    public Long getTotalReview() {
        return this.total_reviews;
    }

    public void setTotalReview(Long total_reviews) {
        this.total_reviews = total_reviews;
    }
}
