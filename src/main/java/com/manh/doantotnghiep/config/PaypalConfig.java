package com.manh.doantotnghiep.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {

        @Value("ATIE_g3-FXFpXi8oGUVn3FAScbRSGrvC05hGDmkfcGgRl9c3DGAhGF-B4wJnuuoHqFsBmSa4k79ipXMU")
    private String clientId;
        @Value("EDsqlJRTTvWonANCWOc2wtPvdQo-OAIEqTJbDq3HR1z9vu41PQj0W7dahoEhmd_fkvovE2JoSOnhVJx9")
    private String clientSecret;
        @Value("${paypal.mode}")
    private String mode;
    
        @Bean
        public Map<String, String> paypalSdkConfig(){
                Map<String, String> sdkConfig = new HashMap<>();
                sdkConfig.put("mode", mode);
                return sdkConfig;
        }
        
        @Bean
        public OAuthTokenCredential authTokenCredential(){
                return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
        }
        
        @Bean
        public APIContext apiContext() throws PayPalRESTException{
                APIContext apiContext = new APIContext(authTokenCredential().getAccessToken());
                apiContext.setConfigurationMap(paypalSdkConfig());
                return apiContext;
        }
}
