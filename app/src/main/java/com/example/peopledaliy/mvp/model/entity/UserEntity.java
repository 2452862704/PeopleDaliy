package com.example.peopledaliy.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserEntity implements Parcelable {
    @Id(autoincrement = true)
    private Long id;
    public int user_id;
    public int user_phone;
    public String user_pwd;
    public String user_name;
    public String user_sex;
    public String user_img;
    public int user_integral;
    @Generated(hash = 145257036)
    public UserEntity(Long id, int user_id, int user_phone, String user_pwd,
            String user_name, String user_sex, String user_img, int user_integral) {
        this.id = id;
        this.user_id = user_id;
        this.user_phone = user_phone;
        this.user_pwd = user_pwd;
        this.user_name = user_name;
        this.user_sex = user_sex;
        this.user_img = user_img;
        this.user_integral = user_integral;
    }
    @Generated(hash = 1433178141)
    public UserEntity() {
    }

    protected UserEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        user_id = in.readInt();
        user_phone = in.readInt();
        user_pwd = in.readString();
        user_name = in.readString();
        user_sex = in.readString();
        user_img = in.readString();
        user_integral = in.readInt();
    }

    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel in) {
            return new UserEntity(in);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getUser_id() {
        return this.user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getUser_phone() {
        return this.user_phone;
    }
    public void setUser_phone(int user_phone) {
        this.user_phone = user_phone;
    }
    public String getUser_pwd() {
        return this.user_pwd;
    }
    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }
    public String getUser_name() {
        return this.user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getUser_sex() {
        return this.user_sex;
    }
    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }
    public String getUser_img() {
        return this.user_img;
    }
    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }
    public int getUser_integral() {
        return this.user_integral;
    }
    public void setUser_integral(int user_integral) {
        this.user_integral = user_integral;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeInt(user_id);
        dest.writeInt(user_phone);
        dest.writeString(user_pwd);
        dest.writeString(user_name);
        dest.writeString(user_sex);
        dest.writeString(user_img);
        dest.writeInt(user_integral);
    }
}
