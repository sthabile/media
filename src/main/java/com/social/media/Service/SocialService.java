package com.social.media.Service;

import com.social.media.Models.SocialUser;
import com.social.media.Repositories.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialService {
    @Autowired
    SocialUserRepository socialUserRepository;

    public List<SocialUser> getAllUsers(){
        return socialUserRepository.findAll();
    }

    public SocialUser saveUser(SocialUser socialUser){
        return socialUserRepository.save(socialUser);
    }

    public SocialUser deleteUser(Long id){
        SocialUser socialUser = socialUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Social user not found"));
        socialUserRepository.deleteById(id);
        return socialUser;
    }
}
