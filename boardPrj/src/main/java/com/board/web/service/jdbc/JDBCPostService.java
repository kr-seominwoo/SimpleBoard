package com.board.web.service.jdbc;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.web.entity.Board;
import com.board.web.entity.Comment;
import com.board.web.entity.Post;
import com.board.web.entity.PostView;
import com.board.web.security.SHA256;
import com.board.web.service.PostService;

@Service
public class JDBCPostService implements PostService {
	@Autowired
	DataSource dataSource;

	@Override
	public int registPost(String writerId, String title, String content, String password, String hashtags) {
		int result = 0;

		String sql = "INSERT INTO POST(USER_ID, PASSWORD, TITLE, CONTENT, HASHTAGS, POST_NUMBER) VALUES(?,?,?,?,?, POST_NUMBER_SEQ.NEXTVAL)";
		SHA256 sha256 = new SHA256();
		String encryptedPassword = "";
		try {
			encryptedPassword = sha256.encrypt(password);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			return result;
		}

		try {
			Connection con = this.dataSource.getConnection();
			con.setAutoCommit(false);
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, writerId);
			st.setString(2, encryptedPassword);
			st.setString(3, title);
			st.setString(4, content);
			st.setString(5, hashtags);

			result = st.executeUpdate();

			con.commit();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}


	@Override
	public Board getBoard() {
		return getBoard("","");
	}
	
	@Override
	public Board getBoard(String field, String searchContent) {
		List<PostView> list = new ArrayList<>();
		Board board = new Board(list, 0);
		int totalCommentCount = 0;

		switch (field) {
		case "title":
			field = "TITLE";
			break;
		case "writerId":
			field = "USER_ID";
			break;
		case "content":
			field = "CONTENT";
			break;
		case "hashtag":
			field = "HASHTAGS";
			break;
		default:
			field = "";
			break;
		}		
		
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM POST_VIEW");
		if (!field.equals("") && !searchContent.equals("")) {
			builder.append(" WHERE ");

			
			builder.append(field);
			builder.append(" LIKE '%");
			builder.append(searchContent);
			builder.append("%'");
		}		
		builder.append(" ORDER BY POST_DATE DESC");
		
		String sql = builder.toString();

		try {
			Connection con = this.dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int postNumber = rs.getInt("POST_NUMBER");
				String postTitle = rs.getString("TITLE");
				String postWriterId = rs.getString("USER_ID");
				Date postDate = rs.getDate("POST_DATE");
				int commentCount = rs.getInt("COMMENT_COUNT");
				int hit = rs.getInt("HIT");
				int like = rs.getInt("LIKE");

				PostView postView = new PostView(postNumber, postTitle, postWriterId, postDate, commentCount, hit, like);
				list.add(postView);
				totalCommentCount += commentCount;
			}

			board.setTotalCommentCount(totalCommentCount);
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return board;
	}

	@Override
	public Post getPost(int postNumber) {
		Post post = null;
		List<Comment> commentList = new ArrayList<>();

		String sqlPost = "SELECT * FROM POST P WHERE P.POST_NUMBER = " + postNumber;
		String sqlComment = "SELECT * FROM \"COMMENT\" C WHERE C.POST_NUMBER = " + postNumber
				+ " ORDER BY C.COMMENT_DATE DESC";
		
		try {
			Connection con = this.dataSource.getConnection();	
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlPost);

			if (rs.next()) {
				String writerId = rs.getString("USER_ID");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date postDate = rs.getDate("POST_DATE");
				Date modifyDate = rs.getDate("MODIFY_DATE");
				String hashtags = rs.getString("HASHTAGS");
				int hit = rs.getInt("HIT") + 1;
				int like = rs.getInt("LIKE");

				List<String> hashtagList = new ArrayList<>();
				if (hashtags != null) {
					StringTokenizer tokenizer = new StringTokenizer(hashtags, ",");
					while (tokenizer.hasMoreTokens()) {
						hashtagList.add(tokenizer.nextToken());
					}
				}				

				String sqlHitUpdate = "UPDATE POST SET HIT = " + hit + " WHERE POST_NUMBER = " + postNumber;
				st.executeUpdate(sqlHitUpdate);				
				
				post = new Post(writerId, title, content, postDate, modifyDate, hashtagList, like, hit, postNumber,
						commentList);
			}

			rs = st.executeQuery(sqlComment);
			while (rs.next()) {
				String writerId = rs.getString("USER_ID");
				String content = rs.getString("CONTENT");
				Date commentDate = rs.getDate("COMMENT_DATE");
				int commentNumber = rs.getInt("COMMENT_NUMBER");

				Comment comment = new Comment(writerId, content, commentDate, postNumber, commentNumber);
				commentList.add(comment);
			}

			con.commit();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return post;
	}

