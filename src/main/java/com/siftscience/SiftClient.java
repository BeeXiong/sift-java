package com.siftscience;

import com.siftscience.model.ApplyDecisionFieldSet;
import com.siftscience.model.DecisionStatusFieldSet;
import com.siftscience.model.GetDecisionFieldSet;
import com.siftscience.model.LabelFieldSet;
import com.siftscience.model.ScoreFieldSet;
import com.siftscience.model.UnlabelFieldSet;
import com.siftscience.model.UserScoreFieldSet;
import com.siftscience.model.WorkflowStatusFieldSet;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Use a SiftClient to access all supported Sift Science APIs. It may be used concurrently from
 * different threads.
 *
 * Usage:
 *
 * SiftClient client = new SiftClient("your_api_key");
 * EventRequest txEventRequest = client.buildRequest(
 *      new TransactionFieldSet()
 *              .setUserId("some_user_id")
 *              .setAmount(506790000L)
 *              .setCurrencyCode("USD")
 *              .setTransactionType("$sale")
 *              ... );
 * try {
 *      EventResponse txEventResponse = txEventRequest.send();
 * } catch (SiftException e) {
 *     ... handle validation and unexpected server errors.
 * }
 *
 * txEventResponse.isOk(); // true;
 * txEventResponse.getApiErrorMessage(); // "OK";
 *
 * FieldSet requestFields = txEventResponse.getRequestBody();
 * EventResponseBody responseFields = txEventResponse.getBody();
 *
 */
public class SiftClient {
    private final String accountId;
    private final String apiKey;
    private OkHttpClient okClient = new OkHttpClient();
    private HttpUrl baseUrl = HttpUrl.parse("https://api.sift.com");

    public SiftClient(String apiKey, String accountId) {
        this.apiKey = apiKey;
        this.accountId = accountId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getAccountId() {
        return accountId;
    }

    public EventRequest buildRequest(FieldSet fields) {
        setupApiKey(fields);
        return new EventRequest(baseUrl, getAccountId(), okClient, fields);
    }

    public ApplyDecisionRequest buildRequest(ApplyDecisionFieldSet fields) {
        setupApiKey(fields);
        return new ApplyDecisionRequest(baseUrl, getAccountId(), okClient, fields);
    }

    public GetDecisionsRequest buildRequest(GetDecisionFieldSet fields) {
        setupApiKey(fields);
        return new GetDecisionsRequest(baseUrl, getAccountId(), okClient, fields);
    }

    public DecisionStatusRequest buildRequest(DecisionStatusFieldSet fields) {
        setupApiKey(fields);
        return new DecisionStatusRequest(baseUrl, getAccountId(), okClient, fields);
    }

    public LabelRequest buildRequest(LabelFieldSet fields) {
        setupApiKey(fields);
        return new LabelRequest(baseUrl, getAccountId(), okClient, fields);
    }

    public UnlabelRequest buildRequest(UnlabelFieldSet fields) {
        setupApiKey(fields);
        return new UnlabelRequest(baseUrl, getAccountId(), okClient, fields);
    }

    public ScoreRequest buildRequest(ScoreFieldSet fields) {
        setupApiKey(fields);
        return new ScoreRequest(baseUrl, getAccountId(), okClient, fields);
    }

    public UserScoreRequest buildRequest(UserScoreFieldSet fields) {
        setupApiKey(fields);
        return new UserScoreRequest(baseUrl, getAccountId(), okClient, fields);
    }

    public WorkflowStatusRequest buildRequest(WorkflowStatusFieldSet fields) {
        setupApiKey(fields);
        return new WorkflowStatusRequest(baseUrl, getAccountId(), okClient, fields);
    }

    private void setupApiKey(FieldSet fields) {
        if (fields.getApiKey() == null) {
            fields.setApiKey(getApiKey());
        }
    }

    // For testing.
    SiftClient setBaseUrl(HttpUrl baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
