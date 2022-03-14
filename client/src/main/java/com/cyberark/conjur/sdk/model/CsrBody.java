/*
 * Conjur
 * This is an API definition for CyberArk Conjur Open Source. You can find out more at [Conjur.org](https://www.conjur.org/).
 *
 * The version of the OpenAPI document: 5.3.0
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
 * CsrBody
 */

public class CsrBody {
  public static final String SERIALIZED_NAME_CSR = "csr";
  @SerializedName(SERIALIZED_NAME_CSR)
  private String csr;

  public static final String SERIALIZED_NAME_TTL = "ttl";
  @SerializedName(SERIALIZED_NAME_TTL)
  private String ttl;


  public CsrBody csr(String csr) {
    
    this.csr = csr;
    return this;
  }

   /**
   * Get csr
   * @return csr
  **/
  @ApiModelProperty(required = true, value = "")

  public String getCsr() {
    return csr;
  }


  public void setCsr(String csr) {
    this.csr = csr;
  }


  public CsrBody ttl(String ttl) {
    
    this.ttl = ttl;
    return this;
  }

   /**
   * Get ttl
   * @return ttl
  **/
  @ApiModelProperty(required = true, value = "")

  public String getTtl() {
    return ttl;
  }


  public void setTtl(String ttl) {
    this.ttl = ttl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CsrBody csrBody = (CsrBody) o;
    return Objects.equals(this.csr, csrBody.csr) &&
        Objects.equals(this.ttl, csrBody.ttl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(csr, ttl);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CsrBody {\n");
    sb.append("    csr: ").append(toIndentedString(csr)).append("\n");
    sb.append("    ttl: ").append(toIndentedString(ttl)).append("\n");
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

