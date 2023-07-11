/*
 * Conjur
 * This is an API definition for CyberArk Conjur Open Source. You can find out more at [Conjur.org](https://www.conjur.org/).
 *
 * The version of the OpenAPI document: 5.3.1
 * Contact: conj_maintainers@cyberark.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.cyberark.conjur.sdk.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Information about the client making a request
 */
@ApiModel(description = "Information about the client making a request")

public class WhoAmI {
  public static final String SERIALIZED_NAME_ACCOUNT = "account";
  @SerializedName(SERIALIZED_NAME_ACCOUNT)
  private String account;

  public static final String SERIALIZED_NAME_CLIENT_IP = "client_ip";
  @SerializedName(SERIALIZED_NAME_CLIENT_IP)
  private String clientIp;

  public static final String SERIALIZED_NAME_TOKEN_ISSUED_AT = "token_issued_at";
  @SerializedName(SERIALIZED_NAME_TOKEN_ISSUED_AT)
  private String tokenIssuedAt;

  public static final String SERIALIZED_NAME_USER_AGENT = "user_agent";
  @SerializedName(SERIALIZED_NAME_USER_AGENT)
  private String userAgent;

  public static final String SERIALIZED_NAME_USERNAME = "username";
  @SerializedName(SERIALIZED_NAME_USERNAME)
  private String username;


  public WhoAmI account(String account) {
    
    this.account = account;
    return this;
  }

   /**
   * The account attribute of the client provided access token.
   * @return account
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "dev", value = "The account attribute of the client provided access token.")

  public String getAccount() {
    return account;
  }


  public void setAccount(String account) {
    this.account = account;
  }


  public WhoAmI clientIp(String clientIp) {
    
    this.clientIp = clientIp;
    return this;
  }

   /**
   * The request client IP address as determined by Conjur. This same IP address appears in application logs and audit logs.
   * @return clientIp
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "127.0.0.1", value = "The request client IP address as determined by Conjur. This same IP address appears in application logs and audit logs.")

  public String getClientIp() {
    return clientIp;
  }


  public void setClientIp(String clientIp) {
    this.clientIp = clientIp;
  }


  public WhoAmI tokenIssuedAt(String tokenIssuedAt) {
    
    this.tokenIssuedAt = tokenIssuedAt;
    return this;
  }

   /**
   * The issued timestamp, that is, when the provided access token was created (iat field in the JWT)
   * @return tokenIssuedAt
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "2017-08-04T22:27:20+00:00", value = "The issued timestamp, that is, when the provided access token was created (iat field in the JWT)")

  public String getTokenIssuedAt() {
    return tokenIssuedAt;
  }


  public void setTokenIssuedAt(String tokenIssuedAt) {
    this.tokenIssuedAt = tokenIssuedAt;
  }


  public WhoAmI userAgent(String userAgent) {
    
    this.userAgent = userAgent;
    return this;
  }

   /**
   * The incoming request HTTP user agent header.
   * @return userAgent
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36", value = "The incoming request HTTP user agent header.")

  public String getUserAgent() {
    return userAgent;
  }


  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }


  public WhoAmI username(String username) {
    
    this.username = username;
    return this;
  }

   /**
   * The username attribute of the provided access token.
   * @return username
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "admin", value = "The username attribute of the provided access token.")

  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WhoAmI whoAmI = (WhoAmI) o;
    return Objects.equals(this.account, whoAmI.account) &&
        Objects.equals(this.clientIp, whoAmI.clientIp) &&
        Objects.equals(this.tokenIssuedAt, whoAmI.tokenIssuedAt) &&
        Objects.equals(this.userAgent, whoAmI.userAgent) &&
        Objects.equals(this.username, whoAmI.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(account, clientIp, tokenIssuedAt, userAgent, username);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WhoAmI {\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    clientIp: ").append(toIndentedString(clientIp)).append("\n");
    sb.append("    tokenIssuedAt: ").append(toIndentedString(tokenIssuedAt)).append("\n");
    sb.append("    userAgent: ").append(toIndentedString(userAgent)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

