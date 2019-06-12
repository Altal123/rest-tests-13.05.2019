package PetStore.models;

public class CategoryModel {
    private String id;
    private String name;

    public CategoryModel(String id, String categoryName) {
        this.id = id;
        this.name = categoryName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
