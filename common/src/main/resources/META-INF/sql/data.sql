insert into ACCOUNT (NUMBER, NAME)
  values ('123456789', 'Jane & John Smith');

insert into ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER)
  values (1, '1234123412341234');

insert into ACCOUNT_OBJECTIVE (ACCOUNT_ID, NAME, ALLOCATION, SAVINGS)
	values (1, 'Glock', 0.5, 0.00);
insert into ACCOUNT_OBJECTIVE (ACCOUNT_ID, NAME, ALLOCATION, SAVINGS)
  values (1, 'M60', 0.5, 0.00);

insert into MERCHANT (NUMBER, NAME, PAYBACK, PAYBACK_POLICY)
	values ('1234567890', 'Guns & Bombs', 0.06, 'ALWAYS');
