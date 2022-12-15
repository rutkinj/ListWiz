package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Task type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Tasks", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byOwner", fields = {"taskOwnerID","name"})
public final class Task implements Model {
  public static final QueryField ID = field("Task", "id");
  public static final QueryField NAME = field("Task", "name");
  public static final QueryField TASK_OWNER = field("Task", "taskOwnerID");
  public static final QueryField DESCRIPTION = field("Task", "description");
  public static final QueryField STATUS = field("Task", "status");
  public static final QueryField CREATED_DATE_TIME = field("Task", "createdDateTime");
  public static final QueryField S3_IMAGE_KEY = field("Task", "s3ImageKey");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="TaskOwner") @BelongsTo(targetName = "taskOwnerID", targetNames = {"taskOwnerID"}, type = TaskOwner.class) TaskOwner taskOwner;
  private final @ModelField(targetType="String") String description;
  private final @ModelField(targetType="StatusEnum") StatusEnum status;
  private final @ModelField(targetType="AWSDateTime") Temporal.DateTime createdDateTime;
  private final @ModelField(targetType="String") String s3ImageKey;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public TaskOwner getTaskOwner() {
      return taskOwner;
  }
  
  public String getDescription() {
      return description;
  }
  
  public StatusEnum getStatus() {
      return status;
  }
  
  public Temporal.DateTime getCreatedDateTime() {
      return createdDateTime;
  }
  
  public String getS3ImageKey() {
      return s3ImageKey;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Task(String id, String name, TaskOwner taskOwner, String description, StatusEnum status, Temporal.DateTime createdDateTime, String s3ImageKey) {
    this.id = id;
    this.name = name;
    this.taskOwner = taskOwner;
    this.description = description;
    this.status = status;
    this.createdDateTime = createdDateTime;
    this.s3ImageKey = s3ImageKey;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Task task = (Task) obj;
      return ObjectsCompat.equals(getId(), task.getId()) &&
              ObjectsCompat.equals(getName(), task.getName()) &&
              ObjectsCompat.equals(getTaskOwner(), task.getTaskOwner()) &&
              ObjectsCompat.equals(getDescription(), task.getDescription()) &&
              ObjectsCompat.equals(getStatus(), task.getStatus()) &&
              ObjectsCompat.equals(getCreatedDateTime(), task.getCreatedDateTime()) &&
              ObjectsCompat.equals(getS3ImageKey(), task.getS3ImageKey()) &&
              ObjectsCompat.equals(getCreatedAt(), task.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), task.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getTaskOwner())
      .append(getDescription())
      .append(getStatus())
      .append(getCreatedDateTime())
      .append(getS3ImageKey())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Task {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("taskOwner=" + String.valueOf(getTaskOwner()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("status=" + String.valueOf(getStatus()) + ", ")
      .append("createdDateTime=" + String.valueOf(getCreatedDateTime()) + ", ")
      .append("s3ImageKey=" + String.valueOf(getS3ImageKey()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Task justId(String id) {
    return new Task(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      taskOwner,
      description,
      status,
      createdDateTime,
      s3ImageKey);
  }
  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Task build();
    BuildStep id(String id);
    BuildStep taskOwner(TaskOwner taskOwner);
    BuildStep description(String description);
    BuildStep status(StatusEnum status);
    BuildStep createdDateTime(Temporal.DateTime createdDateTime);
    BuildStep s3ImageKey(String s3ImageKey);
  }
  

  public static class Builder implements NameStep, BuildStep {
    private String id;
    private String name;
    private TaskOwner taskOwner;
    private String description;
    private StatusEnum status;
    private Temporal.DateTime createdDateTime;
    private String s3ImageKey;
    @Override
     public Task build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Task(
          id,
          name,
          taskOwner,
          description,
          status,
          createdDateTime,
          s3ImageKey);
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep taskOwner(TaskOwner taskOwner) {
        this.taskOwner = taskOwner;
        return this;
    }
    
    @Override
     public BuildStep description(String description) {
        this.description = description;
        return this;
    }
    
    @Override
     public BuildStep status(StatusEnum status) {
        this.status = status;
        return this;
    }
    
    @Override
     public BuildStep createdDateTime(Temporal.DateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }
    
    @Override
     public BuildStep s3ImageKey(String s3ImageKey) {
        this.s3ImageKey = s3ImageKey;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, TaskOwner taskOwner, String description, StatusEnum status, Temporal.DateTime createdDateTime, String s3ImageKey) {
      super.id(id);
      super.name(name)
        .taskOwner(taskOwner)
        .description(description)
        .status(status)
        .createdDateTime(createdDateTime)
        .s3ImageKey(s3ImageKey);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder taskOwner(TaskOwner taskOwner) {
      return (CopyOfBuilder) super.taskOwner(taskOwner);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder status(StatusEnum status) {
      return (CopyOfBuilder) super.status(status);
    }
    
    @Override
     public CopyOfBuilder createdDateTime(Temporal.DateTime createdDateTime) {
      return (CopyOfBuilder) super.createdDateTime(createdDateTime);
    }
    
    @Override
     public CopyOfBuilder s3ImageKey(String s3ImageKey) {
      return (CopyOfBuilder) super.s3ImageKey(s3ImageKey);
    }
  }
  
}
