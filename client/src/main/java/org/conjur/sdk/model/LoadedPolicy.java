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
import java.math.BigDecimal;

/**
 * LoadedPolicy
 */

public class LoadedPolicy {
  public static final String SERIALIZED_NAME_CREATED_ROLES = "created_roles";
  @SerializedName(SERIALIZED_NAME_CREATED_ROLES)
  private Object createdRoles;

  public static final String SERIALIZED_NAME_VERSION = "version";
  @SerializedName(SERIALIZED_NAME_VERSION)
  private BigDecimal version;


  public LoadedPolicy createdRoles(Object createdRoles) {
    
    this.createdRoles = createdRoles;
    return this;
  }

   /**
   * Get createdRoles
   * @return createdRoles
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Object getCreatedRoles() {
    return createdRoles;
  }


  public void setCreatedRoles(Object createdRoles) {
    this.createdRoles = createdRoles;
  }


  public LoadedPolicy version(BigDecimal version) {
    
    this.version = version;
    return this;
  }

   /**
   * Get version
   * @return version
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public BigDecimal getVersion() {
    return version;
  }


  public void setVersion(BigDecimal version) {
    this.version = version;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoadedPolicy loadedPolicy = (LoadedPolicy) o;
    return Objects.equals(this.createdRoles, loadedPolicy.createdRoles) &&
        Objects.equals(this.version, loadedPolicy.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdRoles, version);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoadedPolicy {\n");
    sb.append("    createdRoles: ").append(toIndentedString(createdRoles)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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
