package net.hwcloud.dto;

public class MyModel {

    private String name;

    public MyModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
