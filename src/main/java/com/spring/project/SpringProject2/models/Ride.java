package com.spring.project.SpringProject2.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ride")
public class Ride {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @NotEmpty(message = "Starting point should not be empty")
    @Column(name = "destination_from")
    private String destination_from;

    @NotEmpty(message = "Destination should not be empty")
    @Column(name = "destination_to")
    private String destination_to;

    @NotEmpty(message = "Pick a date")
    @Column(name = "date")
//    @Temporal(TemporalType.DATE)
    private String date;

    @NotEmpty(message = "Pick time")
    @Column(name = "time")
    private String time;

    @Column(name = "price")
    @NotNull(message = "Enter a fee fro the driver")
    @Min(value = 0, message = "Price should be greater than 0")
    private int price;

    @Column(name = "available_seats")
    @NotNull(message = "Enter available seats in your car")
    private int availableSeats;

    @ManyToMany(mappedBy = "bookedRides")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Person> passengers;

    public Ride() {

    }

    public Ride(String destination_from, String destination_to, String date, String time, int price) {
        this.destination_from = destination_from;
        this.destination_to = destination_to;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getDestination_from() {
        return destination_from;
    }

    public void setDestination_from(String destination_from) {
        this.destination_from = destination_from;
    }

    public String getDestination_to() {
        return destination_to;
    }

    public void setDestination_to(String destination_to) {
        this.destination_to = destination_to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void resetAvailableSeats(int booked) {
        this.availableSeats -= booked;
    }

    public List<Person> getPassengers() {
        return passengers;
    }

    public void setPassengers(Person person) {
        passengers.add(person);
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", owner=" + owner +
                ", destination_from='" + destination_from + '\'' +
                ", destination_to='" + destination_to + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                '}';
    }
}
