package com.github.harry.study.lombok.data;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description:
 * @Version: 1.0.0
 */
public class NoLombok {

    private int id;
    private String name;
    private int age;
    private int sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        NoLombok noLombok = (NoLombok) object;

        if (id != noLombok.id) return false;
        if (age != noLombok.age) return false;
        if (sex != noLombok.sex) return false;
        return name != null ? name.equals(noLombok.name) : noLombok.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + sex;
        return result;
    }

    @Override
    public String toString() {
        return "NoLombok{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    /**
     * 只有当被比较的对象是当前对象的子类或同类时才能通过
     * @param other
     * @return
     */
    public boolean canEqual(Object other) {
        return (other instanceof NoLombok);
    }

}
