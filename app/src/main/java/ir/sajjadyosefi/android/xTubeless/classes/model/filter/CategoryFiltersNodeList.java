package ir.sajjadyosefi.android.xTubeless.classes.model.filter;

public class CategoryFiltersNodeList {
    private CategoryFiltersNode head;
    private CategoryFiltersNode current;


    public CategoryFiltersNode getCurrent() {
        return current;
    }

    public void setCurrent(CategoryFiltersNode current) {
        this.current = current;
    }

    public CategoryFiltersNode getHead() {
        return head;
    }

    public void setHead(CategoryFiltersNode head) {
        this.head = head;
    }

    public String printAllNodes() {
        StringBuilder stringBuilder = new StringBuilder();
        CategoryFiltersNode current = head;
        while (current != null)
        {
            stringBuilder.append(current.getTitle() + "\\");
            current = current.getNextNode();
        }

        return stringBuilder.toString();
    }

    public void AddFirst(CategoryFiltersNode data){
        CategoryFiltersNode toAdd = data;
        toAdd.setNextNode(head);
        head = toAdd;
    }


    public void AddLast(CategoryFiltersNode data) {
        if (head == null)
        {
            head = new CategoryFiltersNode();

            head = data;
            head.setNextNode(null);
        }
        else
        {
            CategoryFiltersNode toAdd = data;
            CategoryFiltersNode current = head;
            while (current.getNextNode() != null)
            {
                current = current.getNextNode();
            }

            current.setNextNode(toAdd);
        }
    }
    public CategoryFiltersNode getLast() {
        if (head == null){
            return null;
        }
        else {
            CategoryFiltersNode current = head;
            while (current.getNextNode() != null)
            {
                current = current.getNextNode();
            }

            return current;
        }
    }


}
