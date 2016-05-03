package com.umkcisguide;

import java.awt.color.ProfileDataException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

@Path("/umkcis")
public class MongoRestServices {
	private final static String FIRST_NAME = "fname";
	private final static String LAST_NAME = "lname";
	private final static String EMAIL = "email";
	private final static String USER_NAME = "username";
	private final static String PASSWORD = "password";
	private final static String ID = "id";
	private final static String POST_DATA = "postdata";

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/plain")
	public Response insert(MultivaluedMap<String, String> userParams) {
		MongoConnection mc = new MongoConnection();
		DB db = mc.createConnnect();
		DBCollection users = db.getCollection("umkcusers");
		/*
		 * System.out.println(user); Gson gson = new Gson();
		 * System.out.println(user);
		 */
		String fname = userParams.getFirst(FIRST_NAME);
		String lname = userParams.getFirst(LAST_NAME);
		String username = userParams.getFirst(USER_NAME);
		String email = userParams.getFirst(EMAIL);
		String password = userParams.getFirst(PASSWORD);
		BasicDBObject progress = new BasicDBObject();
		progress.put("passport", false);
		progress.put("shop", false);
		progress.put("visa", false);
		progress.put("i20", false);
		progress.put("gre", false);
		progress.put("ielts", false);
		progress.put("apply", false);
		progress.put("funds", false);
		progress.put("flight", false);
		progress.put("acco",false);
		BasicDBObject doc = new BasicDBObject("fname", fname).append("lname", lname).append("username", username)
				.append("email", email).append("password", password).append("progress", progress);
		WriteResult r = users.insert(doc);
		ObjectId i = doc.getObjectId("_id");
		/*DBCollection progressDB = db.getCollection("progress");
		*/
		/*progressDB.insert(progress);
		*/return Response.status(201).entity(i.toString()).build();
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/plain")
	public Response login(MultivaluedMap<String, String> userParams) {
		String i = null;
		MongoConnection mc = new MongoConnection();
		DB db = mc.createConnnect();
		DBCollection users = db.getCollection("umkcusers");
		String username = userParams.getFirst(USER_NAME);
		String password = userParams.getFirst(PASSWORD);
		System.out.println("hello " + username + " " + password);
		BasicDBObject query = new BasicDBObject();
		query.put("username", username);
		query.put("password", password);
		DBCursor cursor = users.find(query);
		if (cursor.count() >= 1) {
			DBObject o = cursor.next();
			i = o.get("_id").toString();
		}

		return Response.status(201).entity(i).build();
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/plain")
	public Response insertPost(MultivaluedMap<String, String> userParams) {
		MongoConnection mc = new MongoConnection();
		DB db = mc.createConnnect();
		JSONArray comment = new JSONArray();
		System.out.println(comment.toString());
		DBCollection users = db.getCollection("posts");
		String id = userParams.getFirst(ID);
		String username = getName(id, db);
		String postData = userParams.getFirst(POST_DATA);
		BasicDBObject doc = new BasicDBObject("id", id).append("username", username).append("post", postData);
		WriteResult r = users.insert(doc);
		ObjectId i = doc.getObjectId("_id");
		// System.out.println(i);
		return Response.status(201).entity(i.toString()).build();
	}

	@GET
	@Path("/getRecord")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRecord() {
		MongoConnection mc = new MongoConnection();
		DB db = mc.createConnnect();
		JSONObject obj = new JSONObject();
		JSONArray ar = new JSONArray();
		Gson gs = new Gson();
		DBCollection users = db.getCollection("posts");
		BasicDBObject query = new BasicDBObject();
		DBCursor cursor = users.find(query);
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			JSONObject obj1 = new JSONObject();
			obj1.put("postid", o.get("_id"));
			obj1.put("userid", o.get("id"));
			obj1.put("username", o.get("username"));
			obj1.put("posttext", o.get("post"));
			obj1.put("comments", o.get("comments"));
			ar.put(obj1);
		}
		obj.put("posts", ar);
		return Response.status(201).entity(obj.toString()).build();
	}

	public String getName(String id, DB db) {

		String username = "";
		DBCollection dc = db.getCollection("umkcusers");
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBCursor cursor = dc.find(query);
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			username = o.get("fname") + " " + o.get("lname");
		}
		return username;
	}

