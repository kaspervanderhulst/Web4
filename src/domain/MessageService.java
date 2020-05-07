package domain;

import com.google.gson.JsonObject;
import db.MessageRepository;
import db.MessageRepositoryStub;

import java.util.List;

public class MessageService {

    private MessageRepository messageRepository = new MessageRepositoryStub();

    //adds a message to the repo with the sender, recipient and message itself
    public void addMessageService(Person sender, Person recipient, String message){
        Message m = new Message(message, recipient, sender);
        messageRepository.addMessage(m);
    }

// Gets all the messages for given user, returns them in a list of messages
    private List<Message> getMessagesForUser(String userId){
        return this.messageRepository.chatMessagesSent(userId);
    }

    // Gets all the messages for given user, returns them in a list of strings
    public List<String> getMessagesForUserString(String userId, String recipId){

        return this.messageRepository.chatMessagesString(userId,recipId);
    }

    // Gets all the sent messages for given user, returns them in a list of strings
    public List<String> getMessageForUserSentString(String userid){
        return this.messageRepository.chatMessagesSentString(userid);
    }
    // Gets all the received messages for given user, returns them in a list of strings
    public List<String> getMessageForUserReceivedString(String userid){
        return this.messageRepository.chatMessagesReceivedString(userid);
    }
}
