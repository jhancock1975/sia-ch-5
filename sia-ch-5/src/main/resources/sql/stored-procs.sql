/* present number of shares held for a given ticker */
delimiter //
create function getNumSharesHeld(symbol varchar(255))
returns int
reads sql data
begin
	set @result =  (select ifnull((select sum(qty) from stockTxs where ticker=symbol and txtypeid=1), 0)
		- ifnull((select sum(qty) from stockTxs where ticker=symbol and txtypeid=2), 0));
	return @result;
end //
delimiter ;

/* total amount spent on shares with ticker */
delimiter //
create function getAmountSpent(symbol varchar(255))
returns float
reads sql data
begin
	set @result = (select ifnull((select sum(amount) from stockTxs where ticker=symbol and txtypeid=1), 0)
		- ifnull((select sum(amount) from stockTxs where ticker=symbol and txtypeid=2), 0));
	return @result;
end //
delimiter ;

/* list of stocks held */
drop procedure if exists getStocksHeld;
delimiter //
create procedure getStocksHeld()
begin
	select * from stocks where  getNumSharesHeld(ticker) > 0;
end //
delimiter ;

/* list of commission free etfs */
delimiter //
create procedure getCommishFreeEtfs()
begin
	select * from stocks s where commishFreeEtf='y';
end //
delimiter ;
