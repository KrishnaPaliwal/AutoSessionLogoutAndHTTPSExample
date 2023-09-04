package com.auto.logout.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    
    // Account expiration date
    private LocalDate accountExpirationDate;

    // Account locking status
    private boolean accountNonLocked = true;

    // Credentials (password) expiration date
    private LocalDate credentialsExpirationDate;

    // User activation status
    private boolean enabled = true;
    
    // Define other user-related fields and relationships as needed
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implement this method to return user's authorities/roles
        // You can use Spring Security's SimpleGrantedAuthority for roles
        // For example:
        // return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return Collections.emptyList(); // No authorities defined in this example
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Check if the current date is before the account expiration date
        return accountExpirationDate == null || LocalDate.now().isBefore(accountExpirationDate);
    }


    @Override
    public boolean isCredentialsNonExpired() {
        // Check if the current date is before the credentials expiration date
        return credentialsExpirationDate == null || LocalDate.now().isBefore(credentialsExpirationDate);
    }


    // Define getters and setters for other fields

    public LocalDate getAccountExpirationDate() {
        return accountExpirationDate;
    }

    public void setAccountExpirationDate(LocalDate accountExpirationDate) {
        this.accountExpirationDate = accountExpirationDate;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public LocalDate getCredentialsExpirationDate() {
        return credentialsExpirationDate;
    }

    public void setCredentialsExpirationDate(LocalDate credentialsExpirationDate) {
        this.credentialsExpirationDate = credentialsExpirationDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    // Constructors
}
