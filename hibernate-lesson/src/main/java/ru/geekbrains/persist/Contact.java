package ru.geekbrains.persist;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@NamedQuery(name = "withUserName",
        query = "select c.id, c.type, c.contact, u.name " +
                "from Contact c " +
                "inner join c.user u " +
                "where c.id = :id")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column
    private String contact;

    @ManyToOne
    private User user;

    public Contact(Long id, String type, String contact, User user) {
        this.id = id;
        this.type = type;
        this.contact = contact;
        this.user = user;
    }

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", contact='" + contact + '\'' +
                ", user.id=" + user.getId() +
                '}';
    }
}
