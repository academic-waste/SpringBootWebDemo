package com.boot.rest.SpringBootWebDemo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Employee {

    @Id
    private long employeeId;
    private String name;

    @Field("salary")
    private double dash;
    private String region;

    public Employee() {
    }

    public Employee(long employeeId, String name, double dash, String region) {
        this.employeeId = employeeId;
        this.name = name;
        this.dash = dash;
        this.region = region;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDash() {
        return dash;
    }

    public void setDash(double dash) {
        this.dash = dash;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", dash=" + dash +
                ", region='" + region + '\'' +
                '}';
    }
}
