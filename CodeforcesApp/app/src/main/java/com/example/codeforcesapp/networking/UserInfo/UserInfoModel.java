package com.example.codeforcesapp.networking.UserInfo;

public class UserInfoModel {
    private String firstName;
    private String lastName;
    private String rank;
    private String maxRank;
    private String rating;
    private String maxRating;
    private String country;
    private String proPicUrl;

    private UserInfoModel(Builder builder){
        firstName= builder.firstName;
        lastName= builder.lastName;
        rank= builder.rank;
        maxRank= builder.maxRank;
        rating= builder.rating;
        maxRating= builder.maxRating;
        country= builder.country;
        proPicUrl= builder.proPicUrl;
    }

    public String getFullName(){ return firstName+" "+lastName; }

    public String getProPicUrl() {
        return proPicUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRank() {
        return rank;
    }

    public String getMaxRank() {
        return maxRank;
    }

    public String getRating() {
        return rating;
    }

    public String getMaxRating() {
        return maxRating;
    }

    public String getCountry() {
        return country;
    }

    public static class Builder{
        private String firstName, lastName, rank, maxRank, rating, maxRating, country, proPicUrl;

        Builder(){

        }

        public UserInfoModel build(){
            return new UserInfoModel(this);
        }

        public Builder firstName(String name){
            firstName= name;
            return this;
        }

        public Builder lastName(String name){
            lastName= name;
            return this;
        }

        public Builder rank(String r){
            rank= r;
            return this;
        }

        public Builder maxRank(String r){
            maxRank= r;
            return this;
        }

        public Builder rating(String r){
            rating= r;
            return this;
        }

        public Builder maxRating(String m){
            maxRating= m;
            return this;
        }

        public Builder Country(String c){
            country= c;
            return this;
        }

        public Builder proPicUrl(String url){
            proPicUrl= "http:"+url;
            return this;
        }


    }

}
