package savings.merchant;

import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;

import savings.account.Account;
import savings.purchase.Purchase;

public enum PaybackPolicy {

    NEVER {
        @Override
        public boolean isEligible(Account account, Purchase purchase) {
            return false;
        }
    },

    ALWAYS {
        @Override
        public boolean isEligible(Account account, Purchase purchase) {
            return true;
        }
    },

    WEEKDAYS {
        @Override
        public boolean isEligible(Account account, Purchase purchase) {
            int dayOfWeek = purchase.getDate().getDayOfWeek();
            return SATURDAY != dayOfWeek && SUNDAY != dayOfWeek;
        }
    },

    WEEKENDS {
        @Override
        public boolean isEligible(Account account, Purchase purchase) {
            int dayOfWeek = purchase.getDate().getDayOfWeek();
            return SATURDAY == dayOfWeek || SUNDAY == dayOfWeek;
        }
    };

    public String getId() {
        return name();
    }

    public abstract boolean isEligible(Account account, Purchase purchase);

}
