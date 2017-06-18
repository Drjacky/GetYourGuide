package ir.hosseinabbasi.getyourguide.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Dr.jacky on 2017/05/20.
 * Model classes for retrieving the DB table.
 */

//@Entity(nameInDb = "reviews")
public class ReviewPOJO {

    //@Unique
    //@Id(autoincrement = true)
    private Long mainId;

    //@Expose
    @SerializedName("status")
    @Property(nameInDb = "status")
    private boolean status;

    //@Expose
    @SerializedName("total_reviews")
    @Property(nameInDb = "total_reviews")
    private Long total_reviews;

    //@Convert(converter = DataConverter.class, columnType = String.class)
    @SerializedName("data")
    @Property(nameInDb = "data")
    //@ToMany(referencedJoinProperty = "id")
    //@ToMany(joinProperties = {@JoinProperty(name = "mainId", referencedName = "id")})
    private List<Data> data;
    //private DataType data;



    /*public enum DataType {
        Data
    }*/

    /*public static class DataConverter implements PropertyConverter<List<Data>, String> {
        @Override
        public List<Data> convertToEntityProperty(String databaseValue) {
            *//*if (databaseValue == null) {
                return null;
            }
            for (Data role : Data.values()) {
                if (role.id == databaseValue) {
                    return role;
                }
            }
            return Data.DEFAULT;*//*
            //return Data.valueOf(databaseValue);

            if (databaseValue == null)
                return null;
            else {
                List<Data> lista = Arrays.asList(databaseValue.split(","));
                return lista;
            }
        }

        @Override
        public String convertToDatabaseValue(List<String> entityProperty) {
            //return entityProperty == null ? null : entityProperty.;
            //return entityProperty.name();

                if(entityProperty == null)
                    return null;
                else {
                    StringBuilder sb= new StringBuilder();
                    for(String link : entityProperty){
                        sb.append(link);
                        sb.append(",");
                    }
                    return sb.toString();
                }
        }
    }*/

    /*@Entity
    public static class Data {

        @Id(autoincrement = true)
        private Long id;

        @Id
        @SerializedName("review_id")
        private Long review_id;

        @SerializedName("rating")
        @Property(nameInDb = "rating")
        private String rating;

        @SerializedName("title")
        @Property(nameInDb = "title")
        private String title;

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
    }*/


    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public List<Data> getData() {
        return this.data;

    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Long getTotalReview() {
        return this.total_reviews;

    }

    public void setTotalReview(Long total_reviews) {
        this.total_reviews = total_reviews;
    }

    public Long getTotal_reviews() {
        return this.total_reviews;
    }

    public void setTotal_reviews(Long total_reviews) {
        this.total_reviews = total_reviews;
    }

    public Long getMainId() {
        return this.mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }
}
