package brs;

import brs.props.Props;
import java.util.Calendar;
import java.util.TimeZone;

public final class Constants {

  public static final int CARET_DIFF_ADJUST_CHANGE_BLOCK = 1500;

  public static final long CARET_REWARD_RECIPIENT_ASSIGNMENT_START_BLOCK = 1500;
  public static final long CARET_REWARD_RECIPIENT_ASSIGNMENT_WAIT_TIME = 4;

  // not sure when these were enabled, but they each do an alias lookup every block if greater than the current height
  public static final long CARET_ESCROW_START_BLOCK = 0;
  public static final long CARET_SUBSCRIPTION_START_BLOCK = 0;
  public static final int CARET_SUBSCRIPTION_MIN_FREQ = 3600;
  public static final int CARET_SUBSCRIPTION_MAX_FREQ = 31536000;

  public static final int BLOCK_HEADER_LENGTH = 232;

  public static final long MAX_BALANCE_CARET = 647643840L;
  
  public static final long FEE_QUANT =    735000;
  public static final long ONE_CARET = 100000000;

  public static final long MAX_BALANCE_NQT = MAX_BALANCE_CARET * ONE_CARET;
  public static final long INITIAL_BASE_TARGET = 18325193796L;
  public static final long MAX_BASE_TARGET = 18325193796L;
  public static final int MAX_ROLLBACK = Caret.getPropertyService().getInt(Props.DB_MAX_ROLLBACK);

  public static final int MAX_ALIAS_URI_LENGTH = 1000;
  public static final int MAX_ALIAS_LENGTH = 100;

  public static final int MAX_ARBITRARY_MESSAGE_LENGTH = 1000;
  public static final int MAX_ENCRYPTED_MESSAGE_LENGTH = 1000;

  public static final int MAX_MULTI_OUT_RECIPIENTS = 64;
  public static final int MAX_MULTI_SAME_OUT_RECIPIENTS = 128;

  public static final int MAX_ACCOUNT_NAME_LENGTH = 100;
  public static final int MAX_ACCOUNT_DESCRIPTION_LENGTH = 1000;

  public static final long MAX_ASSET_QUANTITY_QNT = 1000000000L * 100000000L;
  public static final long ASSET_ISSUANCE_FEE_NQT = 1000 * ONE_CARET;
  public static final int MIN_ASSET_NAME_LENGTH = 3;
  public static final int MAX_ASSET_NAME_LENGTH = 10;
  public static final int MAX_ASSET_DESCRIPTION_LENGTH = 1000;
  public static final int MAX_ASSET_TRANSFER_COMMENT_LENGTH = 1000;

  public static final int MAX_POLL_NAME_LENGTH = 100;
  public static final int MAX_POLL_DESCRIPTION_LENGTH = 1000;
  public static final int MAX_POLL_OPTION_LENGTH = 100;
  public static final int MAX_POLL_OPTION_COUNT = 100;

  public static final int MAX_DGS_LISTING_QUANTITY = 1000000000;
  public static final int MAX_DGS_LISTING_NAME_LENGTH = 100;
  public static final int MAX_DGS_LISTING_DESCRIPTION_LENGTH = 1000;
  public static final int MAX_DGS_LISTING_TAGS_LENGTH = 100;
  public static final int MAX_DGS_GOODS_LENGTH = 10240;

  public static final int NQT_BLOCK = 0;
  public static final int REFERENCED_TRANSACTION_FULL_HASH_BLOCK = 0;
  public static final int REFERENCED_TRANSACTION_FULL_HASH_BLOCK_TIMESTAMP = 0;
  public static final int PUBLIC_KEY_ANNOUNCEMENT_BLOCK = Integer.MAX_VALUE;

  public static final int MAX_AUTOMATED_TRANSACTION_NAME_LENGTH = 30;
  public static final int MAX_AUTOMATED_TRANSACTION_DESCRIPTION_LENGTH = 1000;

  public static final String MIN_VERSION = "2.2.1";

  static final long UNCONFIRMED_POOL_DEPOSIT_NQT = (Caret.getPropertyService().getBoolean(Props.DEV_TESTNET) ? 50 : 100) * ONE_CARET;

  public static final long EPOCH_BEGINNING;

  static {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    calendar.set(Calendar.YEAR, 2018);
    calendar.set(Calendar.MONTH, Calendar.DECEMBER);
    calendar.set(Calendar.DAY_OF_MONTH, 5);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    EPOCH_BEGINNING = calendar.getTimeInMillis();

    if (MAX_ROLLBACK < 1440) {
      throw new RuntimeException("brs.maxRollback must be at least 1440");
    }
  }

  public static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";

  public static final int EC_RULE_TERMINATOR = 2400; /* cfb: This constant defines a straight edge when "longest chain"
                                                        rule is outweighed by "economic majority" rule; the terminator
                                                        is set as number of seconds before the current time. */

  public static final int EC_BLOCK_DISTANCE_LIMIT = 60;
  public static final int EC_CHANGE_BLOCK_1 = 1500;

  public static final String RESPONSE = "response";
  public static final String TOKEN = "token";
  public static final String WEBSITE = "website";
  public static final String PROTOCOL = "protocol";

  private Constants() {
  } // never

}
