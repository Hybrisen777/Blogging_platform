package com.uep.wap.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authority implements Serializable {

    @Id
    @Column(length = 20)
    private String name;

    @Override
    public String toString() {
        return "Authority{" +
            "name='" + name + "'" +
        "}";
    }

}
