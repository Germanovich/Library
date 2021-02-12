package com.epam.library.model;

import java.util.Objects;

/**
 * The type AEntity.
 */
public abstract class AEntity {

    /**
     * The Id.
     */
    protected Integer id;

    /**
     * Instantiates a new AEntity.
     */
    protected AEntity() {
    }

    /**
     * Instantiates a new AEntity.
     *
     * @param id the id
     */
    protected AEntity(Integer id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AEntity)) {
            return false;
        }
        AEntity aEntity = (AEntity) o;
        return getId().equals(aEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
