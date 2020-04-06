package domain;

public class Comment {
    private String name,comment;
    private int rating;

    public Comment(String name, String comment, int rating){
        setName(name);
        setComment(comment);
        setRating(rating);
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
