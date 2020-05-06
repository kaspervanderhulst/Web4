package domain;

import com.google.gson.JsonObject;
import db.MessageRepository;
import db.MessageRepositoryStub;

import java.util.List;

public class MessageService {

    private MessageRepository messageRepository = new MessageRepositoryStub();

    public void addMessage(Person sender, Person recipient, String message){
        Message m = new Message(message, recipient, sender);
        messageRepository.addMessage(m);
    }

    public JsonObject getMessages(Person user){

        JsonObject messages = getMessagesAsJson(getMessagesForUser(user.getUserId()),user);

        return messages;
    }

    public void addMessageService(Person sender, Person recipient, String message){
        Message m = new Message(message, recipient, sender);
        messageRepository.addMessage(m);
    }

    public List<String> getMessagesForUserString(String userId, String recipId){

        return this.messageRepository.chatMessagesString(userId,recipId);
    }

    public JsonObject getMessagesAsJson(List<Message> messages, Person user){
        JsonObject object = new JsonObject();

        int teller = 0;
        for(Message message: messages){

            JsonObject messageObject = new JsonObject();
            messageObject.addProperty("message", message.getMessage());
            messageObject.addProperty("recipientId", message.getRecipientId());
            messageObject.addProperty("sender", message.getSender().getFirstName());
            messageObject.addProperty("senderId",message.getSenderId());
            object.add(String.valueOf(teller),messageObject);
            teller+=1;
        }
        teller=0;
        return object;
    }

    private List<Message> getMessagesForUser(String userId){

        return this.messageRepository.chatMessagesSent(userId);
    }
    public List<String> getMessageForUserSentString(String userid){
        return this.messageRepository.chatMessagesSentString(userid);
    }

    public List<String> getMessageForUserReceivedString(String userid){
        return this.messageRepository.chatMessagesReceivedString(userid);
    }
}
