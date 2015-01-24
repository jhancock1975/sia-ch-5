package com.rootser.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;

/**
 * 
 * @author john the repository annotation is for hibernate to scan the class and
 *         see that it is a data access object class
 * 
 *         the transactional annotation because hibernate complains about
 *         "No hibernate session bound to thread"
 */
@Repository
@Transactional
public class HibernateStockDao implements StockDao {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	public String addStock(Stock s) {
		currentSession().saveOrUpdate(s);
		return s.getTicker();
	}

	public Integer addStockTx(StockTx tx) {
		currentSession().save(tx);
		// after saving hibernate will fill in the id
		return tx.getStockTxId();
	}

	public Integer getNumSharesHeld(String ticker) {
		SQLQuery query = currentSession().createSQLQuery(
				"select getNumSharesHeld(:ticker)");
		query = (SQLQuery) query.setString("ticker", ticker);
		return (Integer) query.uniqueResult();

	}

	public Float getAmountSpent(String ticker) {
		SQLQuery query = currentSession().createSQLQuery(
				"select  getAmountSpent(:ticker)");
		query = (SQLQuery) query.setString("ticker", ticker);
		return -1 * ((Float) query.uniqueResult());
	}

	@Override
	@Cacheable(cacheName = "stocksHeld", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = { @Property(name = "includeMethod", value = "false") }))
	public List<Stock> getStocksHeld() {
		SQLQuery query = currentSession()
				.createSQLQuery("call getStocksHeld()").addEntity(Stock.class);
		List<Object> raw = query.list();
		List<Stock> result = query.list();
		return result;
	}

	@Override
	public void removeStock(Stock stock) {
		currentSession().delete(stock);

	}

	@Override
	public void removeStockTx(StockTx stockTx) {
		currentSession().delete(stockTx);

	}

	@Override
	@Cacheable(cacheName = "commishFreeEtfs", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = { @Property(name = "includeMethod", value = "false") }))
	public List<Stock> getCommishFreeEtfs() throws DbReadException {
		try {
			Query query = currentSession().createQuery("from stocks where commishFreeEtf='Y'");
			return (List<Stock>) query.list();
		} catch (Exception e) {
			throw new DbReadException(
					"error reading commission free etfs from database");
		}
	}

	@Override
	@Cacheable(cacheName = "commishFreeEtfsAndTickers", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = { @Property(name = "includeMethod", value = "false") }))
	public Map<String, String> getCommishFreeEtfsAndDesc() {
		Map<String, String> result = null;

		Query query = currentSession().createQuery("from stocks where commishFreeEtf='Y'");
		List<Stock> stockList = query.list();
		result = new HashMap<String, String>();
		for (Stock s: stockList){
			result.put(s.getTicker(), s.getDescription());
		}
			return result;
		}
	}
