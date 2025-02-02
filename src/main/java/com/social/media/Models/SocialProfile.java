package com.social.media.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*
        Both entities know of the relationship but SocialUser is the owner
        Relationship managed by user table. Don't create join column here
     */
    @OneToOne(mappedBy = "socialProfile")
    @JsonIgnore //Avoids circular referencing.
    private SocialUser socialUser;

    /**
     * Ensures consistency between social user and social profile
     * @param socialUser
     */
    public void setSocialUser(SocialUser socialUser) {
        this.socialUser = socialUser;
        if(socialUser.getSocialProfile()!=this && socialUser.getSocialProfile()!=null){
            socialUser.setSocialProfile(this);
        }
    }
}