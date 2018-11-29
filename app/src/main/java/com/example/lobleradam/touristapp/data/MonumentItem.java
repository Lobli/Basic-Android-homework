package com.example.lobleradam.touristapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

@Entity(tableName = "monumentitem")
public class MonumentItem {

    public enum Category {
        STATUE,BUILDING,BRIDGE,LANDSCAPE;

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

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "category")
    public Category category;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "description")
    public String description;

}
