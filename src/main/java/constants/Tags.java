package constants;

public enum Tags {
    NAME("name"),
    ID("id"),
    DESCRIPTION("desc"),
    CLOSED("closed"),
    KEY("key"),
    TOKEN("token");

    public String tag;

    Tags(String tag) {
        this.tag = tag;
    }
}
