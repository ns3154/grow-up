package com.example.demo.reference;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/06/02 15:45
 **/
public class ReferenceModel {

    public ReferenceModel() {
    }

    public ReferenceModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReferenceModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Note: finalize() is deprecated in Java 9+ and removed in Java 21
     * This is kept for demonstration purposes only.
     */
    @Deprecated(forRemoval = true)
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("gc............");
    }
}
