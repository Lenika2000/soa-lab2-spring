package ru.itmo.soalab2.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table
@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlElement
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @XmlElement
    private String name; //Поле не может быть null, Строка не может быть пустой
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @XmlElement
    private Coordinates coordinates; //Поле не может быть null
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @XmlElement
    private float area; //Значение поля должно быть больше 0
    @XmlElement
    private int population; //Значение поля должно быть больше 0
    @XmlElement
    private Integer metersAboveSeaLevel;
    @XmlElement
    private Double timezone; //Значение поля должно быть больше -13, Максимальное значение поля: 15
    @Enumerated(EnumType.STRING)
    @XmlElement
    private Government government; //Поле не может быть null
    @Enumerated(EnumType.STRING)
    @XmlElement
    private StandardOfLiving standardOfLiving; //Поле может быть null
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @XmlElement
    private Human governor; //Поле может быть null

    public City(Long id, String name, Coordinates coordinates, LocalDateTime creationDate, float area, int population, Integer metersAboveSeaLevel, Double timezone, Government government, StandardOfLiving standardOfLiving, Human governor) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.timezone = timezone;
        this.government = government;
        this.standardOfLiving = standardOfLiving;
        this.governor = governor;
    }

    public City() {
    }

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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(int metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public Double getTimezone() {
        return timezone;
    }

    public void setTimezone(Double timezone) {
        this.timezone = timezone;
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }

    public void setStandardOfLiving(StandardOfLiving standardOfLiving) {
        this.standardOfLiving = standardOfLiving;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }

}
