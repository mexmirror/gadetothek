package ch.hsr.mge.gadgeothek;


public class HomeItem {
    private String title;
    private String description;

    public HomeItem(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
}