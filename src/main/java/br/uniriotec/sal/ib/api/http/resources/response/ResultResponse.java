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
public class ResultResponse {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "query_id")
    private Long queryId;

    @ApiModelProperty(value = "title")
    private String title;

    @ApiModelProperty(value = "snippet")
    private String snippet;

    @ApiModelProperty(value = "link")
    private String link;
}
