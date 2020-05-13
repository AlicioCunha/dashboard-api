package com.moneyapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("moneyapiproperties")
public class ApiProperty {

    private final Security security = new Security();
    private final Environment environment = new Environment();

    public Security getSecurity() {
        return security;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public static class Security {
        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }
    }

    public static class Environment {

        private String allowedOrigin = "http://localhost:8000";

        public String getAllowedOrigin() {
            return allowedOrigin;
        }
    }
}
