Lab 01 - intro
--
1. Create `savings.service.PaybackBookKeeper` implementation class in `savings.service.impl` package.
2. Implement `PaybackBookKeeperImpl` configuration logic utilizing a constructor to inject (configure):
    * An `AccountRepository` to load `Account` objects to add payback to.
    * A `MerchantRepository` to load `Merchant` objects to calculate payback amount.
    * A `PaybackRepository` to tract confirmed paybacks for accounting and reporting.
3. Use interface JavaDoc and provided unit tests stubs to implement `PaybackBookKeeperImpl` application logic.
