package org.kang.plus.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User:
 * Description:
 * Date: 2024-04-19
 * Time: 12:32
 */
@Getter
@Setter
@ToString
public class FilmDTO implements java.io.Serializable {


    private Integer id;

    private String name;

    private Integer actorId;

    private String remark;

}
