package org.kang.plus.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * User:
 * Description:
 * Date: 2024-05-14
 * Time: 16:53
 */
@Getter
@Setter
@ToString
public class Contact  implements Serializable {

    private static final long serialVersionUID = 34823429529582L;

    private Integer  contactID;

    private String  contactName;
}
