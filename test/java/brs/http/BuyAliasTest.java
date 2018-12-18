package brs.http;

import static brs.TransactionType.Messaging.ALIAS_BUY;
import static brs.fluxcapacitor.FeatureToggle.DIGITAL_GOODS_STORE;
import static brs.http.JSONResponses.INCORRECT_ALIAS_NOTFORSALE;
import static brs.http.common.Parameters.AMOUNT_NQT_PARAMETER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import brs.Alias;
import brs.Alias.Offer;
import brs.Attachment;
import brs.Blockchain;
import brs.Caret;
import brs.CaretException;
import brs.Constants;
import brs.common.QuickMocker;
import brs.common.QuickMocker.MockParam;
import brs.fluxcapacitor.FluxCapacitor;
import brs.services.AliasService;
import brs.services.ParameterService;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Caret.class})
public class BuyAliasTest extends AbstractTransactionTest {

  private BuyAlias t;

  private ParameterService parameterServiceMock;
  private Blockchain blockchain;
  private AliasService aliasService;
  private APITransactionManager apiTransactionManagerMock;

  @Before
  public void init() {
    parameterServiceMock = mock(ParameterService.class);
    blockchain = mock(Blockchain.class);
    aliasService = mock(AliasService.class);
    apiTransactionManagerMock = mock(APITransactionManager.class);

    t = new BuyAlias(parameterServiceMock, blockchain, aliasService, apiTransactionManagerMock);
  }

  @Test
  public void processRequest() throws CaretException {
    mockStatic(Caret.class);
    final HttpServletRequest req = QuickMocker.httpServletRequestDefaultKeys(new MockParam(AMOUNT_NQT_PARAMETER, "" + Constants.ONE_CARET));

    final Offer mockOfferOnAlias = mock(Offer.class);

    final String mockAliasName = "mockAliasName";
    final Alias mockAlias = mock(Alias.class);
    final long mockSellerId = 123L;

    when(mockAlias.getAccountId()).thenReturn(mockSellerId);
    when(mockAlias.getAliasName()).thenReturn(mockAliasName);

    when(aliasService.getOffer(eq(mockAlias))).thenReturn(mockOfferOnAlias);

    when(parameterServiceMock.getAlias(eq(req))).thenReturn(mockAlias);

    mockStatic(Caret.class);
    final FluxCapacitor fluxCapacitor = QuickMocker.fluxCapacitorEnabledFunctionalities(DIGITAL_GOODS_STORE);
    when(Caret.getFluxCapacitor()).thenReturn(fluxCapacitor);

    final Attachment.MessagingAliasBuy attachment = (Attachment.MessagingAliasBuy) attachmentCreatedTransaction(() -> t.processRequest(req), apiTransactionManagerMock);
    assertNotNull(attachment);

    assertEquals(ALIAS_BUY, attachment.getTransactionType());
    assertEquals(mockAliasName, attachment.getAliasName());
  }

  @Test
  public void processRequest_aliasNotForSale() throws CaretException {
    final HttpServletRequest req = QuickMocker.httpServletRequest(new MockParam(AMOUNT_NQT_PARAMETER, "3"));
    final Alias mockAlias = mock(Alias.class);

    when(parameterServiceMock.getAlias(eq(req))).thenReturn(mockAlias);

    when(aliasService.getOffer(eq(mockAlias))).thenReturn(null);

    assertEquals(INCORRECT_ALIAS_NOTFORSALE, t.processRequest(req));
  }

}
