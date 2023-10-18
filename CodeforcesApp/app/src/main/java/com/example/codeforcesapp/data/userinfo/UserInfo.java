package com.example.codeforcesapp.data.userinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("firstName")
    @Expose
    String firstName;

    @SerializedName("lastName")
    @Expose
    String lastName;

    @SerializedName("rank")
    @Expose
    String rank;

    @SerializedName("maxRank")
    @Expose
    String maxRank;

    @SerializedName("rating")
    @Expose
    String rating;

    @SerializedName("maxRating")
    @Expose
    String maxRating;

    @SerializedName("country")
    @Expose
    String country;

    @SerializedName("titlePhoto")
    @Expose
    String titlePhoro;

    public String getTitlePhoto() {
        return titlePhoro;
    }

    public void setTitlePhoro(String titlePhoro) {
        this.titlePhoro = titlePhoro;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getMaxRank() {
        return maxRank;
    }

    public void setMaxRank(String maxRank) {
        this.maxRank = maxRank;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(String maxRating) {
        this.maxRating = maxRating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
