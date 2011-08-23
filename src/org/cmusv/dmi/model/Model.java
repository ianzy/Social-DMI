package org.cmusv.dmi.model;

/**
 * @Author Vinod Ekambaram
 * @file AttributeDAO.java
 */

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.cmusv.dmi.twitter.SearchTweets;
import org.mybeans.factory.BeanTable;

import twitter4j.TwitterException;

public class Model {
	

	private KeywordDAO keywordDAO;
	
	private TweetsDAO tweetsDAO;
	
	private SearchTweets st;

	public Model(ServletConfig config) throws ServletException {

		String jdbcDriver = config.getInitParameter("jdbcDriverName");
		String jdbcURL = config.getInitParameter("jdbcURL");
		BeanTable.useJDBC(jdbcDriver, jdbcURL, "root", "");
		keywordDAO = new KeywordDAO();
		tweetsDAO = new TweetsDAO();
		
		st = new SearchTweets(tweetsDAO, keywordDAO);
		try {
			st.getTweetsByKeyword();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Model(String jdbcDriver, String jdbcURL) {
		BeanTable.useJDBC(jdbcDriver, jdbcURL);
		keywordDAO = new KeywordDAO();
		tweetsDAO = new TweetsDAO();
	}

	public KeywordDAO getKeywordDAO() {
		return keywordDAO;
	}

	public TweetsDAO getTweetsDAO() {
		return tweetsDAO;
	}
	
}
