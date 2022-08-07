package com.board.web.service.jdbc;

import java.security.MessageDigest;
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
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, writerId);
			st.setString(2, encryptedPassword);
			st.setString(3, title);
			st.setString(4, content);
			st.setString(5, hashtags);

			result = st.executeUpdate();

			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<PostView> getPostViewList() {
		List<PostView> list = new ArrayList<>();

		String sql = "SELECT * FROM POST P ORDER BY P.POST_DATE DESC";

		try {
			Connection con = this.dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int postNumber = rs.getInt("POST_NUMBER");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("USER_ID");
				Date postDate = rs.getDate("POST_DATE");
				
				// 게시글의 댓글 수를 제대로 알아오도록 sql문 수정해야 함
				int commentCount = 0;
				int hit = rs.getInt("HIT");
				int like = rs.getInt("LIKE");

				PostView postView = new PostView(postNumber, title, writerId, postDate, commentCount, hit, like);
				list.add(postView);
			}

			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
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
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlPost);

			if (rs.next()) {
				String writerId = rs.getString("USER_ID");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date postDate = rs.getDate("POST_DATE");
				Date modifyDate = rs.getDate("MODIFY_DATE");
				String hashtags = rs.getString("HASHTAGS");
				int hit = rs.getInt("HIT");
				int like = rs.getInt("LIKE");

				List<String> hashtagList = new ArrayList<>();
				StringTokenizer tokenizer = new StringTokenizer(hashtags, ",");
				while (tokenizer.hasMoreTokens()) {
					hashtagList.add(tokenizer.nextToken());
				}

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
			} else {
				return result;
			}

			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, writerId);
			st.setString(2, encryptedPassword);
			st.setString(3, content);
			st.setInt(4, postNumber);

			result = st.executeUpdate();

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
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlPassword);

			if (rs.next()) {
				String compare = rs.getString("PASSWORD");

				if (encryptedPassword.equals(compare)) {
					String sqlDeleteComment = "DELETE FROM \"COMMENT\" WHERE COMMENT_NUMBER = " + commentNumber;
					result = st.executeUpdate(sqlDeleteComment);
				}
			} else {
				return result;
			}

			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
