package com.patient.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.patient.model.AuthResponse;

public class AuthResponseTest {
	

    @Test
       public void test01() {
         AuthResponse authObj = Mockito.mock(AuthResponse.class);//creating mock object
            
         AuthResponse real = new AuthResponse();
         real.setUsername("sagartest@gmail.com");
            when(authObj.getUsername()).thenReturn(real.getUsername());
            assertEquals(real.getUsername(),authObj.getUsername());
     }
     @Test
       public void test02() {
         AuthResponse authObj = Mockito.mock(AuthResponse.class);//creating mock object
            
         AuthResponse real = new AuthResponse();
         real.setAccessToken("jwttoken");
            when(authObj.getAccessToken()).thenReturn(real.getAccessToken());
            assertEquals(real.getAccessToken(),authObj.getAccessToken());
         
     }

}
