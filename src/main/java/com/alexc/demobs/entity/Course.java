package com.alexc.demobs.entity;

import com.alexc.demobs.entity.Resource.Resource;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "course_has_user",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> studentsEnrolled;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "user_has_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> instructors;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "course",
            cascade = CascadeType.ALL

    )
    private List<Resource> courseResources;

    public Course() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<User> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public List<User> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<User> instructors) {
        this.instructors = instructors;
    }

    public Course(String title, String description, List<User> studentsEnrolled, List<User> instructors) {
        this.title = title;
        this.description = description;
        this.studentsEnrolled = studentsEnrolled;
        this.instructors = instructors;
    }

    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                '}';
    }
}
