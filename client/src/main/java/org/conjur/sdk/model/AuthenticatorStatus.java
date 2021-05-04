/*
 * Conjur
 * This is an API definition for CyberArk Conjur OSS. You can find out more at [Conjur.org](https://www.conjur.org/).
 *
 * The version of the OpenAPI document: 5.1.1
 * Contact: conj_maintainers@cyberark.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.conjur.sdk.model;

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
 * AuthenticatorStatus
 */

public class AuthenticatorStatus {
  public static final String SERIALIZED_NAME_ERROR = "error";
  @SerializedName(SERIALIZED_NAME_ERROR)
  private String error;

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private String status;


  public AuthenticatorStatus error(String error) {
    
    this.error = error;
    return this;
  }

   /**
   * The error message if there was an error
   * @return error
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "#<Errors::Authentication::AuthenticatorNotFound: CONJ00001E Authenticator 'authn-oidc' is not implemented in Conjur>", value = "The error message if there was an error")

  public String getError() {
    return error;
  }


  public void setError(String error) {
    this.error = error;
  }


  public AuthenticatorStatus status(String status) {
    
    this.status = status;
    return this;
  }

   /**
   * The status of the endpoint, &#39;error&#39; if there was an error
   * @return status
  **/
  @ApiModelProperty(example = "error", required = true, value = "The status of the endpoint, 'error' if there was an error")

  public String getStatus() {
    return status;
  }


  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthenticatorStatus authenticatorStatus = (AuthenticatorStatus) o;
    return Objects.equals(this.error, authenticatorStatus.error) &&
        Objects.equals(this.status, authenticatorStatus.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(error, status);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthenticatorStatus {\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

