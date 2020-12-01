package com.octodev.twish.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "twish.app")
public class AppProperties {

  private String jwtSecret;
  private Long jwtExpirationMs;

  public String getJwtSecret() {
    return jwtSecret;
  }

  public void setJwtSecret(String jwtSecret) {
    this.jwtSecret = jwtSecret;
  }

  public Long getJwtExpirationMs() {
    return jwtExpirationMs;
  }

  public void setJwtExpirationMs(Long jwtExpirationMs) {
    this.jwtExpirationMs = jwtExpirationMs;
  }

}
