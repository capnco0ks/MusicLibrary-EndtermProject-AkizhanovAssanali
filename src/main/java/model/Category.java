package model;

public class Category {
    private int id;
    private String name;

    public Category(int id, String name){
        setId(id);
        setName(name);
    }

    //---------------------------------

    public int getId(int id){
        return id;
    }

    public String getName() {
        return name;
    }

    //--------------------------------------

    public void setId(int id) {
        if (id <= 0){
            throw new IllegalArgumentException("ID cannot be negative");
        }
        this.id = id;
    }
    public void setName(String name){
        if (name == null){
            throw new IllegalArgumentException("NAME cannot be null");
        }
        this.name = name;
    }


}
