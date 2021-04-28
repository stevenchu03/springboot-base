package org.personal.springbootbase.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    @Id
    private UUID id;

    protected ZonedDateTime createdAt;
    protected ZonedDateTime updatedAt;
    protected ZonedDateTime deletedAt;

    @Transient
    private transient BaseEntity previousState;

    protected boolean isManuallySet(){
        if(this.previousState != null && this.previousState.updatedAt!=null){
            return !this.previousState.updatedAt.equals(this.updatedAt);
        }
        return true;
    }

    @PrePersist
    protected void prePersist(){
        if(createdAt == null){
            createdAt = ZonedDateTime.now();
        }
        if(updatedAt==null){
            updatedAt = ZonedDateTime.now();
        }
        setId(UUID.randomUUID());
    }

    @PreUpdate
    protected void preUpdate(){
        if(updatedAt == null || !isManuallySet()){
            updatedAt = ZonedDateTime.now();
        }
    }

    @PostLoad
    protected void saveState(){
        this.previousState = new BaseEntity();
        this.previousState.createdAt = this.createdAt;
        this.previousState.updatedAt = this.updatedAt;
    }
}
