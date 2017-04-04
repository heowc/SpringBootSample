package com.tistory.heowc.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PROGRAMMING")
@Data
@RequiredArgsConstructor
@GenericGenerator(
        name = "ProgramSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "PROGRAMING_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
public class Programming implements Serializable {

    @Id @GeneratedValue(generator = "ProgramSequenceGenerator")
    private Long idx;

    @NonNull
    private String name;

    @NonNull
    private String url;

    private Programming() {}
}
