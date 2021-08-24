
package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrelloCard {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("closed")
    @Expose
    private Boolean closed;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("idBoard")
    @Expose
    private String idBoard;
    @SerializedName("idList")
    @Expose
    private String idList;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("subscribed")
    @Expose
    private Boolean subscribed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TrelloCard.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("closed");
        sb.append('=');
        sb.append(((this.closed == null)?"<null>":this.closed));
        sb.append(',');
        sb.append("desc");
        sb.append('=');
        sb.append(((this.desc == null)?"<null>":this.desc));
        sb.append(',');
        sb.append("idBoard");
        sb.append('=');
        sb.append(((this.idBoard == null)?"<null>":this.idBoard));
        sb.append(',');
        sb.append("idList");
        sb.append('=');
        sb.append(((this.idList == null)?"<null>":this.idList));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("subscribed");
        sb.append('=');
        sb.append(((this.subscribed == null)?"<null>":this.subscribed));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.subscribed == null)? 0 :this.subscribed.hashCode()));
        result = ((result* 31)+((this.idBoard == null)? 0 :this.idBoard.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.closed == null)? 0 :this.closed.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.idList == null)? 0 :this.idList.hashCode()));
        result = ((result* 31)+((this.desc == null)? 0 :this.desc.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TrelloCard) == false) {
            return false;
        }
        TrelloCard rhs = ((TrelloCard) other);
        return ((((((((this.subscribed == rhs.subscribed)||((this.subscribed!= null)&&this.subscribed.equals(rhs.subscribed)))&&((this.idBoard == rhs.idBoard)||((this.idBoard!= null)&&this.idBoard.equals(rhs.idBoard))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.closed == rhs.closed)||((this.closed!= null)&&this.closed.equals(rhs.closed))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.idList == rhs.idList)||((this.idList!= null)&&this.idList.equals(rhs.idList))))&&((this.desc == rhs.desc)||((this.desc!= null)&&this.desc.equals(rhs.desc))));
    }

}