	@POST
	@Path("/postComment")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/plain")
	public Response insertComment(MultivaluedMap<String, String> postParams) {
		MongoConnection mc = new MongoConnection();
		DB db = mc.createConnnect();
		JSONArray comment = new JSONArray();
		System.out.println(comment.toString());
		DBCollection posts = db.getCollection("posts");
		String postid = postParams.getFirst("postid");
		String userid = postParams.getFirst("userid");
		String username = postParams.getFirst("username");
		String commenText = postParams.getFirst("comment");
		BasicDBObject postDest = new BasicDBObject();
		postDest.put("_id", new ObjectId(postid));
		BasicDBObject commentData = new BasicDBObject();
		commentData.put("uid", userid);
		commentData.put("username", getName(userid, db));
		commentData.put("commentText", commenText);

		BasicDBObject comments = new BasicDBObject();
		comments.put("$push", new BasicDBObject("comments", commentData));
		WriteResult r = posts.update(postDest, comments);
		return Response.status(201).entity(postDest.getObjectId("_id").toString()).build();
	}

	@POST
	@Path("/getComment")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getComment(MultivaluedMap<String, String> postParams) {
		MongoConnection mc = new MongoConnection();
		DB db = mc.createConnnect();
		JSONObject obj = new JSONObject();
		JSONArray ar = new JSONArray();
		String id = postParams.getFirst("postid");
		JSONObject obj1 = null;
		DBCollection users = db.getCollection("posts");
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		DBCursor cursor = users.find(query);
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			obj1 = new JSONObject();
			obj1.put("comments", o.get("comments"));
			System.out.println(o.get("comments"));
			// ar.put(o.get("comments"));
		}

		System.out.println(obj1.toString());
		return Response.status(201).entity(obj1.toString()).build();
	}
	
	@POST
	@Path("/profile")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfile(MultivaluedMap<String, String> profileParams) {
		MongoConnection mc = new MongoConnection();
		DB db = mc.createConnnect();
		JSONObject obj = null;
		String id = profileParams.getFirst("id");
		DBCollection users = db.getCollection("umkcusers");
		BasicDBObject uid = new BasicDBObject();
		uid.put("_id",new ObjectId(id));
		DBCursor cursor = users.find(uid);
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			obj = new JSONObject();
			obj.put("user",o);
		}

		System.out.println(obj.toString());
		return Response.status(201).entity(obj.toString()).build();
	}
	
	@POST
	@Path("/updateprogress")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/plain")
	public Response updateProgress(MultivaluedMap<String, String> progressParams) {
		MongoConnection mc = new MongoConnection();
		DB db = mc.createConnnect();
		DBCollection posts = db.getCollection("umkcusers");
		String userid = progressParams.getFirst("id");
		Boolean passport = Boolean.parseBoolean(progressParams.getFirst("passport"));
		Boolean shop = Boolean.parseBoolean(progressParams.getFirst("shop"));
		Boolean visa = Boolean.parseBoolean(progressParams.getFirst("visa"));
		Boolean i20 = Boolean.parseBoolean(progressParams.getFirst("i20"));
		Boolean gre = Boolean.parseBoolean(progressParams.getFirst("gre"));
		Boolean ielts = Boolean.parseBoolean(progressParams.getFirst("ielts"));
		Boolean apply = Boolean.parseBoolean(progressParams.getFirst("apply"));
		Boolean funds = Boolean.parseBoolean(progressParams.getFirst("funds"));
		Boolean flight = Boolean.parseBoolean(progressParams.getFirst("flight"));
		Boolean acco = Boolean.parseBoolean(progressParams.getFirst("acco"));
		BasicDBObject progress = new BasicDBObject();
		progress.put("passport", passport);
		progress.put("shop", shop);
		progress.put("visa", visa);
		progress.put("i20", i20);
		progress.put("gre", gre);
		progress.put("ielts", ielts);
		progress.put("apply", apply);
		progress.put("funds", funds);
		progress.put("flight", flight);
		progress.put("acco",acco);
		BasicDBObject userDest = new BasicDBObject();
		userDest.put("_id", new ObjectId(userid));
		BasicDBObject pro = new BasicDBObject();
		pro.put("$set", new BasicDBObject("progress", progress));
		WriteResult r = posts.update(userDest, pro);
		return Response.status(201).entity(userDest.getObjectId("_id").toString()).build();
	}
}
