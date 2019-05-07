package org.labiak.java.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

abstract class TemporalEntity {
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    Date created;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    Date updated;
}
