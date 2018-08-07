package org.litespring.service.v4;

import org.litespring.dao.v3.AccountDao;
import org.litespring.dao.v3.ItemDao;
import org.litespring.stereotype.Autowired;
import org.litespring.stereotype.Component;

/**
 * PetStoreService.
 *
 * @author Dulk
 * @version 20180806
 * @date 2018/8/6
 */
@Component(value = "petStore")
public class PetStoreService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
}
