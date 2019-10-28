package com.example.studentprofilebuilder;

import android.graphics.drawable.Drawable;

public class Profile {
    String fname;
    String lname;
    Long stuId;
    String Department;
    Drawable imageDrawable;

    int drawablePath;

    public int getDrawablePath() {
        return drawablePath;
    }

    public void setDrawablePath(int drawablePath) {
        this.drawablePath = drawablePath;
    }



    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public Drawable getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(Drawable imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", stuId=" + stuId +
                ", Department='" + Department + '\'' +
                ", imageDrawable=" + imageDrawable +
                ", drawablePath=" + drawablePath +
                '}';
    }
}
