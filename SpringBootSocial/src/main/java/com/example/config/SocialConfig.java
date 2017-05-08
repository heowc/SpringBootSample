package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.kakao.connect.KakaoConnectionFactory;
import org.springframework.social.naver.connect.NaverConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    @Autowired DataSource dataSource;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment environment) {
//        cfConfig.addConnectionFactory(facebookConnectionFactory(environment));
        cfConfig.addConnectionFactory(kakaoConnectionFactory(environment));
        cfConfig.addConnectionFactory(naverConnectionFactory(environment));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,
                                               ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator,
                                                   UsersConnectionRepository usersConnectionRepository) {
        return new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository);
    }

    private FacebookConnectionFactory facebookConnectionFactory(Environment environment) {
        return new FacebookConnectionFactory(environment.getProperty("spring.social.facebook.appId"),
                                            environment.getProperty("spring.social.facebook.appSecret"));
    }

    private KakaoConnectionFactory kakaoConnectionFactory(Environment environment) {
        return new KakaoConnectionFactory(environment.getProperty("spring.social.kakao.appId"));
    }


    private NaverConnectionFactory naverConnectionFactory(Environment environment) {
        return new NaverConnectionFactory(environment.getProperty("spring.social.naver.appId"),
                                        environment.getProperty("spring.social.naver.appSecret"));
    }
}
