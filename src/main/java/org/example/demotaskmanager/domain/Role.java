package org.example.demotaskmanager.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(/*fetch = FetchType.EAGER,*/ mappedBy = "role")
   // @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Set<User> users = new HashSet<>();

    public Role(){ }

    public void setId(Long id) {
        this.id = id;
    }

    public Role id(Long id){
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Role users(Set<User> users) {
        this.users = users;
        return this;
    }

    public void setUsers(Set<User> users) {
        if (this.users != null){
            for (User user : this.users) {
                user.setRole(null);
            }
        }
        if (users != null){
            for (User user : users){
                user.setRole(this);
            }
        }
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public Role name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addUser(User user){
        this.users.add(user);
        user.setRole(this);
    }

    public void removeUser(User user){
        this.users.remove(user);
        user.setRole(null);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(name, role.name) &&
                Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name ,users);
    }
}
