package org.kang.plus.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.kang.plus.entity.FilmActor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author healthy183
 * @since 2024-04-17
 */
@Getter
@Setter
@ToString
public class ActorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer idSum;

    private Integer idMax;

    private Integer  idMin;

    private Integer  idAvg;

    private Integer idLen;

    private String name;

    private Integer nameLength;

    private Integer nameCount;

    private LocalDateTime updateTime;

    private LocalDateTime updateTimeString;

    private String sex;

    private String concatStr;

    private String concatStr2;

    private String nameLcase;

    private FilmActor filmActor;

    private List<FilmActor> filmActorList;
}
