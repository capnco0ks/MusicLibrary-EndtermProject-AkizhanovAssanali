package model;

import model.interfaces.Validatable;

public abstract class BaseEntity implements Validatable {
    private int id;
    private String name;

    public BaseEntity(int id, String name) {
        setId(id);
        setName(name);
    }

    public abstract void printInfo();
    public abstract String getEntityType();

    public void validate() {
        if (name == null || name.isBlank() || id <= 0) {
            throw new IllegalArgumentException("Invalid BaseEntity data");
        }
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }

    @Override
    public boolean isValid() {
        return id > 0 && name != null && !name.isBlank();
    }
}
