package bankingApp.Users;

import bankingApp.Accounts.Account;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Account> personalAccounts;

    public User() {
        this.personalAccounts = new ArrayList<>();
    }


    public User(String fullName) {
        this();
        this.fullName = fullName;
    }

    public User(UUID id, String fullName) {
        this();
        this.id = id;
        this.fullName = fullName;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Account> getPersonalAccounts() {
        return personalAccounts;
    }

    public void addAccount(Account newAccount) {
        personalAccounts.add(newAccount);
    }
}