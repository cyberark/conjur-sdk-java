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
 * CreateHostForm
 */

public class CreateHostForm {
  public static final String SERIALIZED_NAME_ANNOTATIONS = "annotations";
  @SerializedName(SERIALIZED_NAME_ANNOTATIONS)
  private Object annotations;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private String id;


  public CreateHostForm annotations(Object annotations) {
    
    this.annotations = annotations;
    return this;
  }

   /**
   * Annotations to apply to the new host
   * @return annotations
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "{\"description\":\"new db host\",\"puppet\":\"true\"}", value = "Annotations to apply to the new host")

  public Object getAnnotations() {
    return annotations;
  }


  public void setAnnotations(Object annotations) {
    this.annotations = annotations;
  }


  public CreateHostForm id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * Identifier of the host to be created. It will be created within the account of the host factory.
   * @return id
  **/
  @ApiModelProperty(example = "my-new-host", required = true, value = "Identifier of the host to be created. It will be created within the account of the host factory.")

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateHostForm createHostForm = (CreateHostForm) o;
    return Objects.equals(this.annotations, createHostForm.annotations) &&
        Objects.equals(this.id, createHostForm.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(annotations, id);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateHostForm {\n");
    sb.append("    annotations: ").append(toIndentedString(annotations)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

