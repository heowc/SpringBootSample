package com.example.simple.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class TimeData implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idx;

    @NonNull
//    @Temporal(TemporalType.DATE)
    private LocalDateTime date;
    
    public TimeData() {}
}
