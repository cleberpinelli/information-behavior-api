package br.uniriotec.sal.ib.api.http.resources.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryRequest {
    @ApiModelProperty(value = "Content")
    private String content;

}
