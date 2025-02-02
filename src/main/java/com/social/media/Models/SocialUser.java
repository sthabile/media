package com.social.media.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne(mappedBy = "socialUser", cascade = {CascadeType.REMOVE,CascadeType.PERSIST, CascadeType.MERGE} )
    @OneToOne(mappedBy = "socialUser", cascade = CascadeType.ALL)
    //    @JoinColumn(name = "social_profile_id")
    private SocialProfile socialProfile;

    @OneToMany(mappedBy = "socialUser", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany (fetch = FetchType.EAGER) //Lazy by default
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<SocialGroup> groups = new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }

    /**
     * Custom setter to ensure that both entities are aware of any changes.
     * If there is an update on the profile, the user must be updated with the
     * new profile as well.
     * @param socialProfile
     */
    public void setSocialProfile(SocialProfile socialProfile) {
        socialProfile.setSocialUser(this);
        this.socialProfile = socialProfile;
    }
}