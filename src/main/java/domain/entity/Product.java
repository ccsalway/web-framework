package domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import system.annotations.Column;
import system.annotations.Entity;

import java.sql.Time;
import java.util.Date;

@Entity(table = "products")
public class Product {

    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;

    @Column(name = "justTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time time;

    @Column(name = "justDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "someInt1")
    private int someInt1;

    @Column(name = "someInt2")
    private long someInt2;

    @Column(name = "amount1")
    private double amount1;

    @Column(name = "amount2")
    private double amount2;

    @Column(name = "someText")
    private String someText;

    @JsonIgnore
    private String nothing;

    //--------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getSomeInt1() {
        return someInt1;
    }

    public void setSomeInt1(int someInt1) {
        this.someInt1 = someInt1;
    }

    public long getSomeInt2() {
        return someInt2;
    }

    public void setSomeInt2(long someInt2) {
        this.someInt2 = someInt2;
    }

    public double getAmount1() {
        return amount1;
    }

    public void setAmount1(double amount1) {
        this.amount1 = amount1;
    }

    public double getAmount2() {
        return amount2;
    }

    public void setAmount2(double amount2) {
        this.amount2 = amount2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSomeText() {
        return someText;
    }

    public void setSomeText(String someText) {
        this.someText = someText;
    }
}
