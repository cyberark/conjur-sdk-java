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
 * ResourcePermissions
 */

public class ResourcePermissions {
  public static final String SERIALIZED_NAME_POLICY = "policy";
  @SerializedName(SERIALIZED_NAME_POLICY)
  private String policy;

  public static final String SERIALIZED_NAME_PRIVILEGE = "privilege";
  @SerializedName(SERIALIZED_NAME_PRIVILEGE)
  private String privilege;

  public static final String SERIALIZED_NAME_ROLE = "role";
  @SerializedName(SERIALIZED_NAME_ROLE)
  private String role;


  public ResourcePermissions policy(String policy) {
    
    this.policy = policy;
    return this;
  }

   /**
   * Get policy
   * @return policy
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getPolicy() {
    return policy;
  }


  public void setPolicy(String policy) {
    this.policy = policy;
  }


  public ResourcePermissions privilege(String privilege) {
    
    this.privilege = privilege;
    return this;
  }

   /**
   * Get privilege
   * @return privilege
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getPrivilege() {
    return privilege;
  }


  public void setPrivilege(String privilege) {
    this.privilege = privilege;
  }


  public ResourcePermissions role(String role) {
    
    this.role = role;
    return this;
  }

   /**
   * Get role
   * @return role
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getRole() {
    return role;
  }


  public void setRole(String role) {
    this.role = role;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourcePermissions resourcePermissions = (ResourcePermissions) o;
    return Objects.equals(this.policy, resourcePermissions.policy) &&
        Objects.equals(this.privilege, resourcePermissions.privilege) &&
        Objects.equals(this.role, resourcePermissions.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(policy, privilege, role);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourcePermissions {\n");
    sb.append("    policy: ").append(toIndentedString(policy)).append("\n");
    sb.append("    privilege: ").append(toIndentedString(privilege)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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

