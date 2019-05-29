package PetStore.models;

public class CategoryModel {
    private int id;
    private String name;

    public CategoryModel(int id, String categoryName) {
        this.id = id;
        this.name = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
