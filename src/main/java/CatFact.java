import com.fasterxml.jackson.annotation.JsonProperty;

public class CatFact {
    private String id;
    private String text;
    private Animals type;
    private String user;
    private int upvotes;

    public CatFact(@JsonProperty("id") String id,
                   @JsonProperty("text") String text,
                   @JsonProperty("type") Animals type,
                   @JsonProperty("user") String user,
                   @JsonProperty("upvotes") Integer upvotes) {

        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes == null ?0:upvotes;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "CatFact{" +
                ", text='" + text + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }
}
