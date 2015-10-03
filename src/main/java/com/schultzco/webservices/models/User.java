package com.schultzco.webservices.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, updatable = false)
    @Type(type = "uuid-char")
    private UUID uuid;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String emailAddress;

    @OneToMany(mappedBy = "user")
    private Set<SocialUser> socialUsers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<SocialUser> getSocialUsers() {
        return socialUsers;
    }

    public void setSocialUsers(Set<SocialUser> socialUsers) {
        this.socialUsers = socialUsers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        User rhs = (User) obj;
        return new EqualsBuilder()
                .append(this.uuid, rhs.uuid)
                .append(this.firstName, rhs.firstName)
                .append(this.lastName, rhs.lastName)
                .append(this.emailAddress, rhs.emailAddress)
                .append(this.socialUsers, rhs.socialUsers)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uuid)
                .append(firstName)
                .append(lastName)
                .append(emailAddress)
                .append(socialUsers)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .append("uuid", uuid)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("emailAddress", emailAddress)
                .append("socialUsers", socialUsers)
                .toString();
    }

}
