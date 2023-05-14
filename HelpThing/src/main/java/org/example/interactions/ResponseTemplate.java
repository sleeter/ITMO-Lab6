package org.example.interactions;

import java.io.Serializable;

/**
 * Class for get response value.
 */
public class ResponseTemplate implements Serializable {
    private final ResponseResult responseResult;
    private final String responseBody;

    public ResponseTemplate(ResponseResult responseResult, String responseBody) {
        this.responseResult = responseResult;
        this.responseBody = responseBody;
    }

    /**
     * @return Response result
     */

    public ResponseResult getResponseResult() {
        return responseResult;
    }

    /**
     *
     * @return Response body
     */
    public String getResponseBody() {
        return responseBody;
    }

    @Override
    public String toString() {
        return "Response[" + responseResult + ", " + responseBody + "]";
    }
}