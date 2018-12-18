package brs.assetexchange;

import brs.Account.AccountAsset;
import brs.db.CaretIterator;
import brs.db.store.AccountStore;

class AssetAccountServiceImpl {

  private final AccountStore accountStore;

  public AssetAccountServiceImpl(AccountStore accountStore) {
    this.accountStore = accountStore;
  }

  public CaretIterator<AccountAsset> getAssetAccounts(long assetId, int from, int to) {
    return accountStore.getAssetAccounts(assetId, from, to);
  }

  public CaretIterator<AccountAsset> getAssetAccounts(long assetId, int height, int from, int to) {
    if (height < 0) {
      return getAssetAccounts(assetId, from, to);
    }
    return accountStore.getAssetAccounts(assetId, height, from, to);
  }

  public int getAssetAccountsCount(long assetId) {
    return accountStore.getAssetAccountsCount(assetId);
  }

}
