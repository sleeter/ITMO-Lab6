package org.example.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The Organization class represents an organization.
 */
public class Organization implements Comparable<Organization>, Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float annualTurnover; //Значение поля должно быть больше 0
    private String fullName; //Значение этого поля должно быть уникальным, Длина строки не должна быть больше 1936, Поле может быть null
    private Integer employeesCount; //Поле не может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address postalAddress; //Поле может быть null

    public Organization(){

    }

    public Organization(Integer id, String name, Coordinates coordinates, LocalDate creationDate, float annualTurnover, String fullName, Integer employeesCount, OrganizationType type, Address postalAddress) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.annualTurnover = annualTurnover;
        this.fullName = fullName;
        this.employeesCount = employeesCount;
        this.type = type;
        this.postalAddress = postalAddress;
    }
    /**
     * Returns the id of the object.
     *
     * @return The id of the organization.
     */
    public Integer getId() {
        return id;
    }
    /**
     * It generates a unique positive id for the organization.
     *
     * @param id sets the id value given in.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Returns the name of the organization.
     *
     * @return The name of the organization.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the coordinates of the point.
     *
     * @return The coordinates of the point.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }
    /**
     * Get the creation date of the object.
     *
     * @return The creation date of the organization.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }
    /**
     * Returns the annual turnover of the organization.
     *
     * @return The annual turnover of the organization.
     */
    public float getAnnualTurnover() {
        return annualTurnover;
    }
    /**
     * Returns the full name of organization.
     *
     * @return The full name of the organization.
     */
    public String getFullName() {
        return fullName;
    }
    /**
     * Get the number of employees in the company.
     *
     * @return The number of employees in the organization.
     */
    public Integer getEmployeesCount() {
        return employeesCount;
    }
    /**
     * Returns the type of the organization.
     *
     * @return The type of the organization.
     */
    public OrganizationType getType() {
        return type;
    }
    /**
     * Returns the postal address of the organization.
     *
     * @return The postal address of the organization.
     */
    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setAnnualTurnover(float annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public void setFullName() {
        this.fullName = this.name + "_Unique";
    }

    public void setEmployeesCount(Integer employeesCount) {
        this.employeesCount = employeesCount;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    @Override
    public int compareTo(Organization o) {
        return Float.compare(this.annualTurnover, o.annualTurnover);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", annualTurnover=" + annualTurnover +
                ", fullName='" + fullName + '\'' +
                ", employeesCount=" + employeesCount +
                ", type=" + type +
                ", postalAddress=" + postalAddress +
                '}';
    }
}
