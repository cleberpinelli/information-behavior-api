package br.uniriotec.sal.ib.api.http.resources.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryResponse {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "Content")
    private String content;

}
