package com.twu28.biblioteca;

public class User {
    String userID;
    String password;
    public User(String userID,String password){
        this.userID=userID;
        this.password=password;
    }
    public String toString(){
        return userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (userID != null ? !userID.equals(user.userID) : user.userID != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
