package org.client.model;

import lombok.Data;

@Data
public class FileResp {
    private String name;

    private Long fileSize;

    private Boolean deleted;
}
