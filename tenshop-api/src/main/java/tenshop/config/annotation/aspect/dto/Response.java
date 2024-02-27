package tenshop.config.annotation.aspect.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Response {
    private ResponseCommon common;
    private Object data;

    public Response(ResponseCommon common, Object data) {
        this.common = common;
        this.data = data;
    }

    public static Response create(ResponseCommon common, Object data) {
        return new Response(common, data);
    }

    public static Response create(ResponseCommon common) {
        return new Response(common, null);
    }

    public static Response create(Object data) {
        return new Response(null, data);
    }

    public void commonUpdate(ResponseCommon common) {
        this.common = common;
    }
}


