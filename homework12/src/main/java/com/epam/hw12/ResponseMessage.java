package com.epam.hw12;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage implements Serializable {
    private UUID corrId;
    private int result;
}
