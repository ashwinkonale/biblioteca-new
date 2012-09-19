package com.twu28.biblioteca;

public class Movie {
    private String rating;
    private String movieName;
    private String directorName;

    public Movie(String movieName, String directorName, String rating) {
        this.movieName=movieName;
        this.directorName =directorName;
        this.rating=rating;
    }
    public String display(){
//        String pattern="%-20s%-15.12s%10s\n";
//        System.out.printf(pattern,movieName,directorName,rating);
////        return movieName+" "+directorName+" "+rating;
        String name=String.format("%-20s",movieName);
        String director=String.format("%-20s",directorName);
        String rating1=String.format("%10s",rating);
        return name+""+director+""+rating1;
    }

}
