package org.dbWandy.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResultListObject<T> {
    private int page;
    private List<T> data;
    private int total;
}
