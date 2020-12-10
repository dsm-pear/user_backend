package com.dsmpear.main.security;

import com.dsmpear.main.security.auth.AuthenticationFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"test"})
public class AuthenticationFacadeTest {
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Test
    @DisplayName("getUserEmail Test")
    @WithMockUser(value = "aaaa@dsm.hs.kr", password = "1111")
    public void testGetUserEmail() {
        assertEquals("aaaa@dsm.hs.kr", authenticationFacade.getUserEmail());
    }
}
