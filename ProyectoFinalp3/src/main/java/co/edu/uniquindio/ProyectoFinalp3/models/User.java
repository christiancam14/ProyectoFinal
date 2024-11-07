package co.edu.uniquindio.ProyectoFinalp3.models;

import jakarta.persistence.*;
import java.util.List;

import javax.management.relation.Role;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true, nullable = false)
    private String email;
    
    private String password;
    private String firstName;
    private String lastName;
    private String cedula;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    @OneToMany(mappedBy = "user")
    private List<Chat> chats;

    // Constructor vacío
    public User() {}

    // Constructor con parámetros
    public User(String username, String password, String firstName, String lastName, String cedula, String address, Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cedula = cedula;
        this.address = address;
        this.role = role;
    }

    // Getter y Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public List<Contact> getContacts() { return contacts; }
    public void setContacts(List<Contact> contacts) { this.contacts = contacts; }

    public List<Chat> getChats() { return chats; }
    public void setChats(List<Chat> chats) { this.chats = chats; }

    public String getEmail() {
        return email;
    }
}
