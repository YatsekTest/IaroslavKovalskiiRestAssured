package constants;

public enum Tags {
    NAME("name"),
    ID("id"),
    DESCRIPTION("desc"),
    CLOSED("closed"),
    KEY("key"),
    API_KEY("apiKey"),
    TOKEN("token"),
    ID_BOARD("idBoard"),
    SUBSCRIBED("subscribed"),
    ID_LIST("idList");

    private String tag;

    public String getTag() {
        return tag;
    }

    Tags(String tag) {
        this.tag = tag;
    }
}
