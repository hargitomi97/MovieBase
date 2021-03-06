package hu.bme.aut.moviebase.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.os.Parcel;
import android.os.Parcelable;


@Entity(tableName = "movie")
public class Movie_ implements Parcelable {

    public Movie_() {

    }

    private Movie_(Parcel in) {
        id = in.readLong();
        name = in.readString();
        category = (Category) in.readSerializable();
        length = in.readInt();
        description = in.readString();
        rating = in.readFloat();
        price = in.readInt();
    }

    public static final Creator<Movie_> CREATOR = new Creator<Movie_>() {
        @Override
        public Movie_ createFromParcel(Parcel in) {
            return new Movie_(in);
        }

        @Override
        public Movie_[] newArray(int size) {
            return new Movie_[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeSerializable(category);
        dest.writeInt(length);
        dest.writeString(description);
        dest.writeFloat(rating);
        dest.writeInt(price);
    }


    public enum Category {
        ACTION, DOCUMENTARY, HORROR, COMEDY, ROMANCE;


        @TypeConverter
        public static Category getByOrdinal(int ordinal) {
            Category ret = null;
            for (Category cat : Category.values()) {
                if (cat.ordinal() == ordinal) {
                    ret = cat;
                    break;
                }
            }
            return ret;
        }

        @TypeConverter
        public static int toInt(Category category) {
            return category.ordinal();
        }
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "category")
    public Category category;

    @ColumnInfo(name = "length")
    public int length;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "rating")
    public float rating;

    @ColumnInfo(name = "price")
    public int price;

    @ColumnInfo(name = "uid")
    public long uid;
}
