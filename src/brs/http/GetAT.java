package brs.http;

import static brs.http.common.Parameters.AT_PARAMETER;

import brs.CaretException;
import brs.services.AccountService;
import brs.services.ParameterService;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONStreamAware;

public final class GetAT extends APIServlet.APIRequestHandler {

  private final ParameterService parameterService;
  private final AccountService accountService;

  GetAT(ParameterService parameterService, AccountService accountService) {
    super(new APITag[]{APITag.AT}, AT_PARAMETER);
    this.parameterService = parameterService;
    this.accountService = accountService;
  }

  @Override
  JSONStreamAware processRequest(HttpServletRequest req) throws CaretException {
    return JSONData.at(parameterService.getAT(req), accountService);
  }

}
