package brs.db.store;

import brs.Account;
import brs.db.CaretIterator;
import brs.db.VersionedBatchEntityTable;
import brs.db.VersionedEntityTable;
import brs.db.CaretKey;

/**
 * Interface for Database operations related to Accounts
 */
public interface AccountStore {

  VersionedBatchEntityTable<Account> getAccountTable();

  VersionedEntityTable<Account.RewardRecipientAssignment> getRewardRecipientAssignmentTable();

  CaretKey.LongKeyFactory<Account.RewardRecipientAssignment> getRewardRecipientAssignmentKeyFactory();

  CaretKey.LinkKeyFactory<Account.AccountAsset> getAccountAssetKeyFactory();

  VersionedEntityTable<Account.AccountAsset> getAccountAssetTable();

  int getAssetAccountsCount(long assetId);

  CaretKey.LongKeyFactory<Account> getAccountKeyFactory();

  CaretIterator<Account.RewardRecipientAssignment> getAccountsWithRewardRecipient(Long recipientId);

  CaretIterator<Account.AccountAsset> getAssets(int from, int to, Long id);

  CaretIterator<Account.AccountAsset> getAssetAccounts(long assetId, int from, int to);

  CaretIterator<Account.AccountAsset> getAssetAccounts(long assetId, int height, int from, int to);
  // returns true iff:
  // this.publicKey is set to null (in which case this.publicKey also gets set to key)
  // or
  // this.publicKey is already set to an array equal to key
  boolean setOrVerify(Account acc, byte[] key, int height);
}
