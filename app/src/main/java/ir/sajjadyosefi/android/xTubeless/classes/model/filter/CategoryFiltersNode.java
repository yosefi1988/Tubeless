package ir.sajjadyosefi.android.xTubeless.classes.model.filter;

public class CategoryFiltersNode {
    private int catId = 0;
    private String title = "";
    private String pathTitle = "";
    private String description = "";
    private CategoryFiltersNode nextNode;

    private CategoryFiltersNode preNode;


    public CategoryFiltersNode getNextNode() {
        return nextNode;
    }
    public CategoryFiltersNode getPreNode() {
        return preNode;
    }

    public void setNextNode(CategoryFiltersNode nextNode) {
        this.nextNode = nextNode;
    }


    public void setPreNode(CategoryFiltersNode preNode) {
        this.preNode = preNode;
    }


    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPathTitle() {
        return pathTitle;
    }

    public void setPathTitle(String pathTitle) {
        this.pathTitle = pathTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
