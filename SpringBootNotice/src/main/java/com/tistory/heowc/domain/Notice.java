package com.tistory.heowc.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@SuppressWarnings("serial")
@AllArgsConstructor
@GenericGenerator(
        name = "NoticeSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "NOTICE_SEQ"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
        }
)
public class Notice implements Serializable {

    @Id @GeneratedValue(generator = "NoticeSequenceGenerator")
    private Long idx;
    private String title;

    @Column(columnDefinition = "text")
    private String content;

    protected Notice() { }
}
