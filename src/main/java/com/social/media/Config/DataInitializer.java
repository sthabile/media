package com.social.media.Config;

import com.social.media.Models.Post;
import com.social.media.Models.SocialGroup;
import com.social.media.Models.SocialProfile;
import com.social.media.Models.SocialUser;
import com.social.media.Repositories.PostRepository;
import com.social.media.Repositories.SocialGroupRepository;
import com.social.media.Repositories.SocialProfileRepository;
import com.social.media.Repositories.SocialUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class DataInitializer {
    private final SocialUserRepository socialUserRepository;
    private final SocialGroupRepository socialGroupRepository;
    private final SocialProfileRepository socialProfileRepository;
    private final PostRepository postRepository;

    // This will be executed at startup.
    // Should be able to see these values in the configured db

    @Bean
    public CommandLineRunner initialiseData(){
        return args -> {
            SocialUser socialUser1 = new SocialUser();
            SocialUser socialUser2 = new SocialUser();
            SocialUser socialUser3 = new SocialUser();

            socialUserRepository.save(socialUser1);
            socialUserRepository.save(socialUser2);
            socialUserRepository.save(socialUser3);

            SocialGroup group1 = new SocialGroup();
            SocialGroup group2 = new SocialGroup();

            group1.getSocialUsers().add(socialUser1);
            group1.getSocialUsers().add(socialUser2);

            group2.getSocialUsers().add(socialUser2);
            group2.getSocialUsers().add(socialUser3);

            socialGroupRepository.save(group1);
            socialGroupRepository.save(group2);

            socialUser1.getGroups().add(group1);
            socialUser2.getGroups().add(group1);

            socialUser2.getGroups().add(group2);
            socialUser3.getGroups().add(group2);

            socialUserRepository.save(socialUser3);
            socialUserRepository.save(socialUser2);
            socialUserRepository.save(socialUser1);


            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            post1.setSocialUser(socialUser1);
            post2.setSocialUser(socialUser2);
            post3.setSocialUser(socialUser3);

            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            SocialProfile socialProfile1 = new SocialProfile();
            SocialProfile socialProfile2 = new SocialProfile();
            SocialProfile socialProfile3 = new SocialProfile();

            socialProfile1.setSocialUser(socialUser1);
            socialProfile2.setSocialUser(socialUser2);
            socialProfile3.setSocialUser(socialUser3);

            socialProfileRepository.save(socialProfile1);
            socialProfileRepository.save(socialProfile2);
            socialProfileRepository.save(socialProfile3);

        };
    }
}
