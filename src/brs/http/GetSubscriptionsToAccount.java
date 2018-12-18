package brs.http;

import static brs.http.common.Parameters.ACCOUNT_PARAMETER;

import brs.Account;
import brs.CaretException;
import brs.Subscription;
import brs.db.CaretIterator;
import brs.services.ParameterService;
import brs.services.SubscriptionService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

public final class GetSubscriptionsToAccount extends APIServlet.APIRequestHandler {

  private final ParameterService parameterService;
  private final SubscriptionService subscriptionService;

  GetSubscriptionsToAccount(ParameterService parameterService, SubscriptionService subscriptionService) {
    super(new APITag[] {APITag.ACCOUNTS}, ACCOUNT_PARAMETER);
    this.parameterService = parameterService;
    this.subscriptionService = subscriptionService;
  }
	
  @Override
  JSONStreamAware processRequest(HttpServletRequest req) throws CaretException {
    final Account account = parameterService.getAccount(req);
		
    JSONObject response = new JSONObject();
		
    JSONArray subscriptions = new JSONArray();

    CaretIterator<Subscription> accountSubscriptions = subscriptionService.getSubscriptionsToId(account.getId());
		
    while(accountSubscriptions.hasNext()) {
      subscriptions.add(JSONData.subscription(accountSubscriptions.next()));
    }
		
    response.put("subscriptions", subscriptions);
    return response;
  }
}