	@Override
	public int deletePost(String password, int postNumber) {
		int result = 0;

		SHA256 sha256 = new SHA256();
		String encryptedPassword = "";
		try {
			encryptedPassword = sha256.encrypt(password);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			return result;
		}

		String sqlPassword = "SELECT P.PASSWORD FROM POST P WHERE POST_NUMBER = " + postNumber;
		try {
			Connection con = dataSource.getConnection();
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlPassword);

			if (rs.next()) {
				String compare = rs.getString("PASSWORD");

				if (encryptedPassword.equals(compare)) {
					String sqlDeletePost = "DELETE FROM POST WHERE POST_NUMBER = " + postNumber;
					result += st.executeUpdate(sqlDeletePost);

					String sqlDeleteComment = "DELETE FROM \"COMMENT\" WHERE POST_NUMBER = " + postNumber;
					result += st.executeUpdate(sqlDeleteComment);
				}
			}

			con.commit();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int registComment(String writerId, String content, String password, int postNumber) {
		int result = 0;

		String sql = "INSERT INTO \"COMMENT\" (USER_ID, PASSWORD, CONTENT, POST_NUMBER, COMMENT_NUMBER) VALUES(?,?,?,?, COMMENT_NUMBER_SEQ.NEXTVAL)";
		SHA256 sha256 = new SHA256();
		String encryptedPassword = "";
		try {
			encryptedPassword = sha256.encrypt(password);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			return result;
		}

		try {
			Connection con = this.dataSource.getConnection();
			con.setAutoCommit(false);
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, writerId);
			st.setString(2, encryptedPassword);
			st.setString(3, content);
			st.setInt(4, postNumber);

			result = st.executeUpdate();

			con.commit();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int deleteComment(String password, int commentNumber) {
		int result = 0;

		SHA256 sha256 = new SHA256();
		String encryptedPassword = "";
		try {
			encryptedPassword = sha256.encrypt(password);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			return result;
		}

		String sqlPassword = "SELECT C.PASSWORD FROM \"COMMENT\" C WHERE COMMENT_NUMBER = " + commentNumber;
		try {
			Connection con = dataSource.getConnection();
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlPassword);

			if (rs.next()) {
				String compare = rs.getString("PASSWORD");

				if (encryptedPassword.equals(compare)) {
					StringBuilder builder = new StringBuilder();
					builder.append("UPDATE \"COMMENT\" ");
					builder.append("SET ");
					builder.append("CONTENT = '삭제된 댓글입니다' ");
					builder.append("WHERE COMMENT_NUMBER = ");
					builder.append(commentNumber);
					
					String sqlDeleteComment = builder.toString();					
					result = st.executeUpdate(sqlDeleteComment);
				}
			}

			con.commit();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int updatePost(String title, String content, String hashtags, int postNumber) {
		int result = 0;

		String sql = "UPDATE POST SET TITLE=?, CONTENT=?, HASHTAGS=? WHERE POST_NUMBER=?";
		try {
			Connection con = dataSource.getConnection();
			con.setAutoCommit(false);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, hashtags);
			st.setInt(4, postNumber);
			
			result = st.executeUpdate();

			con.commit();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int isYourPost(String password, int postNumber) {
		final int PASSWORD_MATCH = 1;
		int result = 0;

		SHA256 sha256 = new SHA256();
		String encryptedPassword = "";
		try {
			encryptedPassword = sha256.encrypt(password);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			return result;
		}

		String sqlPassword = "SELECT P.PASSWORD FROM POST P WHERE POST_NUMBER = " + postNumber;
		try {
			Connection con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlPassword);

			if (rs.next()) {
				String compare = rs.getString("PASSWORD");

				if (encryptedPassword.equals(compare)) {
					result = PASSWORD_MATCH;
				}
			}

			st.close();
			con.close();
		} catch (SQLException e) {
			result = 0;
			e.printStackTrace();
		}

		return result;		
	}

}
