package com.bivashy.lamp.commands.vk.objects;

import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class CallbackButton {

	@SerializedName("user_id")
	private Integer userID;

	@SerializedName("peer_id")
	private Integer peerID;

	@SerializedName("event_id")
	private String eventID;

	@SerializedName("payload")
	private String payload;

	@SerializedName("conversation_message_id")
	private Integer conversationMessageID;

	public Integer getUserID() {
		return userID;
	}

	public CallbackButton setUserID(Integer userID) {
		this.userID = userID;
		return this;
	}

	public Integer getPeerID() {
		return peerID;
	}

	public CallbackButton setPeerID(Integer peerID) {
		this.peerID = peerID;
		return this;
	}

	public String getEventID() {
		return eventID;
	}

	public CallbackButton setEventID(String eventID) {
		this.eventID = eventID;
		return this;
	}

	public String getPayload() {
		return payload;
	}

	public CallbackButton setPayload(String payload) {
		this.payload = payload;
		return this;
	}

	public Integer getConversationMessageID() {
		return conversationMessageID;
	}

	public CallbackButton setConversationMessageID(Integer conversationMessageID) {
		this.conversationMessageID = conversationMessageID;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userID, peerID, eventID, payload, conversationMessageID);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CallbackButton buttonEvent = (CallbackButton) o;
		return Objects.equals(userID, buttonEvent.userID) && Objects.equals(peerID, buttonEvent.peerID)
				&& Objects.equals(eventID, buttonEvent.eventID) && Objects.equals(payload, buttonEvent.payload)
				&& Objects.equals(conversationMessageID, buttonEvent.conversationMessageID);
	}

	@Override
	public String toString() {
		final Gson gson = new Gson();
		return gson.toJson(this);
	}

	public String toPrettyString() {
		final StringBuilder sb = new StringBuilder("CallbackButton{");
		sb.append("userID=").append(userID);
		sb.append(", peerID=").append(peerID);
		sb.append(", eventID='").append(eventID).append("'");
		sb.append(", payload='").append(payload).append("'");
		sb.append(", conversationMessageID=").append(conversationMessageID);
		sb.append('}');
		return sb.toString();
	}
}