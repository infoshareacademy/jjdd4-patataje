package com.hydrozagadka.Beans;


import com.google.api.client.auth.openidconnect.IdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.hydrozagadka.User;
import com.hydrozagadka.dao.UserDao;

import javax.inject.Inject;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;

public class GoogleVerifierBean {

    @Inject
    private UserDao userDao;
    private static final JacksonFactory jacksonFactory = new JacksonFactory();
    private static final String CLIENT_ID = "838946843173-a1982aq9rsq35t2hlanpjbn74ok93es0.apps.googleusercontent.com";

    public String verify(String idTokenString) throws GeneralSecurityException, IOException {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jacksonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            IdToken.Payload payload = idToken.getPayload();
            String userId = payload.getSubject();
                String email = ((GoogleIdToken.Payload) payload).getEmail();
                boolean emailVerified = Boolean.valueOf(((GoogleIdToken.Payload) payload).getEmailVerified());
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");

                User user = userDao.findById(userId);
                if (user != null) {
                   return user.getName();
                } else if (emailVerified) {
                    User u = new User(userId,name, email, false, 0, new ArrayList<>(), pictureUrl, locale);
                    userDao.save(u);
                    return u.getName();
                }
            } else {
                throw new IllegalArgumentException("Id użytkownika nieprawidłowe");
            }
        return "";
    }
}
